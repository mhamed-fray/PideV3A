<?php

namespace App\Controller\Back;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Repository\QuestionRepository;
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



class questionController extends AbstractController
{
    /**
     * @Route("/back/question", name="back_question")
     */
    public function index(): Response
    {
        return $this->render('back/question/index.html.twig', [
            'controller_name' => 'questionController',
        ]);
    }


    /**
     * @param QuestionRepository $repository
     * @Route("/back/question/allquestions", name="back_all_questions")
     */
    public function all(QuestionRepository $repository): Response
    {

        $questions = $repository->findAll();
    

        return $this->render('back/questions.html.twig', [
            'allquestions' => $questions,
        ]);
    }


    /**
     * @Route("back/question/editquestion/{id}", name="back_edit_question")
     */
    public function edit_questions(QuestionRepository $repository,$id, Request $request ):response
    {
        $Question=$repository->find($id);

        $form=$this->createForm(QuestionType::class, $Question);
        $form->add('update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() )
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('back_all_questions');
        }
        return $this->render('back/question/editquestion.html.twig',[

            'form' =>$form->createView()
        ]);
    }

    /**
    * @Route("back/question/deletequestion/{id}", name="back_delete_question")
    */
    public function delete_question($id)
    {

        $em = $this->getDoctrine()->getManager();
        $Question = $this->getDoctrine()->getRepository(Question::class)->find($id);
        $em->remove($Question);
        $em->flush();
        return $this->redirectToRoute("back_all_questions");

    }


}
