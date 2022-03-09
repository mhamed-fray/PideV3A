<?php

namespace App\Controller\Front\TestTaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ReservationRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Reservation;
use App\Services\QrcodeService;
use App\Form\ReservationType;
use App\Entity\Evenement;
use App\Repository\EvenementRepository;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


class ReservationController extends AbstractController
{
    #[Route('/reservation', name: 'reservation')]
    public function index(): Response
    {
        return $this->render('reservation/index.html.twig', [
            'controller_name' => 'ReservationController',
        ]);
    }




    /**
         * @param EvenementRepository $repository
         * @return \Symfony\Component\HttpFundation\Response
         *  @Route("/tkevenements", name="front_entreprise_mesevenements")
         */
        public function Affichee(EvenementRepository $repository)
        {
            $Even=$repository->affichetrie();
            return $this->render('front/test_taker/reservation/ttousevenements.html.twig',
                ['evenement'=>$Even]);
        }


       /**
     * @param Request $request
     * @param QrcodeService $qrcodeService
     * @return \Symfony\Component\HttpFundation\Response
     * @Route ("/reserver/{id}",name="postuler")
     */
    public function reserver(EvenementRepository $repository,Request $request,$id, QrcodeService $qrcodeService)
    {
        $ev=new Reservation();
        $qrCode = null;
        $form=$this->createForm(ReservationType::class, $ev);
        $form->add('reserver',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $ev=$form->getData();
            $Even=$repository->find($id);
            $qq=$Even->getTitre();
            $qrCode = $qrcodeService->qrcode($qq);
            
            $ev->setEve($id);
            $value=$Even->getnb_participants()+$ev->getNbplace();
            $Even=$repository->nombreDePlace($value,$id);
            $em=$this->getDoctrine()->getManager();
            $em->persist($ev);
            $em->flush();
            //return $this->redirectToRoute('front_entreprise_mesevenements'); #on va ajouter ici le nom de route de l'affichage du succes de reservation
        }
        return $this->render('front/test_taker/reservation/addres.html.twig',[
            'form'=>$form->createView(),
            'qrCode' => $qrCode
        ]);
    }


    
    /**
     *  @param Request $request
     * @return \Symfony\Component\HttpFundation\Response
     * @Route("/modifierreservation/{id}",name="mod")
     */
    public function update(EvenementRepository $repo,$id,Request $req)
    {
        $ev=$repo->find($id);
        $form=$this->CreateForm(EvenementType::class,$ev);
        $form->add("modifier",SubmitType::class);
        $form->handleRequest($req);
        if($form->isSubmitted() && $form->isValid())
        {
            
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('front_entreprise_test');
        }
        return $this->render('front/entreprise/evenement/modifier.html.twig',[
            'form'=>$form->createView()
        ]);
    }

}


