<?php

namespace App\Form;

use App\Entity\Choix;
use App\Entity\Question;
use App\Entity\Quiz;
use App\Repository\QuestionRepository;
use Doctrine\ORM\EntityRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class QuizType extends AbstractType
{
    /**
     * @param QuestionRepository $repository
     */
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
     
        $id_question = $options['id'];
        $builder
        ->add('choix', EntityType::class,[
            'class' => Choix::class,
            'query_builder' => function (EntityRepository $er) use($id_question){
                return $er->createQueryBuilder('c')
                    ->where('c.question = :question')
                    ->setParameter('question', $id_question);

            },
            'attr' => [
                'class' => 'choix_quiz',
            ],
            'label' => "Sélectionner un ou plusieurs choix :",
            'choice_label' => 'contenu',
            'multiple' => true,
            'expanded' => true
        ]);
        
    }

    public function configureOptions(OptionsResolver $resolver): void
    {

        $resolver->setRequired('id');
        // type validation - User instance or int, you can also pick just one.
        $resolver->setAllowedTypes('id', array(Question::class, 'int'));
        
        $resolver->setDefaults([
            'data_class' => Quiz::class,
        ]);
    }
}
