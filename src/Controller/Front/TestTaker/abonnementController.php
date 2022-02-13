<?php

namespace App\Controller\Front\TestTaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class abonnementController extends AbstractController
{
    /**
     * @Route("/front/test/taker/abonnement", name="front_test_taker_abonnement")
     */
    public function index(): Response
    {
        return $this->render('front/test_taker/abonnement/index.html.twig', [
            'controller_name' => 'abonnementController',
        ]);
    }
    /**
     * @Route("/allabonnements", name="all_abonnements")
     */
    public function all(): Response
    {
        return $this->render('back/abonnements.html.twig', [
            'controller_name' => 'abonnementController',
        ]);
    }
}
