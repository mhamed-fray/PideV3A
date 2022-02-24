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


class choixController extends AbstractController
{
    /**
     * @Route("/back/choix", name="back_choix")
     */
    public function index(): Response
    {
        return $this->render('back/choix/index.html.twig', [
            'controller_name' => 'choixController',
        ]);
    }



    /**
     * @param ChoixRepository $repository
     * @Route("/back/choix/allchoix", name="back_all_choix")
     */
    public function list_choix(ChoixRepository $repository): Response
    {

        $choix = $repository->findAll();
    
        return $this->render('Back\choix.html.twig', [
            'allchoix' => $choix,
        ]);
    }



    /**
    * @Route("back/choix/deletechoix/{id}", name="back_delete_choix")
    */
    public function delete_choix($id)
    {

        $em = $this->getDoctrine()->getManager();
        $Choix = $this->getDoctrine()->getRepository(Choix::class)->find($id);
        $em->remove($Choix);
        $em->flush();
        return $this->redirectToRoute("all_choix");

    }


    /**
     * @Route("back/choix/addchoix", name="back_add_choix")
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
        return $this->render('Back/choix/addchoix.html.twig', ['form_choix' => $form->createView()]);

        }


    /**
     * @Route("back/choix/editchoix/{id}", name="back_edit_choix")
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
        return $this->render('Back/choix/editchoix.html.twig',[

            'form_choix' =>$form->createView()
        ]);
    }
}
