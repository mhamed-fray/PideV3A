<?php

namespace App\Controller\Front\TestTaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Repository\QuestionRepository;
use App\Repository\BibliothequeRepository;
use App\Repository\ChoixRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use App\Form\QuestionType;
use App\Form\ChoixType;
use App\Entity\Question;
use App\Entity\Choix;
use phpDocumentor\Reflection\Types\Boolean;



class questionnaireController extends AbstractController
{
    /**
     * @Route("/front/test/taker/questionnaire", name="front_test_taker_questionnaire")
     */
    public function index(): Response
    {
        return $this->render('front/test_taker/questionnaire/index.html.twig', [
            'controller_name' => 'questionnaireController',
        ]);
    }



    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testtaker/questionnaire", name="questionnaire")
     */
    public function list_question(QuestionRepository $repository_question,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        
        
        $questions = $repository_question->findAll();
        
        
        

        
        

        
    
        return $this->render('front\test_taker\questionnaire\questionnaire.html.twig', [
            'allquestions'=> $questions
        ]);
    }


}
