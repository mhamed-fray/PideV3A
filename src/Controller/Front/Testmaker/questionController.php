<?php

namespace App\Controller\Front\Testmaker;

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////**//////////////////////////////////////////////


     /*
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testmaker/allquestions", name="all_questions")
     */
   
    /*public function list_questions(QuestionRepository $repository_question,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        
        $Question = new question();
        $questions = $repository_question->findAll();
        
        $bibs = [1,5,7,8,6,7];

        $form = $this->createForm(QuestionType::class,$Question);
        
        $form->handleRequest($request);

        if($form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($Question);
            $em->flush();
            return $this->redirectToRoute('all_choix',['id_question' => $Question->getId()]);
        }
    
        return $this->render('front\testmaker\question\allquestions.html.twig', [
            'form' =>$form->createView(), 'list' => $bibs
        ]);
    }

    */



    ///////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////


    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testmaker/allquestions", name="all_question")
     */
    public function list_question(QuestionRepository $repository_question,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        
        $Question = new question();
        $questions = $repository_question->findAll();
        
        $bibs = $repository_bibliotheque->findAll();
        $bib_questions = $bibs[0]->getQuestions();

        $form = $this->createForm(QuestionType::class,$Question);
        
        $form->handleRequest($request);
        

        if($form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            
            
        
            return $this->redirectToRoute('all_choix',['contenu_question' => $Question->getContenu()]);
        }
    
        return $this->render('front\testmaker\question\allquestions.html.twig', [
            'form' =>$form->createView(), 'allquestions'=> $questions, 'list' => $bib_questions
        ]);
    }




