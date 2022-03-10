<?php

namespace App\Controller\Front\Entreprise;
use App\Form\CandidatureType;
use App\Form\OffreType;
use App\Repository\CandidatureRepository;
use App\Repository\OffreRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Candidature;
use App\Entity\Offre;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;









class CandidatureController extends AbstractController
{
    /**
     * @Route("/front/test_taker/candidature", name="postuleroffre")
     */
    public function index(OffreRepository $repository): Response
    {
        $Offre=$repository->findAll();
        return $this->render('front/test_taker/abonnement/postuleroffre.html.twig', [
            'Offre' => $Offre,
        ]);
    }
    /**
     * @Route("/allcandidatures", name="all_candidatures")
     */
    public function all(): Response
    {
        return $this->render('back/candidatures.html.twig', [
            'controller_name' => 'CandidatureController',
        ]);
    }


    /**
     * @Route("/condidature/postuler/{id}", name="ajouterdemande")
     */
    public function createAction(Request $request,$id,FlashyNotifier $flashy) {

        $candidature = new Candidature();



        $form = $this->createForm(CandidatureType::class,$candidature);
        $form->add('Postuler',SubmitType::class);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isvalid() ){
            $em = $this->getDoctrine()->getManager();
            $candidature->setEtatCandidature('waiting');



            $em->persist($candidature);
            $em->flush();
            $flashy->success('DONE !!', 'http://your-awesome-link.com');


            return $this->redirectToRoute('postuleroffre');
        }
        return $this->render("front/entreprise/candidature/adddemande.html.twig", ['form2' => $form->createView()]);

    }
    /**
     * @param CandidatureRepository $repository
     * @Route("/front/tous_condidatures", name="tous_condidatures")
     */
    public function liste(CandidatureRepository $repository): Response
    {

        $candidature=$repository->findAll();
        return $this->render('front/entreprise/candidature/tous_condidatures.html.twig', [
            'candidature' => $candidature,
        ]);
    }

    /**
     * @Route("/candidature/delete/{id}", name="delete_condidature")
     */
    public function deleteClass($id)
    {

        $em = $this->getDoctrine()->getManager();
        $candidature = $this->getDoctrine()->getRepository(Candidature::class)->find($id);
        $em->remove($candidature);
        $em->flush();
        return $this->redirectToRoute("tous_condidatures");

    }





    /**
     * @Route("candidature/edit/{id}", name="editer_condidature")
     */
    public function edit(CandidatureRepository $repository,$id, Request $request ):response
    {
        $candidature=$repository->find($id);

        $form=$this->createForm(CandidatureType::class, $candidature);
        $form->add('update',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() )
        {
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('tous_condidatures');
        }
        return $this->render('front/entreprise/candidature/editer_condidature.html.twig',[

            'form3' =>$form->createView()
        ]);
    }



    /**
     * @Route("candidature/accepter/{id}", name="accepter_candidature")
     */
    public function accepter(CandidatureRepository $repository,$id, Request $request):response
    {
        $candidature=$repository->find($id);
        $candidature->setEtatCandidature('accepte');
        $em=$this->getDoctrine()->getManager();
        $em->flush();





        return $this->redirectToRoute("tous_condidatures");
    }


    /**
     * @Route("candidature/refuser/{id}", name="refuser_candidature")
     */
    public function refuser(CandidatureRepository $repository,$id, Request $request ):response
    {
        $candidature=$repository->find($id);
        $candidature->setEtatCandidature('refuse');
        $em=$this->getDoctrine()->getManager();
        $em->flush();


        return $this->redirectToRoute("tous_condidatures");
    }


    /**
     * @Route("/deleteRE", name="SUPPR")
     */


    public function listeA(CandidatureRepository $repository): Response
    {

        $candidature=$repository->findOnlyaccepted();
        return $this->render('front/entreprise/candidature/tous_condidatures.html.twig', [
            'candidature' => $candidature,
        ]);
    }






}
