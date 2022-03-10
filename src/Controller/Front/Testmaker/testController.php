<?php

namespace App\Controller\Front\Testmaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class testController extends AbstractController
{
    /**
     * @Route("/front/testmaker/test", name="front_testmaker_test")
     */
    public function index(): Response
    {
        return $this->render('front/testmaker/test/index.html.twig', [
            'controller_name' => 'testController',
        ]);
    }
    /**
     * @Route("/front/testmaker/test", name="front_testmaker_mestests")
     */
    public function s(): Response
    {
        return $this->render('front/testmaker/test/index.html.twig', [
            'controller_name' => 'testController',
        ]);
    }
    /**
     * @Route("/alltests", name="all_tests")
     */
    public function all(): Response
    {
        return $this->render('back/tests.html.twig', [
            'controller_name' => 'testController',
        ]);
    }
}
