<?php

namespace App\Controller\Back;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\EvenementRepository;
use App\Repository\ReservationRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Evenement;
use App\Entity\Reservation;

class adminController extends AbstractController
{
    /**
     * @Route("/profiladmin", name="profil_admin")
     */
    public function index(): Response
    {
        return $this->render('back/admin/index.html.twig', [
            'controller_name' => 'adminController',
        ]);
    }
    /**
         * @param EvenementRepository $repository
         * @return \Symfony\Component\HttpFundation\Response
         * @Route("/allevenements",name="all_evenements")
         */
        public function Afficheevad(EvenementRepository $repository)
        {
            $Even=$repository->findAll();
            return $this->render('back/evenements.html.twig',
                ['evenement'=>$Even]);
        }


            /**
             *@param Request $request
             *@return \Symfony\Component\HttpFundation\Response
             *@Route("/delete/{id}", name="d")
            */
    public function DeleteE($id , EvenementRepository $repo ){
      
        $evenement=$repo->find($id);

        $em=$this->getDoctrine()->getManager();
        $em->remove($evenement);
        
        $em->flush();
        return $this->redirectToRoute('all_evenements'); 
    }


       /**
             *@param Request $request
             *@return \Symfony\Component\HttpFundation\Response
             *@Route("/deleteee/{id}", name="de")
            */
            public function Deleter($id , ReservationRepository $repo ){
      
                $evenement=$repo->find($id);
        
                $em=$this->getDoctrine()->getManager();
                $em->remove($evenement);
                $em->flush();
                return $this->redirectToRoute('all_reservations'); 
            }

       /**
         * @param EvenementRepository $repository
         * @return \Symfony\Component\HttpFundation\Response
         * @Route("/allreservations",name="all_reservations")
         */
        public function Afficheres(ReservationRepository $repository)
        {
            $res=$repository->findAll();
            return $this->render('back/resrvations.html.twig',
                ['reservation'=>$res]);
        }
        /**
             *@param Request $request
             *@return \Symfony\Component\HttpFundation\Response
             *@Route("/supprimer/{ik}", name="sup")
            */
    public function del($ik , ReservationRepository $repo ){
        $evenement=$repo->find($ik);
        $em=$this->getDoctrine()->getManager();
        $em->remove($evenement);
        $em->flush();
        return $this->redirectToRoute('all_reservations'); 
    }
}
