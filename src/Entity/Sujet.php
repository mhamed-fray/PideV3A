<?php

namespace App\Entity;

use App\Repository\SujetRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=SujetRepository::class)
 */
class Sujet
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * * * @Assert\Length(
     *      min = 4,
     *      max = 10,
     *      minMessage = "Your subject must be at least {{ limit }} characters long",
     *      maxMessage = "Your subject cannot be longer than {{ limit }} characters"
     * )
     *  * @Assert\NotBlank(message="le champ sujet est obligatoire")
     */
    private $Sujet;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getSujet(): ?string
    {
        return $this->Sujet;
    }

    public function setSujet(string $Sujet): self
    {
        $this->Sujet = $Sujet;

        return $this;
    }
}
