<?php

namespace App\Controller\Front\Testmaker;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Repository\QuestionRepository;
use App\Repository\BibliothequeRepository;
use App\Repository\TestRepository;
use App\Repository\ChoixRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use App\Form\QuestionType;
use App\Form\choices\ChoixType;
use App\Form\choices\Choix2Type;
use App\Form\choices\Choix3Type;
use App\Entity\Question;
use App\Entity\Choix;
use phpDocumentor\Reflection\Types\Boolean;
use Symfony\Component\Validator\Constraints\Length;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Form\FormError;
use Symfony\Component\OptionsResolver\OptionsResolver;

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
     * @param TestRepository $repository
     * @Route("/front/testmaker/allquestions/{id}/{favoris}", name="all_question", defaults={"favoris": 0})
     */
    public function add_questions(TestRepository $repository_test,QuestionRepository $repository_question,$favoris,$id,Request $request,BibliothequeRepository $repository_bibliotheque,PaginatorInterface $paginator): Response
    {

        
        $Question = new question();
        $questions = $repository_question->findAll();
        $questions_test = $repository_question->findBy(array('test' => $id));
        
        $question_length = count($questions_test);
        
        $test_id = $repository_test->find($id);
        
        $bibs = $repository_bibliotheque->findAll();
        $bib_questions = $bibs[0]->getQuestions();

        $bib_questions = $paginator->paginate(
            $bib_questions, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            20/*limit per page*/);

        $form = $this->createForm(QuestionType::class,$Question);
        
        $form->handleRequest($request);
        

        if($form->isSubmitted() && $form->isValid()){
            $em = $this->getDoctrine()->getManager();

            

            /*
            $dupl_question = $repository_question->findBy(array('contenu' => $Question->getContenu()));

            if($dupl_question)
            {
                $error = new FormError("C'est interdit de dupliqué une question");
                $form->get('contenu')->addError($error);
            }
            else
            {
                return $this->redirectToRoute('all_choix',['contenu_question' => $Question->getContenu(),'id' => $id]);
            }*/
            $control = 0;
            foreach($questions_test as $questions_on_control)
            {
                if($questions_on_control->getContenu() ==  $Question->getContenu())
                    $control = 1;
                else
                    $control = $control + 0;
            }

            
            
            if($control == 1)
            {
                $error = new FormError("C'est interdit de dupliqué une question");
                $form->get('contenu')->addError($error);
            }
            elseif($control == 0)
            {
                return $this->redirectToRoute('all_choix',['contenu_question' => $Question->getContenu(),'id' => $id]);
            }



        
            
        }
    
        return $this->render('front\testmaker\question\allquestions.html.twig', [
            'form' =>$form->createView(), 'allquestions'=> $questions, 'list' => $bib_questions,'test' => $test_id,'number' => $question_length,'favoris'=>$favoris
        ]);
    }



    
////////////////////////////////////////////////////////////////////
///////////////////  list test questions and choices  ///////////////////
////////////////////////////////////////////////////////////////////





    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param TestRepository $repository
     * @Route("/front/testmaker/test/questions/{id}", name="test_all_question")
     */
    public function list_question(TestRepository $repository_test,QuestionRepository $repository_question,$id,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $test_selected = $repository_test->find($id);

        $twig_page = 'front\testmaker\question\testquestions.html.twig';

        
        
    
        return $this->render($twig_page, [
            'test_selected'=> $test_selected,
        ]);
    }




    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param TestRepository $repository
     * @Route("/front/testmaker/test/pdf/{id}", name="pdf")
     */
    public function page_pdf(TestRepository $repository_test,QuestionRepository $repository_question,$id,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        
        
        $test_selected = $repository_test->find($id);

        
        
    
        return $this->render('front\testmaker\question\pdf\listpdf.html.twig', [
            'test_selected'=> $test_selected,
        ]);
    }


    


    /////////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////









