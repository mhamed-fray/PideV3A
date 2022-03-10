<?php

namespace App\Entity;

use App\Repository\ChoixRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=ChoixRepository::class)
 */
class Choix
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="boolean")
     */
    private $etatchoix;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Aucun choix peut être null")
     */
    private $contenu;

    /**
     * @ORM\ManyToOne(targetEntity=Question::class, inversedBy="choix")
     */
    private $question;

    /**
     * @ORM\ManyToOne(targetEntity=Quiz::class, inversedBy="choix")
     */
    private $quiz;

    /**
     * @ORM\Column(type="boolean", nullable=true)
     */
    private $checked;

    

    

   

    


    
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEtatChoix(): ?bool
    {
        return $this->etatchoix;
    }

    public function setEtatChoix(bool $etatchoix): self
    {
        $this->etatchoix = $etatchoix;

        return $this;
    }

    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    public function setContenu(string $contenu): self
    {
        $this->contenu = $contenu;

        return $this;
    }
    

    public function getQuestion(): ?Question
    {
        return $this->question;
    }

    public function setQuestion(?Question $question): self
    {
        $this->question = $question;

        return $this;
    }

    public function getQuiz(): ?Quiz
    {
        return $this->quiz;
    }

    public function setQuiz(?Quiz $quiz): self
    {
        $this->quiz = $quiz;

        return $this;
    }

    public function getChecked(): ?bool
    {
        return $this->checked;
    }

    public function setChecked(?bool $checked): self
    {
        $this->checked = $checked;

        return $this;
    }

    


    


    

    
}
