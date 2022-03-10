<?php

namespace App\Entity;

use App\Repository\AbonnementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=AbonnementRepository::class)
 */
class Abonnement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *      min = 2,
     *      max = 10,
     *      minMessage = "Le nom doit contenir au moins {{ limit }} caractères",
     *      maxMessage = "Le nom doit contenir au maximum {{ limit }} caractères"
     * )
     * @Assert\NotBlank(
     *     message="Ce champs ne doit pas etre vide")
     * @Groups("post:read")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *      min = 10,
     *      max = 100,
     *      minMessage = "Votre description doit contenir au moins {{ limit }} caractères",
     *      maxMessage = "Votre description doit contenir au maximum {{ limit }} caractères"
     * )
     * @Assert\NotBlank(
     *     message="Ce champs ne doit pas etre vide")
     * @Groups("post:read")
     */
    private $description;

    /**
     * @ORM\Column(type="integer")
     * @Assert\GreaterThan(
     *     value = 0,
     *     message="Le cout d'un abonnement ne doit pas etre egal ou inferieur à 0"
     * )
     * @Assert\NotBlank(
     *     message="Ce champs ne doit pas etre vide")
     *@Groups("post:read")
     */
    private $cout;

    /**
     * @ORM\ManyToOne(targetEntity=Type::class, inversedBy="abonnements")
     * @Groups("post:read")
     */
    private $Type;

    /**
     * @ORM\OneToMany(targetEntity=Commande::class, mappedBy="abonnement")
     */
    private $commandes;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $Likes;

    /**
     * @ORM\ManyToOne(targetEntity=Likes::class, inversedBy="abonnement_id")
     */
    private $likes;

    /**
     * @ORM\OneToMany(targetEntity=Likes::class, mappedBy="abonnement_id")
     */
    private $likes_id;

    public function __construct()
    {
        $this->commandes = new ArrayCollection();
        $this->likes_id = new ArrayCollection();
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(?string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getCout(): ?int
    {
        return $this->cout;
    }

    public function setCout(?int $cout): self
    {
        $this->cout = $cout;

        return $this;
    }

    public function getid(): ?int
    {
        return $this->id;
    }

    public function setid(int $id): self
    {
        $this->id = $id;

        return $this;
    }

    public function getType(): ?Type
    {
        return $this->Type;
    }

    public function setType(?Type $Type): self
    {
        $this->Type = $Type;

        return $this;
    }

    /**
     * @return Collection|Commande[]
     */
    public function getCommandes(): Collection
    {
        return $this->commandes;
    }

    public function addCommande(Commande $commande): self
    {
        if (!$this->commandes->contains($commande)) {
            $this->commandes[] = $commande;
            $commande->setAbonnement($this);
        }

        return $this;
    }

    public function removeCommande(Commande $commande): self
    {
        if ($this->commandes->removeElement($commande)) {
            // set the owning side to null (unless already changed)
            if ($commande->getAbonnement() === $this) {
                $commande->setAbonnement(null);
            }
        }

        return $this;
    }

    public function getLikes(): ?int
    {
        return $this->Likes;
    }

    public function setLikes(?int $Likes): self
    {
        $this->Likes = $Likes;

        return $this;
    }

    /**
     * @return Collection|Likes[]
     */
    public function getLikesId(): Collection
    {
        return $this->likes_id;
    }

    public function addLikesId(Likes $likesId): self
    {
        if (!$this->likes_id->contains($likesId)) {
            $this->likes_id[] = $likesId;
            $likesId->setAbonnementId($this);
        }

        return $this;
    }

    public function removeLikesId(Likes $likesId): self
    {
        if ($this->likes_id->removeElement($likesId)) {
            // set the owning side to null (unless already changed)
            if ($likesId->getAbonnementId() === $this) {
                $likesId->setAbonnementId(null);
            }
        }

        return $this;
    }
}
