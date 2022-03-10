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
}
