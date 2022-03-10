<?php

namespace App\Controller\Front\TestTaker;
use App\Entity\Abonnement;
use App\Form\AbonnementType;
use App\Repository\AbonnementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Common\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class AbonnementJsonController extends AbstractController
{
    /**
     * @var AbonnementRepository
     */
    private $repository;
    public function __construct(AbonnementRepository $repository){
        $this->repository=$repository;
    }
    /**
     * @Route("/front/test/taker/abonnement/json", name="front_test_taker_abonnement_json")
     */
    public function index(): Response
    {
        return $this->render('front/test_taker/abonnement_json/index.html.twig', [
            'controller_name' => 'AbonnementJsonController',
        ]);
    }
    /**
     * @Route("/allabonnementsJson", name="all_abonnementsJson")
     */
    public function all(NormalizerInterface $normalizer)
    {
        $abonnements=$this->repository->findAll();
        $jsonContent=$normalizer->normalize($abonnements,'json',['groups'=>'post:read']);
        return $this->render('back/abonnementsJson.html.twig', [
            'data' => $jsonContent,
        ]);
    }
    /**
     * @Route("/addJson", name="ajouterAbonnementJson")
     */
    public function ajouter(Request $request,EntityManagerInterface $em,NormalizerInterface $normalizer){
        $abonnement=new Abonnement();
        $abonnement->setCout($request->get('cout'));
        $abonnement->setDescription($request->get('description'));
        $abonnement->setNom($request->get('nom'));
        $abonnement->setType($request->get('type_id'));
        $em->persist($abonnement);
        $em->flush();
        $jsonContent=$normalizer->normalize($abonnement,'json',['groups'=>'post:read']);
            return new Response(json_encode($jsonContent));
    }
    /**
     * @Route("/abonnementsJson/{id}", name="admin_abonnement_editJson")
     * @param Abonnement $abonnements
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function editer(Abonnement $abonnements,Request $request,$id,EntityManagerInterface $em,AbonnementRepository $abo,NormalizerInterface $normalizer){
        $abonnement = $abo->find($id);
        $abonnement->setCout($request->get('cout'));
        $abonnement->setDescription($request->get('description'));
        $abonnement->setNom($request->get('nom'));
        $abonnement->setType($request->get('type_id'));
        $em->flush();
        $jsonContent=$normalizer->normalize($abonnement,'json',['groups'=>'post:read']);
        return new Response("Abo modifié avec succès".json_encode($jsonContent));

    }
    /**
     * @Route("/removeJson/{id}", name="SupprimerAbonnementJson")
     */
    public function supprimer($id,AbonnementRepository $abonnements,EntityManagerInterface $em,NormalizerInterface $normalizer){
        $abonnements= $abonnements->find($id);
        $em->remove($abonnements);
        $em->flush();
        $jsonContent=$normalizer->normalize($abonnements,'json',['groups'=>'post:read']);
        return new Response("Abo supprimé avec succès".json_encode($jsonContent));

    }
}
