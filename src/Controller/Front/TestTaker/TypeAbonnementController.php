<?php

namespace App\Controller\Front\TestTaker;

use App\Entity\Type;
use App\Form\TypeAboType;
use App\Repository\TypeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Common\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TypeAbonnementController extends AbstractController
{
    /**
     * @var TypeRepository
     */
    private $repository;
    public function __construct(TypeRepository $repository){
        $this->repository=$repository;
    }
    /**
     * @Route("/front/test/taker/type/abonnement", name="front_test_taker_type_abonnement")
     */
    /**
    public function index(): Response
    {
        return $this->render('front/test_taker/type_abonnement/index.html.twig', [
            'controller_name' => 'TypeAbonnementController',
        ]);
    }*/
    /**
     * @Route("/allTypeAbo", name="all_Typeabonnements")
     */
    public function all(): Response
    {
        $Typeabonnement=$this->repository->findAll();
        return $this->render('back/Typeabonnement.html.twig', [
            'Typeabonnement' => $Typeabonnement,
        ]);
    }
    /**
     * @Route("/Typeabonnement/{id}", name="admin_Typeabonnement_edit")
     * @param Type $type
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function editer(Type $type,Request $request,$id,EntityManagerInterface $em,TypeRepository $tr){
        $Typeabonnement = $tr->find($id);
        $form=$this->createForm(TypeAboType::class,$Typeabonnement);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em->flush();
            $this->addFlash('success','Type Abonnement modifié avec succès!');
            return $this->redirectToRoute('all_Typeabonnements');
        }
        return $this->render('back/admin/EditTypeabonnements.html.twig', [
            'form'=>$form->createView()]);
    }
    /**
     * @Route("/removeType/{id}", name="SupprimerTypeAbonnement")
     */
    public function supprimer($id,TypeRepository $tr,EntityManagerInterface $em){
        $tr= $tr->find($id);
        $em->remove($tr);
        $em->flush();
        $this->addFlash('success','Type Abonnement supprimé avec succès!');

        return $this->redirectToRoute('all_Typeabonnements');
    }
    /**
     * @Route("/addType", name="ajouterTypeAbonnement")
     */
    public function ajouter(Request $request,EntityManagerInterface $em){
        $tr=new Type();
        $form= $this->createForm(TypeAboType::class,$tr);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em->persist($tr);
            $em->flush();
            $this->addFlash('success','Type Abonnement ajouté avec succès!');
            return $this->redirectToRoute('all_Typeabonnements');
        }
        return $this->render("back/admin/AjouterTypeabonnements.html.twig",array('form'=>$form->createView()));
    }
}
