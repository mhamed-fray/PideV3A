<?php

namespace App\Controller\Front\TestTaker;

use App\Entity\Abonnement;
use App\Entity\Commande;
use App\Entity\User;
use App\Entity\Likes;
use App\Form\AbonnementType;
use App\Repository\AbonnementRepository;
use App\Repository\LikesRepository;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Common\Persistence\ManagerRegistry;
use Stripe\Service\StripeService;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class abonnementController extends AbstractController
{
    /**
     * @var AbonnementRepository
     */
    /**
     * @var StripeService
     */
    protected $stripeService;
    private $repository;
    public function __construct(AbonnementRepository $repository){
        $this->repository=$repository;
    }
    /**
     * @Route("/front/test/taker/abonnement", name="front_test_taker_abonnement")
     */
    public function index(UserRepository $u): Response
    {
        $abonnements=$this->repository->findAll();
        $user=$u->find(1);



        return $this->render('front/test_taker/abonnement/index.html.twig', [
            'abonnements' => $abonnements,'user'=>$user
        ]);
    }
    /**
     * @Route("/allabonnements", name="all_abonnements")
     */
    public function all(): Response
    {
        $abonnements=$this->repository->findAll();
        return $this->render('back/abonnements.html.twig', [
            'abonnements' => $abonnements,
        ]);
    }
    /**
     * @Route("/abonnements/{id}", name="admin_abonnement_edit")
     * @param Abonnement $abonnements
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function editer(Abonnement $abonnements,Request $request,$id,EntityManagerInterface $em,AbonnementRepository $abo){
        $abonnements = $abo->find($id);
        $form=$this->createForm(AbonnementType::class,$abonnements);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em->flush();
            $this->addFlash('success','Abonnement modifié avec succès!');
            return $this->redirectToRoute('all_abonnements');
        }
        return $this->render('back/admin/Editabonnements.html.twig', [
            'form'=>$form->createView()]);
    }
    /**
     * @Route("/remove/{id}", name="SupprimerAbonnement")
     */
    public function supprimer($id,AbonnementRepository $abonnements,EntityManagerInterface $em){
        $abonnements= $abonnements->find($id);
        $em->remove($abonnements);
        $em->flush();
        $this->addFlash('success','Abonnement supprimé avec succès!');

        return $this->redirectToRoute('all_abonnements');
    }
    /**
     * @Route("/add", name="ajouterAbonnement")
     */
    public function ajouter(Request $request,EntityManagerInterface $em){
        $abonnement=new Abonnement();
        $Like=$abonnement->setLikes(1);
        $form= $this->createForm(AbonnementType::class,$abonnement);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em->persist($abonnement);
            $em->flush();
            $this->addFlash('success','Abonnement ajouté avec succès!');
            return $this->redirectToRoute('all_abonnements');
        }
        return $this->render("back/admin/Ajouterabonnements.html.twig",array('form'=>$form->createView()));
    }
    /**
     * @Route("/abonnementsASC", name="all_abonnementsASC")
     * @param AbonnementRepository $repository
     * @return void
     */
    public function Cher(AbonnementRepository $repository): Response
    {
        $abonnements=$repository->OrderByCout();
        return $this->render('front/test_taker/abonnement/index.html.twig',['abonnements' => $abonnements]);
    }


    /**
     * @Route("/abonnementsDSC", name="all_abonnementsDSC")
     * @param AbonnementRepository $repository
     * @return void
     */
    public function MoinsCher(AbonnementRepository $repository): Response
    {
        $abonnements=$repository->OrderByCoutDSC();
        return $this->render('front/test_taker/abonnement/index.html.twig',['abonnements' => $abonnements]);
    }
    /**
     * @Route("/Like/{id}", name="LikeAbonnement")
     */
    /**public function Like($id,AbonnementRepository $abonnements,EntityManagerInterface $em){
        $abonnements= $abonnements->find($id);

        $l=$abonnements->getLikes();
        $l=$l+1;
        $abonnements->setLikes($l);
        $em->persist($abonnements);
        $em->flush();
        return $this->redirectToRoute('front_test_taker_abonnement');
    }*/

    /**
     * @param $ida
     * @param $id
     * @param AbonnementRepository $abonnement
     * @param UserRepository $user
     * @param LikesRepository $like
     * @param EntityManagerInterface $em
     * @Route ("Aimer/{ida}",name="AimerAbo")
     */
    public function Aimer($ida,AbonnementRepository $abonnement,UserRepository $user,LikesRepository $like,EntityManagerInterface $em){
        $abonnements=$this->repository->findAll();
        $abo=$abonnement->find($ida);
        $us=$user->find(1);
        $l=new Likes();
        $l->setAbonnementId($abo);
        $l->setUserId($us);
        $l->setAimer(1);
        $em->persist($l);
        $em->flush();
        return $this->render('front/test_taker/abonnement/index.html.twig',['l' => $l,'abonnements' => $abonnements]);

    }


    /**
     * @param $ida
     * @param $idu
     * @param AbonnementRepository $abonnement
     * @param UserRepository $user
     * @param LikesRepository $like
     * @param Likes $l
     * @param EntityManagerInterface $em
     * @return Response
     * @Route ("NAimer/{idu}/{ida}",name="NAimerAbo")
     */
    public function dislike($ida,$idu,AbonnementRepository $abonnement,UserRepository $user,LikesRepository $like,Likes $l,EntityManagerInterface $em){
        $abonnement=$abonnement->find($ida);
        $user=$user->find($idu);
        $l->setAbonnementId($abonnement);
        $l->setUserId($user);
        $l->setAimer(0);
        $em->persist($l);
        $em->flush();
        return $this->render('front/test_taker/abonnement/index.html.twig',['l' => $l,'user'=>$user]);
    }


    public function intentSecret(Abonnement $abonnement){
        $intent=$this->stripeService->paymentIntent($abonnement);
        return $intent['client_secret']??null;
    }

    /**
     * @param array $stripeParameter
     * @param Abonnement $abonnement
     * @return array|null
     */
    public function stripe(array $stripeParameter,Abonnement $abonnement){
        $resource=null;
        $data=$this->stripeService->stripe($stripeParameter,$abonnement);
        if($data){
            $resource=[
                'stripeBrand'=>$data['charges']['data'][0]['payment_method_details']['card']['brand'],
                'stripeLast4'=>$data['charges']['data'][0]['payment_method_details']['card']['Last4'],
                'stripeId'=>$data['charges']['data'][0]['payment_method_details']['card']['id'],
                'stripeStatus'=>$data['charges']['data'][0]['payment_method_details']['card']['status'],
                'stripeToken'=>$data['client_secret']

            ];
        }
        return $resource;
    }

    /**
     * @param array $resource
     * @param Abonnement $abonnement
     * @param User $user
     */
    public function create_subscription(array $resource,Abonnement $abonnement,User $user,EntityManagerInterface $em){
        $commande=new Commande();
        $commande->setUser($user);
        $commande->setAbonnement($abonnement);
        $commande->setPrix($abonnement->getCout());
        $commande->setBrandToken($resource['stripeBrand']);
        $commande->setLast4Stripe($resource['stripeBrand']);
        $commande->setIdChangeStripe($resource['stripeBrand']);
        $commande->setStripeToken($resource['stripeBrand']);
        $commande->setStatusStripe($resource['stripeBrand']);
        $em->persist($commande);
        $em->flush();

    }

}
