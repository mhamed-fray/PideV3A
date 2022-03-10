<?php

namespace App\Controller\Front\Testmaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class bibliothequeController extends AbstractController
{
    /**
     * @Route("/front/testmaker/bibliotheque", name="front_testmaker_bibliotheque")
     */
    public function index(): Response
    {
        return $this->render('front/testmaker/bibliotheque/index.html.twig', [
            'controller_name' => 'bibliothequeController',
        ]);
    }



    






}
