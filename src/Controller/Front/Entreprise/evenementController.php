<?php

namespace App\Controller\Front\Entreprise;


use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\EvenementRepository;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Evenement;
use App\Form\EvenementType;
use App\Entity\Reservation;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use App\Entity\Comments;
use App\Form\CommentsType;
use Monolog\DateTimeImmutable;
use App\Repository\CommentsRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;

class evenementController extends AbstractController
{




   
    /**
     * @Route("/front/entreprise/evenement", name="front_entreprise_creerevenement")
     */
    public function index(): Response
    {
        return $this->render('front/entreprise/evenement/index.html.twig', [
            'controller_name' => 'evenementController',
        ]);
    }

    

         /**
         * @param EvenementRepository $repository
         * @return \Symfony\Component\HttpFundation\Response
         *  @Route("/entevenements", name="front_entreprise_test")
         */
        public function Affichee(EvenementRepository $repository)
        {
            $Even=$repository->findAll();
            return $this->render('front/entreprise/evenement/tousevenements.html.twig',
                ['evenement'=>$Even]);
        }



    /**
    * @param EvenementRepository $repository
     * @return \Symfony\Component\HttpFundation\Response
     *@Route("/supprimer",name="supp")
     */
    public function Delete(EvenementRepository $repo){
        
        $ev=$repo->find($id);
        
        $em=$this->getDoctrine()->getManager();
        $em->remove($ev);
        $em->flush();
       
        return $this->redirectToRoute('front_entreprise_test'); 
    }
    /**
     * @param Request $request
     * @return \Symfony\Component\HttpFundation\Response
     * @Route ("addev",name="add")
     */
    public function addEvenement(Request $request)
    {
        $ev=new Evenement();
        $form=$this->createForm(EvenementType::class, $ev);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $file = $ev->getImage();
$fileName=md5(uniqid()).'.'.$file->guessExtension();
try {
    $file->move(
        $this->getParameter('images_directory'),
        $fileName
    );
} catch (FileException $e) {
    // ... handle exception if something happens during file upload
}

$ev->setImage($fileName);
            $ev=$form->getData();
            $em=$this->getDoctrine()->getManager();
            $em->persist($ev);
            $em->flush();
            return $this->redirectToRoute('front_entreprise_test');
        }
        return $this->render('front/entreprise/evenement/addev.html.twig',[
            'form'=>$form->createView()
        ]);
    }

    /**
     *  @param Request $request
     * @return \Symfony\Component\HttpFundation\Response
     * @Route("/modifier/{id}",name="modifier")
     */
    public function update(EvenementRepository $repo,$id,Request $req)
    {
        $ev=$repo->find($id);
        $form=$this->CreateForm(EvenementType::class,$ev);
        $form->add("modifier",SubmitType::class);
        $form->handleRequest($req);
        if($form->isSubmitted() && $form->isValid())
        {
            $file = $ev->getImage();
$fileName=md5(uniqid()).'.'.$file->guessExtension();
try {
    $file->move(
        $this->getParameter('images_directory'),
        $fileName
    );
} catch (FileException $e) {
    // ... handle exception if something happens during file upload
}

$ev->setImage($fileName);
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('front_entreprise_test');
        }
        return $this->render('front/entreprise/evenement/modifier.html.twig',[
            'form'=>$form->createView()
        ]);
    }

    /**
     *  @param ReservationRepository $repository
     * @Route("/affreservation/{id}",name="affreser")
     */
    public function affres($id)
    {
        $res=$this->getDoctrine()
        ->getRepository(Reservation::class)
        ->AfficherParIdEv($id);
        dd($res);
       return $this->render('front/entreprise/evenement/res.html.twig',
                    ['reservation'=>$res]);

    }


    /**
     * @param Request $request
     * @Route("\details\{id}", name="details")
     */
    public function details(EvenementRepository $repository,$id,Request $req)
    {
        $evenement=$repository->findOneBy(['id' => $id]);

        if(!$evenement){
            throw new NotFoundHttpException('Pas d\'evenement trouvé');
        }

        $comment=new Comments;

        $commentForm=$this->createForm(CommentsType::class,$comment);
        $commentForm->handleRequest($req);
        if($commentForm->isSubmitted() && $commentForm->isValid()){
           
            $comment->setCreatedAt(new DateTimeImmutable("now" ,null));
            $comment->setEvent($evenement);

            $parentid= $commentForm->get("parentid")->getData();

            $em=$this->getDoctrine()->getManager();

            $parent=$em->getRepository(comments::class)->find($parentid);
            $comment->setParent($parent);
            $em->persist($comment);
            $em->flush();

            $this->addFlash('message','Votre commentaire a été envoyé');
            return $this->redirectToRoute('details',['id'=>$evenement->getId()]);

        }


        return $this->render('front/test_taker/reservation/detail.html.twig',
                ['evenement'=>$evenement ,
                  'commentForm' => $commentForm->createView()
            ]);
    }





    /**
     * @param Request $request
     * @Route("\detent\{id}", name="detailentre")
     */
    public function detentr(EvenementRepository $repository,$id,Request $req)
    {
        $evenement=$repository->findOneBy(['id' => $id]);

        if(!$evenement){
            throw new NotFoundHttpException('Pas d\'evenement trouvé');
        }

        $comment=new Comments;

        $commentForm=$this->createForm(CommentsType::class,$comment);
        $commentForm->handleRequest($req);
        if($commentForm->isSubmitted() && $commentForm->isValid()){
           
            $comment->setCreatedAt(new DateTimeImmutable("now" ,null));
            $comment->setEvent($evenement);

            $parentid= $commentForm->get("parentid")->getData();

            $em=$this->getDoctrine()->getManager();

            $parent=$em->getRepository(comments::class)->find($parentid);
            $comment->setParent($parent);
            $em->persist($comment);
            $em->flush();

            $this->addFlash('message','Votre commentaire a été envoyé');
            return $this->redirectToRoute('detailentre',['id'=>$evenement->getId()]);

        }


        return $this->render('front/entreprise/evenement/detent.html.twig',
                ['evenement'=>$evenement ,
                  'commentForm' => $commentForm->createView()
            ]);
    }

    

    /**
     *@Route("\supprimercom\{id}", name="deletecom")
     */
    public function suppcom($id , CommentsRepository $repo,FlashyNotifier $flashy){
 
        
        $Comment=$repo->find($id);
        
       

        $em=$this->getDoctrine()->getManager();
        $em->remove($Comment);
        $em->flush();

        $flashy->success('commentaire supprimé!', 'http://your-awesome-link.com');
        return $this->redirectToRoute('detailentre',['id'=>$Comment-> getEvent()->getId()]); 

    }


}
