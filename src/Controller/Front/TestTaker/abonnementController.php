<?php

namespace App\Controller\Front\TestTaker;

use App\Entity\Abonnement;
use App\Form\AbonnementType;
use App\Repository\AbonnementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Common\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class abonnementController extends AbstractController
{
    /**
     * @var AbonnementRepository
     */
    private $repository;
    public function __construct(AbonnementRepository $repository){
        $this->repository=$repository;
    }
    /**
     * @Route("/front/test/taker/abonnement", name="front_test_taker_abonnement")
     */
    public function index(): Response
    {
        $abonnements=$this->repository->findAll();

        return $this->render('front/test_taker/abonnement/index.html.twig', [
            'abonnements' => $abonnements,
        ]);
    }
    /**
     * @Route("/allabonnements", name="all_abonnements")
     */
    public function all(): Response
    {
        $abonnements=$this->repository->findAll();
        /**$abonnements = $this->getDoctrine()
            ->getRepository(AbonnementRepository::class)
            ->findAll();*/
        return $this->render('back/abonnements.html.twig', [
            'abonnements' => $abonnements,

            /**'controller_name' => 'abonnementController',*/
        ]);
    }
    /**
     * @Route("/abonnements/{id}", name="admin_abonnement_edit")
     * @param Abonnement $abonnements
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function editer(Abonnement $abonnements,Request $request,$id,EntityManagerInterface $em,AbonnementRepository $abo){
        $abonnements = $abo->find($id);
        $form=$this->createForm(AbonnementType::class,$abonnements);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em->flush();
            $this->addFlash('success','Abonnement modifié avec succès!');
            return $this->redirectToRoute('all_abonnements');
        }
        return $this->render('back/admin/Editabonnements.html.twig', [
            'form'=>$form->createView()]);
    }
    /**
     * @Route("/remove/{id}", name="SupprimerAbonnement")
     */
    public function supprimer($id,AbonnementRepository $abonnements,EntityManagerInterface $em){
        $abonnements= $abonnements->find($id);
        $em->remove($abonnements);
        $em->flush();
        $this->addFlash('success','Abonnement supprimé avec succès!');

        return $this->redirectToRoute('all_abonnements');
    }
    /**
     * @Route("/add", name="ajouterAbonnement")
     */
    public function ajouter(Request $request,EntityManagerInterface $em){
        $abonnement=new Abonnement();
        $form= $this->createForm(AbonnementType::class,$abonnement);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em->persist($abonnement);
            $em->flush();
            $this->addFlash('success','Abonnement ajouté avec succès!');
            return $this->redirectToRoute('all_abonnements');
        }
        return $this->render("back/admin/Ajouterabonnements.html.twig",array('form'=>$form->createView()));
    }
}
