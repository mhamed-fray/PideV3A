<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220302185757 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE comments (id INT AUTO_INCREMENT NOT NULL, event_id INT NOT NULL, parent_id INT DEFAULT NULL, content LONGTEXT NOT NULL, active TINYINT(1) NOT NULL, email VARCHAR(255) NOT NULL, nickname VARCHAR(255) NOT NULL, created_at DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', INDEX IDX_5F9E962A71F7E88B (event_id), INDEX IDX_5F9E962A727ACA70 (parent_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE comments ADD CONSTRAINT FK_5F9E962A71F7E88B FOREIGN KEY (event_id) REFERENCES evenement (id)');
        $this->addSql('ALTER TABLE comments ADD CONSTRAINT FK_5F9E962A727ACA70 FOREIGN KEY (parent_id) REFERENCES comments (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE comments DROP FOREIGN KEY FK_5F9E962A727ACA70');
        $this->addSql('DROP TABLE comments');
        $this->addSql('ALTER TABLE abonnement CHANGE nom nom VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE candidature CHANGE etat_candidature etat_candidature VARCHAR(255) DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE message message VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE categorie CHANGE type_categorie type_categorie VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE commentaire CHANGE contenu contenu VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE entreprise CHANGE nom_entreprise nom_entreprise VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE logo logo VARCHAR(255) DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE siteweb siteweb VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE secteur_entreprise secteur_entreprise VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE evenement CHANGE titre titre VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE image image VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE localisation localisation VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE offre CHANGE titre titre VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE secteur secteur VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE description description VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE localisation_offre localisation_offre VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE question CHANGE contenu contenu VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE numero numero VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE type_question type_question VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE grade grade VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE type CHANGE type type VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE `user` CHANGE email email VARCHAR(180) NOT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE roles roles LONGTEXT NOT NULL COLLATE `utf8mb4_unicode_ci` COMMENT \'(DC2Type:json)\', CHANGE password password VARCHAR(255) NOT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
