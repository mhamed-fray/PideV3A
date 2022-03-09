<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use App\Repository\EvenementRepository;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=EvenementRepository::class)
 * @Vich\Uploadable()
 */
class Evenement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @Assert\NotBlank
     * @ORM\Column(type="string", length=255)
     */
    private $titre;

    /**
     * @Assert\NotBlank
     * @ORM\Column(type="date")
     */
    private $dat;

    /**
     * @Assert\NotBlank
     * @ORM\Column(type="string", length=255)
     */
    private $description;


    /**
     *  @ORM\Column(type="string", length=255)
     *     @Assert\File(mimeTypes={"image/jpeg"})
     */
    private $image;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $localisation;

    /**
     * @ORM\Column(type="integer")
     */
    public $nb_participants;

    /**
     * @ORM\OneToMany(targetEntity=Comments::class, mappedBy="event", orphanRemoval=true)
     */
    private $comments;


    /**
     * @ORM\Column(type="integer")
     */
    public $note;

   
   

    public function __construct()
    {
        
        $this->comments = new ArrayCollection();
        $this->reservations = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
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

    public function getDat(): ?\DateTimeInterface
    {
        return $this->dat;
    }

    public function setDat(\DateTimeInterface $dat): self
    {
        $this->dat = $dat;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    public function getLocalisation(): ?string
    {
        return $this->localisation;
    }

    public function setLocalisation(string $localisation): self
    {
        $this->localisation = $localisation;

        return $this;
    }

    public function getnb_participants(): ?int
    {
        return $this->nb_participants;
    }

    public function setnb_participants(int $nb_participants): self
    {
        $this->nb_participants += $nb_participants;

        return $this;
    }

    
    /**
     * @return Collection|Comments[]
     */
    public function getComments(): Collection
    {
        return $this->comments;
    }

    public function addComment(Comments $comment): self
    {
        if (!$this->comments->contains($comment)) {
            $this->comments[] = $comment;
            $comment->setEvent($this);
        }

        return $this;
    }

    public function removeComment(Comments $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getEvent() === $this) {
                $comment->setEvent(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|Reservation[]
     */
    public function getReservations(): Collection
    {
        return $this->reservations;
    }

    public function addReservation(Reservation $reservation): self
    {
        if (!$this->reservations->contains($reservation)) {
            $this->reservations[] = $reservation;
            $reservation->setEve_id($this);
            $this->setnb_participants($reservation.getNbplace());
        }

        return $this;
    }

    public function removeReservation(Reservation $reservation): self
    {
        if ($this->reservations->removeElement($reservation)) {
            // set the owning side to null (unless already changed)
            if ($reservation->getEve_id() === $this) {
                $reservation->setEve_id(null);
            }
        }

        return $this;
    }

}