////////////////////////////////////////////////////////////////////
///////////////////  list pdf  /////////////////////////////////////
////////////////////////////////////////////////////////////////////





    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param TestRepository $repository
     * @Route("/front/testmaker/test/questions/pdf/{id}", name="listpdf")
     */
    public function list_pdf(TestRepository $repository_test,QuestionRepository $repository_question,$id,Request $request,BibliothequeRepository $repository_bibliotheque)
    {

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $test_selected = $repository_test->find($id);
        
        
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('front\testmaker\question\pdf\listpdf.html.twig', [
            'test_selected'=> $test_selected,
        ]);
        
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
        
        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A3', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("quiz.pdf", [
            "Attachment" => true
        ]);
        
    
        
    }


    


    /////////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////










////////////////////////////////////////////////////////////////////
///////////////////  supprimer et changer question de test  ///////////////////
////////////////////////////////////////////////////////////////////


    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param TestRepository $repository
     * @Route("/front/testmaker/modifier/{id}/{question_id}", name="modifier_question")
     */
    public function modifier_question(TestRepository $repository_test,$question_id,QuestionRepository $repository_question,$id,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $em = $this->getDoctrine()->getManager();
        $Question = $this->getDoctrine()->getRepository(Question::class)->find($question_id);
        $em->remove($Question);
        $em->flush();

        return $this->redirectToRoute('all_question',['id' => $id]);
    }



    


    /////////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////













