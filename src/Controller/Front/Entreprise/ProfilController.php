<?php

namespace App\Controller\Front\Entreprise;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ProfilController extends AbstractController
{
    /**
     * @Route("/front/monprofil", name="front_entreprise_profil")
     */
    public function index(): Response
    {
        return $this->render('front/entreprise/profil/index.html.twig', [
            'controller_name' => 'ProfilController',
        ]);
    }

    /**
     * @Route("/user", name="users")
     */
    public function users(): Response
    {
        return $this->render('back/pages-user-profile.html.twig', [
            'controller_name' => 'ProfilController',
        ]);
    }

    
   
}
