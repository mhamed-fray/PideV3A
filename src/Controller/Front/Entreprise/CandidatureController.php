<?php

namespace App\Controller\Front\Entreprise;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CandidatureController extends AbstractController
{
    /**
     * @Route("/front/entreprise/candidature", name="front_entreprise_candidature")
     */
    public function index(): Response
    {
        return $this->render('front/entreprise/candidature/index.html.twig', [
            'controller_name' => 'CandidatureController',
        ]);
    }
    /**
     * @Route("/allcandidatures", name="all_candidatures")
     */
    public function all(): Response
    {
        return $this->render('back/candidatures.html.twig', [
            'controller_name' => 'CandidatureController',
        ]);
    }
}
