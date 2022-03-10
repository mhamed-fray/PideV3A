<?php

namespace App\Entity;

use App\Repository\TestRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;


use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=TestRepository::class)
 */
class Test
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     *  @Assert\NotBlank
     * @Assert\GreaterThan("today")
     * @Groups("post:read")
     */
    private $datedebut;


    /**
     * @ORM\Column(type="date", nullable=true)
     * @Assert\NotBlank
     * * @Assert\GreaterThan(propertyPath="datedebut")
     * @Groups("post:read")

     *


     */
    private $datefin;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("post:read")
     * * @Assert\Length(
     *      min = 4,
     *      max = 20,
     *      minMessage = "Your title  must be at least {{ limit }} characters long",
     *      maxMessage = "Your tilte cannot be longer than {{ limit }} characters"
     *
     * )
     * @Assert\NotBlank(message="le champ titre est obligatoire ")

     */
    private $titre;


    /**
     * @ORM\OneToOne(targetEntity=Sujet::class, cascade={"persist", "remove"})
     * @Assert\Valid

     */
    private $Sujet;

    /**
     * @ORM\OneToMany(targetEntity=Question::class, mappedBy="test", orphanRemoval=true)
     */
    private $questions;

    /**
     * @ORM\OneToOne(targetEntity=Quiz::class, mappedBy="test", cascade={"persist", "remove"})
     */
    private $quiz;





    

    public function __construct()
    {
        $this->questions = new ArrayCollection();
    }
    public function getSujet(): ?Sujet
    {
        return $this->Sujet;
    }

    public function setSujet(?Sujet $Sujet): self
    {
        $this->Sujet = $Sujet;

        return $this;
    }






    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->datedebut;
    }

    public function setDateDebut(\DateTimeInterface $datedebut): self
    {
        $this->datedebut = $datedebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->datefin;
    }

    public function setDateFin(?\DateTimeInterface $datefin): self
    {
        $this->datefin = $datefin;

        return $this;
    }
    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

        return $this;
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
            $question->setTest($this);
        }

        return $this;
    }

    public function removeQuestion(Question $question): self
    {
        if ($this->questions->removeElement($question)) {
            // set the owning side to null (unless already changed)
            if ($question->getTest() === $this) {
                $question->setTest(null);
            }
        }

        return $this;
    }

    public function getQuiz(): ?Quiz
    {
        return $this->quiz;
    }

    public function setQuiz(Quiz $quiz): self
    {
        // set the owning side of the relation if necessary
        if ($quiz->getTest() !== $this) {
            $quiz->setTest($this);
        }

        $this->quiz = $quiz;

        return $this;
    }
}
