<?php

namespace App\Controller\Front\Entreprise;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class evenementController extends AbstractController
{
    /**
     * @Route("/front/entreprise/evenement", name="front_entreprise_creerevenement")
     */
    public function index(): Response
    {
        return $this->render('front/entreprise/evenement/index.html.twig', [
            'controller_name' => 'evenementController',
        ]);
    }
    /**
     * @Route("/front/entreprise/evenement", name="front_entreprise_mesevenements")
     */
    public function indssex(): Response
    {
        return $this->render('front/entreprise/evenement/evenements.html.twig', [
            'controller_name' => 'evenementController',
        ]);
    }
    /**
     * @Route("/front/entreprise/evenement", name="front_entreprise_tousevenements")
     */
    public function indddssex(): Response
    {
        return $this->render('front/entreprise/evenement/tousevenements.html.twig', [
            'controller_name' => 'evenementController',
        ]);
    }
    /**
     * @Route("/allevenements", name="all_evenements")
     */
    public function all(): Response
    {
        return $this->render('back/evenements.html.twig', [
            'controller_name' => 'evenementController',
        ]);
    }
}