////////////////////////////////////////////////////////////////////
///////////////////  select a question from library  ///////////////////
////////////////////////////////////////////////////////////////////

    /**
     * @param QuestionRepository $repository
     * @param BibliothequeRepository $repository
     * @param TestRepository $repository
     * @Route("/front/testmaker/selected/{selected_id}/{id}/{favoris}", name="question_selected", defaults={"favoris": 0})
     */
    public function question_selected(PaginatorInterface $paginator,TestRepository $repository_test,QuestionRepository $repository_question,$selected_id,$favoris,$id,Request $request,BibliothequeRepository $repository_bibliotheque): Response
    {

        $selected_question = $repository_question->find($selected_id);
        $Question = new question();
        $Question->setContenu($selected_question->getContenu()); 
        $questions = $repository_question->findAll();
        $questions_test = $repository_question->findBy(array('test' => $id));
        
        $question_length = count($questions_test);
        
        $test_id = $repository_test->find($id);
        
        $bibs = $repository_bibliotheque->findAll();
        $bib_questions = $bibs[0]->getQuestions();

        $bib_questions = $paginator->paginate(
            $bib_questions, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            20/*limit per page*/);

        $form = $this->createForm(QuestionType::class,$Question);
        

        $form->handleRequest($request);


        
        
        

        if($form->isSubmitted() && $form->isValid()){

            $control = 0;
            foreach($questions_test as $questions_on_control)
            {
                if($questions_on_control->getContenu() ==  $Question->getContenu())
                    $control = 1;
                else
                    $control = $control + 0;
            }

            
            
            if($control == 1)
            {
                $error = new FormError("C'est interdit de dupliqué une question");
                $form->get('contenu')->addError($error);
            }
            elseif($control == 0)
            {
                return $this->redirectToRoute('all_choix',['contenu_question' => $Question->getContenu(),'id' => $id]);
            }

              
        }
            


    
        return $this->render('front\testmaker\question\questionselected.html.twig', [
            'form' =>$form->createView(), 'allquestions'=> $questions, 'list' => $bib_questions,'test' => $test_id,'number' => $question_length,'favoris'=>$favoris
        ]);
    }


    /////////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
    



    


    


    /**
     * @Route("front/testmaker/question/editquestion/{id}", name="edit_question")
     */
    public function edit_questions(QuestionRepository $repository,$id, Request $request,OptionsResolver $resolver ):response
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
     * @param TestRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testmaker/question/allchoix/{contenu_question}/{id}", name="all_choix")
     */
    public function add_choix1(TestRepository $repository_test,BibliothequeRepository $repository_bibliotheque,ChoixRepository $repository_choix,$id,$contenu_question,Request $request,QuestionRepository $repository_question ): Response
    {
        $state_control = 0;

        $bibs = $repository_bibliotheque->findAll();
        $test_replacement = $repository_question->findAll();
        
        $test_id = $repository_test->find($id);
        $questions_test = $repository_question->findBy(array('test' => $id));
        $question = new question();
        
        
       
        $Choix_1 = new choix();
        $choix_2 = new choix();
        $choix_3 = new choix();



        $form_choix_1 = $this->createForm(ChoixType::class,$Choix_1);
        $form_choix_1->handleRequest($request);

        $form_choix_2 = $this->createForm(Choix2Type::class,$choix_2);
        $form_choix_2->handleRequest($request);

        $form_choix_3 = $this->createForm(Choix3Type::class,$choix_3);
        $form_choix_3->handleRequest($request);
        


        $question->setContenu($contenu_question);
        

        $form_question = $this->createForm(QuestionType::class, $question);
        
        
 
        $form_question->handleRequest($request);

        $question->setBibliotheque($bibs[0]);
        $question->setTest($test_id);


        if($form_choix_1->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_1->setQuestion($question);
            $em->persist($Choix_1);
        }


        if($form_choix_2->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $choix_2->setQuestion($question);
            $em->persist($choix_2);
        }


        if($form_choix_3->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $choix_3->setQuestion($question);
            $em->persist($choix_3);
        }


        if($form_question->isSubmitted() && $form_question->isValid() && $form_choix_1->isValid() && $form_choix_2->isValid() && $form_choix_3->isValid() )
        {

            $choix1_state = $Choix_1->getEtatChoix();
            $choix2_state = $choix_2->getEtatChoix();
            $choix3_state = $choix_3->getEtatChoix();

            if($choix1_state == 0 && $choix2_state == 0 && $choix3_state == 0 )
            {
                $state_control = 1;
                return $this->render('front\testmaker\question\allchoix.html.twig', [
                    'test_replacement'=>$test_replacement,'test' => $test_id ,'form_question'=>$form_question->createView(),'form_choix_1'=>$form_choix_1->createView(),'form_choix_2'=>$form_choix_2->createView(),'form_choix_3'=>$form_choix_3->createView(),'state_control'=>$state_control,
                    ]);
            }


            $control = 0;
            foreach($questions_test as $questions_on_control)
            {
                if($questions_on_control->getContenu() ==  $question->getContenu())
                    $control = 1;
                else
                    $control = $control + 0;
            }

            if($control == 1)
            {
                $error = new FormError("C'est interdit de dupliqué une question");
                $form_question->get('contenu')->addError($error);
            }
            elseif($control == 0)
            {
                $em = $this->getDoctrine()->getManager();
                $em->persist($question);
                $em->flush();

                $this->addFlash(
                    'info',
                    'question ajouter'
                );

                return $this->redirectToRoute("all_question",['id' => $id]);
            }
            
            
            
        }

        return $this->render('front\testmaker\question\allchoix.html.twig', [
            'test_replacement'=>$test_replacement,'test' => $test_id ,'form_question'=>$form_question->createView(),'form_choix_1'=>$form_choix_1->createView(),'form_choix_2'=>$form_choix_2->createView(),'form_choix_3'=>$form_choix_3->createView(),'state_control'=>$state_control
            ]);
    }


    /**
     * @param ChoixRepository $repository
     *  @param QuestionRepository $repository
     * @param TestRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testmaker/question/favor/{test_id}/{question_favor_id}", name="favor")
     */
    public function favori(TestRepository $repository_test,QuestionRepository $repository_question,$question_favor_id,$test_id) {

        
        $question_to_favorise = $repository_question->find($question_favor_id);
        $test_shared = $repository_test->find($test_id);

        $question_to_favorise->setFavori(true);
        $em = $this->getDoctrine()->getManager();
        $em->flush();

        return $this->redirectToRoute("all_question",['id' => $test_id]);


    }

    /**
     * @param ChoixRepository $repository
     *  @param QuestionRepository $repository
     * @param TestRepository $repository
     * @param BibliothequeRepository $repository
     * @Route("/front/testmaker/question/unfavor/{test_id}/{question_favor_id}", name="unfavor")
     */
    public function unfavori(TestRepository $repository_test,QuestionRepository $repository_question,$question_favor_id,$test_id) {

        
        $question_to_favorise = $repository_question->find($question_favor_id);

        $question_to_favorise->setFavori(false);
        $em = $this->getDoctrine()->getManager();
        $em->flush();

        return $this->redirectToRoute("all_question",['id' => $test_id]);


    }




    /**
     * @param ChoixRepository $repository
     * @param TestRepository $repository
     *  @param QuestionRepository $repository
     * @Route("/front/testmaker/question/addchoix2/{id_question}/{id}", name="add_choix2")
     */
    /*
    public function add_choix2(TestRepository $repository_test,ChoixRepository $repository_choix,$id,$id_question,Request $request,QuestionRepository $repository_question ): Response
    {

        
        $test_replacement = $repository_question->findAll();
        $question = $repository_question->find($id_question);
        $test_id = $repository_test->find($id);
        
        
        $Choix_2= new choix();
        

        $form_choix_2 = $this->createForm(ChoixType::class,$Choix_2);
        $form_choix_2->add('Ajouter',SubmitType::class);
        $form_choix_2->handleRequest($request);


        $question->setContenu($question->getContenu());
        $question->setTest($test_id);
        

        
        if($form_choix_2->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_2->setQuestion($question);
            $em->persist($Choix_2);

            $em->flush();

            return $this->redirectToRoute("add_choix3",['id_question' => $question->getId(),'id' => $id]);

        }

        return $this->render('front\testmaker\question\deuxiemechoix.html.twig', [
            'test_replacement'=>$test_replacement,'form_choix_2'=>$form_choix_2->createView(),'question'=>$question,'test' => $test_id
        ]);
    }

    */


    /**
     * @param ChoixRepository $repository
     *  @param QuestionRepository $repository
     * @param TestRepository $repository
     * @Route("/front/testmaker/question/addchoix3/{id_question}/{id}", name="add_choix3")
     */
    /*
    public function add_choix3(TestRepository $repository_test,ChoixRepository $repository_choix,$id_question,$id,Request $request,QuestionRepository $repository_question ): Response
    {

        
        $test_replacement = $repository_question->findAll();
        $question = $repository_question->find($id_question);
        $test_id = $repository_test->find($id);
        
        $Choix_3= new choix();
        

        $form_choix_3 = $this->createForm(ChoixType::class,$Choix_3);
        $form_choix_3->add('Ajouter',SubmitType::class);
        $form_choix_3->handleRequest($request);


        $question->setContenu($question->getContenu());
        $question->setTest($test_id);
        

        
        if($form_choix_3->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $Choix_3->setQuestion($question);
            $em->persist($Choix_3);
            $em->flush();

            return $this->redirectToRoute("all_question",['id' => $id]);

        }

        return $this->render('front\testmaker\question\troisiemechoix.html.twig', [
            'test_replacement'=>$test_replacement,'form_choix_3'=>$form_choix_3->createView(),'question'=>$question,
        ]);
    }

    */

        

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
    * @Route("front/testmaker/bib/deletequestion/{id_bib_delete_question}/{test_id}", name="delete_biblio")
    */
    public function delete_bib_question($id_bib_delete_question,$test_id)
    {

        $em = $this->getDoctrine()->getManager();
        $Question = $this->getDoctrine()->getRepository(Question::class)->find($id_bib_delete_question);
        $em->remove($Question);
        $em->flush();
        return $this->redirectToRoute("all_question",['id' => $test_id]);

    }


    


    
}
