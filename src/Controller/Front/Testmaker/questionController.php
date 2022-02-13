<?php

namespace App\Controller\Front\Testmaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class questionController extends AbstractController
{
    /**
     * @Route("/front/testmaker/question", name="front_testmaker_question")
     */
    public function index(): Response
    {
        return $this->render('front/testmaker/question/index.html.twig', [
            'controller_name' => 'questionController',
        ]);
    }
    /**
     * @Route("/allquestions", name="all_questions")
     */
    public function all(): Response
    {
        return $this->render('back/questions.html.twig', [
            'controller_name' => 'questionController',
        ]);
    }
}
