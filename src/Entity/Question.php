<?php

namespace App\Entity;

use App\Repository\QuestionRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;




/**
 * @ORM\Entity(repositoryClass=QuestionRepository::class)
 */
class Question
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank
     *  @Assert\Length(
     *      min = 4,
     *      minMessage = "Your question  must be at least {{ limit }} characters long",
     * )
     */
    private $contenu;

    /**
     * @ORM\ManyToOne(targetEntity=Bibliotheque::class, inversedBy="questions")
     */
    private $bibliotheque;

    /**
     * @ORM\OneToMany(targetEntity=Choix::class, mappedBy="question", orphanRemoval=true)
     */
    private $choix;

    
    /**
     * @ORM\ManyToOne(targetEntity=Test::class, inversedBy="questions")
     * @ORM\JoinColumn(nullable=false)
     */
    private $test;


    /**
     * @ORM\ManyToOne(targetEntity=Quiz::class, inversedBy="questions")
     */
    private $quiz;

    /**
     * @ORM\Column(type="boolean", nullable=true)
     */
    private $favori;

    

    public function __construct()
    {
        $this->choix = new ArrayCollection();
    }

    






    public function getId(): ?int
    {
        return $this->id;
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


    public function getBibliotheque(): ?Bibliotheque
    {
        return $this->bibliotheque;
    }

    public function setBibliotheque(?Bibliotheque $bibliotheque): self
    {
        $this->bibliotheque = $bibliotheque;

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
            $choix->setQuestion($this);
        }

        return $this;
    }

    public function removeChoix(Choix $choix): self
    {
        if ($this->choix->removeElement($choix)) {
            // set the owning side to null (unless already changed)
            if ($choix->getQuestion() === $this) {
                $choix->setQuestion(null);
            }
        }

        return $this;
    }

    public function getTest(): ?Test
    {
        return $this->test;
    }

    public function setTest(?Test $test): self
    {
        $this->test = $test;

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

    public function getFavori(): ?bool
    {
        return $this->favori;
    }

    public function setFavori(?bool $favori): self
    {
        $this->favori = $favori;

        return $this;
    }

    
    
}
