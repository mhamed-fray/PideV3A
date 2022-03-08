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
use App\Form\QuestionNoteType;
use App\Form\QuizType;
use App\Form\ChoixType;
use App\Entity\Question;
use App\Entity\Choix;
use App\Entity\Quiz;
use App\Repository\TestRepository;
use phpDocumentor\Reflection\Types\Boolean;



class questionnaireController extends AbstractController
{


    /**
     * @Route("/questionnaire", name="test_questionnaire")
     */
    public function exampleAction(Request $request){
        
        $choix_1 = $request->get("choix_1");
        $choix_2 = $request->get("choix_2");
        $choix_3 = $request->get("choix_3");

        return $this->render('front/test_taker/questionnaire/delete.html.twig', [
            'choix_1' => $choix_1,'choix_2' => $choix_2,'choix_3' => $choix_3,
        ]);
    
    }


    /**
     * @Route("/front/test/taker/questionnaire", name="front_test_taker_questionnaire")
     */
    
    public function index(): Response
    {
        return $this->render('front/test_taker/questionnaire/index.html.twig', [
            'controller_name' => 'questionnaireController',
        ]);
    }




    /*
    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testtaker/questionnaire", name="questionnaire")
     */
    /*
    public function list_question(QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 1;
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();


        

        $quiz = new Quiz();


        $form = $this->createForm(QuizType::class,$quiz);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($quiz);
            $em->flush();
            
        }

        $lien_twig= 'front\test_taker\questionnaire\questionnaire.html.twig';
       
        
        return $this->render($lien_twig, [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView()
        ]);
    }
    */


    /*
    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire1", name="questionnaire1")
     */
    
    /*public function list_question1(ChoixRepository $repository_choix,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 5;
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        
        for($i=0 ; $i<4 ; $i++)
        {
            $form=$this->createForm(QuestionNoteType::class, $questions_test[$i],array('id' => $questions_test[$i]->getId()));
            $form->add('update',SubmitType::class);
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('questionnaire2');
            }   
        } 
            
        
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
                'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[0], 'test_d'=> $test_id
            ]);
    } 
*/

    


/*
    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire2", name="questionnaire2")
     */
    
    /*public function list_question2(ChoixRepository $repository_choix,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 5;
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        
        
            $form=$this->createForm(QuestionNoteType::class, $questions_test[1],array('id' => 20));
            $form->add('update',SubmitType::class);
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('questionnaire3');
                
            }
            return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
                'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'test_d'=> $test_id,'question' =>$questions_test[1]
            ]);
        

    } */ 



/*
    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire3", name="questionnaire3")
     */
    
    /*public function list_question3(ChoixRepository $repository_choix,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 5;
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        
        
            $form=$this->createForm(QuestionNoteType::class, $questions_test[2],array('id' => 21));
            $form->add('update',SubmitType::class);
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('questionnaire4');
                
            }
            return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
                'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'test_d'=> $test_id,'question' =>$questions_test[2]
            ]);
        
    } */



/*
    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire4", name="questionnaire4")
     */
    
