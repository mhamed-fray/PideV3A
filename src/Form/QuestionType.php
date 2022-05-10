<?php

namespace App\Form;

use App\Entity\Choix;
use App\Entity\Question;
use Doctrine\ORM\EntityRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\RangeType;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class QuestionType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        
        $builder
            ->add('contenu', TextType::class, [
                'attr' => [
                    'class' => 'form-style-textfield',
                ],
            ])
            
            
        ;
    }

   

    public function configureOptions(OptionsResolver $resolver): void
    {
       

        $resolver->setDefaults([
            'data_class' => Question::class,
        ]);
    }
}
