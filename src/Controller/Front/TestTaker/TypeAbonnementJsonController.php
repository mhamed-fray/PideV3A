<?php

namespace App\Controller\Front\TestTaker;

use App\Entity\Abonnement;
use App\Entity\Type;
use App\Repository\AbonnementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\TypeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Common\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class TypeAbonnementJsonController extends AbstractController
{
    /**
     * @var TypeRepository
     */
    private $repository;
    public function __construct(TypeRepository $repository){
        $this->repository=$repository;
    }
    /**
     * @Route("/front/test/taker/type/abonnement/json", name="front_test_taker_type_abonnement_json")
     */
    public function index(): Response
    {
        return $this->render('front/test_taker/type_abonnement_json/index.html.twig', [
            'controller_name' => 'TypeAbonnementJsonController',
        ]);
    }
    /**
     * @Route("/allTypeabonnementsJson", name="all_typeabonnementsJson")
     */
    public function all(NormalizerInterface $normalizer)
    {
        $Typeabonnement=$this->repository->findAll();
        $jsonContent=$normalizer->normalize($Typeabonnement,'json',['groups'=>'post:read']);
        return $this->render('back/TypeabonnementsJson.html.twig', [
            'data' => $jsonContent,
        ]);
    }
    /**
     * @Route("/addTypeJson", name="ajoutertypeAbonnementJson")
     */
    public function ajouter(Request $request,EntityManagerInterface $em,NormalizerInterface $normalizer){
        $tr=new Type();
        $tr->setType($request->get('type'));
        $em->persist($tr);
        $em->flush();
        $jsonContent=$normalizer->normalize($tr,'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }
    /**
     * @Route("/typeabonnementsJson/{id}", name="admin_typeabonnement_editJson")
     * @param Type $type
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function editer(Type $type,Request $request,$id,EntityManagerInterface $em,TypeRepository $tr,NormalizerInterface $normalizer){
        $Typeabonnement = $tr->find($id);
        $Typeabonnement->setType($request->get('type'));
        $em->flush();
        $jsonContent=$normalizer->normalize($Typeabonnement,'json',['groups'=>'post:read']);
        return new Response("type modifié avec succès".json_encode($jsonContent));

    }
    /**
     * @Route("/removetypeJson/{id}", name="SupprimertypeAbonnementJson")
     */
    public function supprimer($id,TypeRepository $tr,EntityManagerInterface $em,NormalizerInterface $normalizer){
        $tr= $tr->find($id);
        $em->remove($tr);
        $em->flush();
        $jsonContent=$normalizer->normalize($tr,'json',['groups'=>'post:read']);
        return new Response("type supprimé avec succès".json_encode($jsonContent));

    }
}
