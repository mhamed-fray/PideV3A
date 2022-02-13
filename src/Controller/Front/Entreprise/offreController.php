<?php

namespace App\Controller\Front\Entreprise;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class offreController extends AbstractController
{
    /**
     * @Route("/front/creeroffre", name="front_entreprise_creroffre")
     */
    public function index(): Response
    {
        return $this->render('front/entreprise/offre/index.html.twig', [
            'controller_name' => 'offreController',
        ]);
    }

    /**
     * @Route("/front/mesofffres", name="front_entreprise_mesoffres")
     */
    public function ddddd(): Response
    {
        return $this->render('front/entreprise/offre/offres.html.twig', [
            'controller_name' => 'offreController',
        ]);
    }
    /**
     * @Route("/front/tousofffres", name="tous_offres")
     */
    public function ddsffdddd(): Response
    {
        return $this->render('front/entreprise/offre/tousoffres.html.twig', [
            'controller_name' => 'offreController',
        ]);
    }
    /**
     * @Route("/alloffres", name="all_offres")
     */
    public function all(): Response
    {
        return $this->render('back/offres.html.twig', [
            'controller_name' => 'offreController',
        ]);
    }
}