    /*public function list_question4(ChoixRepository $repository_choix,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 5;
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        
        
            $form=$this->createForm(QuestionNoteType::class, $questions_test[3],array('id' => 22));
            $form->add('update',SubmitType::class);
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('calcul_note');
                
            }
            return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
                'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'test_d'=> $test_id,'question' =>$questions_test[3]
            ]);
        

    }  */



    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire1/{id_cand}", name="questionnaire1")
     */
    public function questionnaire1(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[0]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('questionnaire2',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[0], 'test_d'=> $test_id
        ]);

    }




    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire2/{id_cand}", name="questionnaire2")
     */
    public function questionnaire2(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

       
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[1]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('questionnaire3',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[1], 'test_d'=> $test_id
        ]);

    }




    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire3/{id_cand}", name="questionnaire3")
     */
    public function questionnaire3(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[2]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('questionnaire4',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[2], 'test_d'=> $test_id
        ]);

    }




    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire4/{id_cand}", name="questionnaire4")
     */
    public function questionnaire4(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[3]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('questionnaire5',['id_cand' => $id_cand]);

                

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[3], 'test_d'=> $test_id
        ]);

    }





    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire5/{id_cand}", name="questionnaire5")
     */
    public function questionnaire5(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[4]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                return $this->redirectToRoute('calcul_note',['id_cand' => $id_cand]);

                //return $this->redirectToRoute('questionnaire6',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[4], 'test_d'=> $test_id
        ]);

    }





    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire6/{id_cand}", name="questionnaire6")
     */
    public function questionnaire6(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[5]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                //return $this->redirectToRoute('questionnaire7',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[5], 'test_d'=> $test_id
        ]);

    }



    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire7/{id_cand}", name="questionnaire7")
     */
    public function questionnaire7(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[6]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                //return $this->redirectToRoute('questionnaire8',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[6], 'test_d'=> $test_id
        ]);

    }



    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire8/{id_cand}", name="questionnaire8")
     */
    public function questionnaire8(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[7]->getId()));
           
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                //return $this->redirectToRoute('questionnaire9',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[7], 'test_d'=> $test_id
        ]);

    }



    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire9/{id_cand}", name="questionnaire9")
     */
    public function questionnaire9(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[8]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

                //return $this->redirectToRoute('questionnaire10',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[8], 'test_d'=> $test_id
        ]);

    }
    


    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/questionnaire10/{id_cand}", name="questionnaire10")
     */
    public function questionnaire10(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $id = 8;
        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();

        $quiz = new Quiz();

        
        
            $form=$this->createForm(QuizType::class, $quiz,array('id' => $questions_test[9]->getId()));
            
            $form->handleRequest($request);
            if($form->isSubmitted() && $form->isValid() )
            {
                $choix = $form->get('choix')->getData();
                $em=$this->getDoctrine()->getManager();
            
                
                $c_list = $choix->toArray();
                
                foreach($c_list as $c){

                    $choix_id = $repository_choix->find($c->getId());
                    $choix_id->setChecked(true);
                    
                }
               
                $em->flush();

               // return $this->redirectToRoute('calcul_note',['id_cand' => $id_cand]);

                
                
            }
        return $this->render('front\test_taker\questionnaire\dixchoix\quiz.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,'form' => $form->createView(),'question' =>$questions_test[9], 'test_d'=> $test_id
        ]);

    }






    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param ChoixRepository $repository
     * @Route("/front/testtaker/calculnote/{id_cand}", name="calcul_note")
     */
    
    public function calcul_note(ChoixRepository $repository_choix,$id_cand,QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        
        $note = 0;

        $test_id = $repository_test->find($id_cand);
        $questions_test = $repository_question->findBy(array('test' => $test_id));


        
        foreach($questions_test as $each_question)
        {
            $note_count = 0;

            foreach($each_question->getChoix() as $each_question_choix)
            {
                if($each_question_choix->getEtatChoix() == 1 && $each_question_choix->getChecked() == 1)
                {
                    if($note_count == 2)
                    $note_count = 0;
                    else
                    $note_count = 1;

                }
                elseif($each_question_choix->getEtatChoix() == 0 && $each_question_choix->getChecked() == 1)
                {
                    $note_count = 2;
                }
                
            }
            if($note_count == 1)
                $note = $note + 1;
        }
        
        
        
            




            return $this->render('front\test_taker\questionnaire\calculnote.html.twig', [
                'test_questions' => $questions_test,'note' => $note
            ]);
        

    }

    
    




    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testtaker/questionnaire", name="questionnaire")
     */
    /*
    public function list_question(QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $output = $request->request->get('output');
        $id = 5;
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();
       
        
   
    
        return $this->render('front\test_taker\questionnaire\questionnaire.html.twig', [
            'allquestions'=> $questions,'test' => $questions_test,
        ]);
    }
*/

    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/note", name="note")
     */
    /*
    public function nooote(QuestionRepository $repository_question,TestRepository $repository_test,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $output = $request->request->get('output');
        $id = 5;
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $test_id));
        $questions = $repository_question->findAll();
       
        
   
    
        return $this->render('front\test_taker\questionnaire\delete.html.twig', [
            'note' => $output,
        ]);
    }
    */





   

    


}
