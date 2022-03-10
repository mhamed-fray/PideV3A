<?php

namespace App\Controller;
use Swift_Mailer;
use App\Entity\Contact;
use App\Form\ContactType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ContactController extends AbstractController
{

    /**
     * @Route("/contact", name="contact")
     * @param Request $request
     * @param Swift_Mailer $mailer
     * @return Response
     */
    public function email(Request $request , Swift_Mailer $mailer)
    {

        $contact= new Contact();
        $form = $this->createForm(ContactType::class,$contact);
        $form->handleRequest($request);
        # check if form is submitted

        if($form->isSubmitted() &&  $form->isValid()) {
            $name = $form['name']->getData();
            $email = $form['email']->getData();
            $message = $form['message']->getData();

            # set form data

            $contact->setName($name);
            $contact->setEmail($email);
            $contact->setMessage($message);

            # finally add data in database

            $sn = $this->getDoctrine()->getManager();
            $sn->persist($contact);
            $sn->flush();
            $subj = (new \Swift_Message('Proposition'))
                ->setFrom($email)
                ->setTo('nidhal.zoukeri@esprit.tn')
                ->setBody($this->renderView('emailTemplate/sendEmail.html.twig',array('name' => $name, 'message' => $message)),'text/html');
            $mailer->send($subj);

        }

        return $this->render('emailTemplate/contact.html.twig',['emailForm' => $form->createView()]);
    }
}
