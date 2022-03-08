<?php

namespace App\Entity;

use App\Repository\QuizRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;


/**
 * @ORM\Entity(repositoryClass=QuizRepository::class)
 */
class Quiz
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\OneToMany(targetEntity=Question::class, mappedBy="quiz", orphanRemoval=true)
     */
    private $questions;

    /**
     * @ORM\OneToOne(targetEntity=Test::class, inversedBy="quiz", cascade={"persist", "remove"})
     */
    private $test;

    /**
     * @ORM\OneToMany(targetEntity=Choix::class, mappedBy="quiz")
     */
    private $choix;

    



    public function __construct()
    {
        $this->questions = new ArrayCollection();
        $this->choix = new ArrayCollection();
        
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @return Collection|Question[]
     */
    public function getQuestions(): Collection
    {
        return $this->questions;
    }

    public function addQuestion(Question $question): self
    {
        if (!$this->questions->contains($question)) {
            $this->questions[] = $question;
            $question->setQuiz($this);
        }

        return $this;
    }

    public function removeQuestion(Question $question): self
    {
        if ($this->questions->removeElement($question)) {
            // set the owning side to null (unless already changed)
            if ($question->getQuiz() === $this) {
                $question->setQuiz(null);
            }
        }

        return $this;
    }

    public function getTest(): ?Test
    {
        return $this->test;
    }

    public function setTest(Test $test): self
    {
        $this->test = $test;

        return $this;
    }

    /**
     * @return Collection|Choix[]
     */
    public function getChoix(): Collection
    {
        return $this->choix;
    }

    public function addChoix(Choix $choix): self
    {
        if (!$this->choix->contains($choix)) {
            $this->choix[] = $choix;
            $choix->setQuiz($this);
        }

        return $this;
    }

    public function removeChoix(Choix $choix): self
    {
        if ($this->choix->removeElement($choix)) {
            // set the owning side to null (unless already changed)
            if ($choix->getQuiz() === $this) {
                $choix->setQuiz(null);
            }
        }

        return $this;
    }

   

    
    

    

    
}