/////////////////////////////////////////////////////////
///////////////////select a question///////////////////
//////////////////////////////////////////////////////

    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testmaker/allquestions/{id}", name="question_selected")
     */
    
    public function question_selected(QuestionRepository $repository_question,$id,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $selected_question = $repository_question->find($id);
        $Question = new question();
        $Question->setContenu($selected_question->getContenu()); 
        $questions = $repository_question->findAll();
        
        
        $bibs = $repository_bibliotheque->findAll();
        $bib_questions = $bibs[0]->getQuestions();

        $form = $this->createForm(QuestionType::class,$Question);
        

        $form->handleRequest($request);
        
        

        if($form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($Question);
            $em->flush();
            return $this->redirectToRoute('all_choix',['id_question' => $Question->getId()]);
        }
    
        return $this->render('front\testmaker\question\questionselected.html.twig', [
            'form' =>$form->createView(), 'allquestions'=> $questions, 'list' => $bib_questions, 'selected' => $selected_question,
        ]);
    }


    /////////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
    


    


    /**
     * @Route("front/testmaker/question/editquestion/{id}", name="edit_question")
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
            return $this->redirectToRoute('all_questions');
        }

        return $this->render('front\testmaker\question\editquestion.html.twig',[

            'form' =>$form->createView()
        ]);
    }

    


    /**
     * @Route("front/testmaker/question/addquestion", name="add_question")
     */
    public function add_question(Request $request) {

        $Question = new question();

        $form = $this->createForm(QuestionType::class,$Question);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($Question);
            $em->flush();
            return $this->redirectToRoute('all_questions');
        }
        return $this->render('front\testmaker\question\addquestion.html.twig', ['form' => $form->createView()]);

        }





    /**
    * @Route("front/testmaker/question/deletequestion/{id}", name="delete_question")
    */
    public function delete_question($id)
    {

        $em = $this->getDoctrine()->getManager();
        $Question = $this->getDoctrine()->getRepository(Question::class)->find($id);
        $em->remove($Question);
        $em->flush();
        return $this->redirectToRoute("all_questions");

    }






    ///////////////////////////////////////////
    ///////////////choix//////////////////////
    /////////////////////////////////////////




    /**
     * @param ChoixRepository $repository
     *  @param QuestionRepository $repository
     * @Route("/front/testmaker/question/allchoix/{contenu_question}/{etat_question}/{grade}", name="all_choix")
     */
    /*public function list_choix(ChoixRepository $repository_choix,$contenu_question,$etat_question,$grade,Request $request,QuestionRepository $repository_question ): Response
    {

        
        $test_replacement = $repository_question->findAll();
        $question = new question();
        
        $Choix_2= new choix();
        $Choix_1= new choix();
        $Choix_3= new choix();


        $form_choix_2 = $this->createForm(ChoixType::class,$Choix_2);
        $form_choix_2->add('ajouter',SubmitType::class);
        $form_choix_2->handleRequest($request);

        $form_choix_1 = $this->createForm(ChoixType::class,$Choix_1);
        $form_choix_1->add('ajouter',SubmitType::class);
        $form_choix_1->handleRequest($request);

        $form_choix_3 = $this->createForm(ChoixType::class,$Choix_3);
        $form_choix_3->add('ajouter',SubmitType::class);
        $form_choix_3->handleRequest($request);


        $question->setContenu($contenu_question);
        

        $form_question = $this->createForm(QuestionType::class, $question);
        $form_question->add('ajouter',SubmitType::class);
        $form_question->handleRequest($request);


        
        if($form_choix_2->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_2->setQuestion($question);
            $em->persist($Choix_2);
        }
        if($form_choix_1->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_1->setQuestion($question);
            $em->persist($Choix_1);
        }
        if($form_choix_3->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_3->setQuestion($question);
            $em->persist($Choix_3);
        }



        if($form_question->isSubmitted() && $form_question->isValid() )
        {
            
            $em = $this->getDoctrine()->getManager();

           

            

            $em->persist($question);
            
            $em->flush();
            
        }

        return $this->render('front\testmaker\question\allchoix.html.twig', [
            'test_replacement'=>$test_replacement ,'form_question'=>$form_question->createView(),'form_choix_1'=>$form_choix_1->createView(),'form_choix_2'=>$form_choix_2->createView(),'form_choix_3'=>$form_choix_3->createView(),
        ]);
    }*/




    /**
     * @param ChoixRepository $repository
     *  @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testmaker/question/allchoix/{contenu_question}", name="all_choix")
     */
    public function add_choix1(BibliothequeRepository $repository_bibliotheque,ChoixRepository $repository_choix,$contenu_question,Request $request,QuestionRepository $repository_question ): Response
    {

        $bibs = $repository_bibliotheque->findAll();
        $test_replacement = $repository_question->findAll();
        $question = new question();
        
       
        $Choix_1= new choix();

        $form_choix_1 = $this->createForm(ChoixType::class,$Choix_1);
    
        $form_choix_1->handleRequest($request);


        $question->setContenu($contenu_question);
        

        $form_question = $this->createForm(QuestionType::class, $question);
        
        $form_question->handleRequest($request);

        $question->setBibliotheque($bibs[0]);

        if($form_choix_1->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_1->setQuestion($question);
            $em->persist($Choix_1);
        }
    



        if($form_question->isSubmitted() && $form_question->isValid() )
        {
            
            $em = $this->getDoctrine()->getManager();
            $em->persist($question);
            $em->flush();

            return $this->redirectToRoute("add_choix2",['id_question' => $question->getId()]);
            
        }

        return $this->render('front\testmaker\question\allchoix.html.twig', [
            'test_replacement'=>$test_replacement ,'form_question'=>$form_question->createView(),'form_choix_1'=>$form_choix_1->createView(),
        ]);
    }




    /**
     * @param ChoixRepository $repository
     *  @param QuestionRepository $repository
     * @Route("/front/testmaker/question/addchoix2/{id_question}", name="add_choix2")
     */
    public function add_choix2(ChoixRepository $repository_choix,$id_question,Request $request,QuestionRepository $repository_question ): Response
    {

        
        $test_replacement = $repository_question->findAll();
        $question = $repository_question->find($id_question);
        
        $Choix_2= new choix();
        

        $form_choix_2 = $this->createForm(ChoixType::class,$Choix_2);
        $form_choix_2->add('Ajouter',SubmitType::class);
        $form_choix_2->handleRequest($request);


        $question->setContenu($question->getContenu());
        

        
        if($form_choix_2->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_2->setQuestion($question);
            $em->persist($Choix_2);

            $em->flush();

            return $this->redirectToRoute("add_choix3",['id_question' => $question->getId()]);

        }

        return $this->render('front\testmaker\question\deuxiemechoix.html.twig', [
            'test_replacement'=>$test_replacement,'form_choix_2'=>$form_choix_2->createView(),'question'=>$question,
        ]);
    }


    /**
     * @param ChoixRepository $repository
     *  @param QuestionRepository $repository
     * @Route("/front/testmaker/question/addchoix3/{id_question}", name="add_choix3")
     */
    public function add_choix3(ChoixRepository $repository_choix,$id_question,Request $request,QuestionRepository $repository_question ): Response
    {

        
        $test_replacement = $repository_question->findAll();
        $question = $repository_question->find($id_question);
        
        $Choix_3= new choix();
        

        $form_choix_3 = $this->createForm(ChoixType::class,$Choix_3);
        $form_choix_3->add('Ajouter',SubmitType::class);
        $form_choix_3->handleRequest($request);


        $question->setContenu($question->getContenu());
        

        
        if($form_choix_3->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_3->setQuestion($question);
            $em->persist($Choix_3);

            $em->flush();

            return $this->redirectToRoute("all_question");

        }

        return $this->render('front\testmaker\question\troisiemechoix.html.twig', [
            'test_replacement'=>$test_replacement,'form_choix_3'=>$form_choix_3->createView(),'question'=>$question,
        ]);
    }

        

        /*
        
            $Choix_1= new choix();

        $form_choix_1 = $this->createForm(ChoixType::class,$Choix_1);
        
        $form_choix_1->handleRequest($request);
        $form_choix_1->add('ajouter',SubmitType::class);
        if($form_choix_1->isSubmitted()){
            
            $em = $this->getDoctrine()->getManager();
            $em->persist($Choix_1);

            

            $em->flush();
            
        }
        


        
        $Choix_2= new choix();

        $form_choix_2 = $this->createForm(ChoixType::class,$Choix_2);
        $form_choix_2->handleRequest($request);
        $form_choix_2->add('ajouter',SubmitType::class);
        if($form_choix_2->isSubmitted()){
            
            $em = $this->getDoctrine()->getManager();
            $em->persist($Choix_2);
            $em->flush();
            
        }


        $Choix_3= new choix();

        $form_choix_3 = $this->createForm(ChoixType::class,$Choix_3);
        $form_choix_3->handleRequest($request);
        $form_choix_3->add('ajouter',SubmitType::class);
        if($form_choix_3->isSubmitted()){
            
            $em = $this->getDoctrine()->getManager();
            $em->persist($Choix_3);
            $em->flush();
        }
            */
        


        



    

    /**
     * @Route("front/testmaker/question/addchoix", name="add_choix")
     */
    public function add_choix(Request $request) {

        $Choix= new choix();

        $form = $this->createForm(ChoixType::class,$Choix);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);

        if($form->isSubmitted()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($Choix);
            $em->flush();
            return $this->redirectToRoute('all_choix');
        }
        return $this->render('front\testmaker\question\addchoix.html.twig', ['form_choix' => $form->createView()]);

        }


    /**
     * @Route("front/testmaker/question/editchoix/{id}", name="edit_choix")
     */
    public function edit_choix(ChoixRepository $repository,$id, Request $request ):response
    {
        $Choix=$repository->find($id);

        $form=$this->createForm(ChoixType::class, $Choix);
        $form->add('update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() )
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('all_choix');
        }
        return $this->render('front\testmaker\question\editchoix.html.twig',[

            'form_choix' =>$form->createView()
        ]);
    }



    ///////////////////////////////////////////
    ///////////////bibliotheque//////////////////////
    /////////////////////////////////////////







    /**
    * @Route("front/testmaker/bib/deletequestion/{id_bib_question}", name="delete_biblio")
    */
    public function delete_bib_question($id_bib_question)
    {

        $em = $this->getDoctrine()->getManager();
        $Question = $this->getDoctrine()->getRepository(Question::class)->find($id_bib_question);
        $em->remove($Question);
        $em->flush();
        return $this->redirectToRoute("all_question");

    }


    


    
}
