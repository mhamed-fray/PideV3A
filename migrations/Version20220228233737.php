<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220228233737 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE commande (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, abonnement_id INT NOT NULL, stripe_token VARCHAR(255) NOT NULL, brand_token VARCHAR(255) NOT NULL, last4_stripe INT NOT NULL, id_change_stripe VARCHAR(255) NOT NULL, status_stripe VARCHAR(255) NOT NULL, INDEX IDX_6EEAA67DA76ED395 (user_id), INDEX IDX_6EEAA67DF1D74413 (abonnement_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DA76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DF1D74413 FOREIGN KEY (abonnement_id) REFERENCES abonnement (id)');
        $this->addSql('ALTER TABLE user ADD stripe_token VARCHAR(255) DEFAULT NULL, ADD brand_stripe VARCHAR(255) DEFAULT NULL, ADD last4_stripe VARCHAR(255) NOT NULL, ADD id_charge_stripe VARCHAR(255) DEFAULT NULL, ADD status_stripe VARCHAR(255) DEFAULT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE commande');
        $this->addSql('ALTER TABLE `user` DROP stripe_token, DROP brand_stripe, DROP last4_stripe, DROP id_charge_stripe, DROP status_stripe');
    }
}
