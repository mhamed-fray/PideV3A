<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BackindexController extends AbstractController
{
    /**
     * @Route("/backindex", name="backindex")
     */
    public function index(): Response
    {
        return $this->render('backindex/index.html.twig', [
            'controller_name' => 'BackindexController',
        ]);
    }
     /**
         * @param EvenementRepository $repository
         * @return \Symfony\Component\HttpFundation\Response
         * @Route("/allevenements")
         */
        public function Afficheevad(EvenementRepository $repository)
        {
            $Even=$repository->findAll();
            return $this->render('back/admin/evenements.html.twig',
                ['evenement'=>$Even]);
        }
}
