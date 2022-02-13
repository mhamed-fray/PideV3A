<?php

namespace App\Controller\Front\Testmaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class candidatureAccpController extends AbstractController
{
    /**
     * @Route("/front/testmaker/candidature/accp", name="front_testmaker_candidature_accp")
     */
    public function index(): Response
    {
        return $this->render('front/testmaker/candidature_accp/index.html.twig', [
            'controller_name' => 'candidatureAccpController',
        ]);
    }
}
