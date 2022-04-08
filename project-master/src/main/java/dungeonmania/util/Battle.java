package dungeonmania.util;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.inventory.*;
import dungeonmania.dungeon.Dungeon;
import dungeonmania.entity.*;
import dungeonmania.entity.Spider.Spider;

public class Battle {
    /**
     * If an enemy is in the same position as player, they battle trading health.
     * Deletes player or enemy from allEntities if their health is <= 0
     * 
     * @param allEntities
     * @return
     */
    public static void doBattle(Player player, List<Entity> allEntities, String gameMode, Dungeon dungeon) {
        Position playerPosition = player.getPosition();
        List<Entity> entitiesToRemove = new ArrayList<Entity>();
        if (!(player.getPlayerState().getState().equals("invisible"))) {
            for (Entity entity : allEntities) {
                if (battleConditions(entity, playerPosition)) {
                    if (player.isAnAlly(entity)) {
                        continue;
                    }

                    if (player.getPlayerState().getState().equals("invincible")) {
                        entitiesToRemove.add(entity);
                        break;
                    }
                    BattleInterface enemy = (BattleInterface) entity;
                    player.setInBattle(true);
                    while (true) {
                        battleSimulation(player, enemy, gameMode, entitiesToRemove, entity);
                        List<Entity> allies = player.getAllies();
                        List<Entity> deadAllies = new ArrayList<>();
                        for (Entity ally : allies) {
                            BattleInterface battleAlly = (BattleInterface) ally;
                            battleSimulation(battleAlly, enemy, gameMode, entitiesToRemove, entity);
                            if (battleAlly.getHealth() <= 0) {
                                deadAllies.remove(ally);
                            }
                        }
                        for (Entity ally : deadAllies) {
                            allies.remove(ally);
                            player.detach((EnemyObserver) ally);
                        }
                        if (player.getHealth() <= 0 || enemy.getHealth() <= 0) {
                            player.detach((EnemyObserver) enemy);
                            break;
                        }
                    }
                }
                if (player.getHealth() <= 0) {
                    break;
                }
            }
            removeDeadCharacters(allEntities, entitiesToRemove, player);
        }
    }

    private static void damageCalculations(BattleInterface attacker, BattleInterface defender, Boolean attackIsPlayer) {
        double attackerHealth = attacker.getHealth();
        double attackerDamage = attacker.getAttack();

        String weaponWeakness = defender.getWeaponWeakness();
        OffensiveWeapon attackWeapon = null;
        Boolean weakness = true;
        if (weaponWeakness != null) {
            attackWeapon = attacker.getOffensiveWeapon(weaponWeakness);
        } else {
            weakness = false;
        }
        if (attackWeapon == null) {
            attackWeapon = attacker.getOffensiveWeapon();
            weakness = false;
        }

        if (attackWeapon != null) {
            attackerDamage = attacker.getAttack() * attackWeapon.getAttackModifier();
        }

        if (attacker.getArmourAttack() > 0) {
            attackerDamage = attacker.getAttack() * attacker.getArmourAttack();
        }

        if (weakness == true) {
            attackerDamage = attackerDamage * 3;
        }

        double damage = 0;
        if (attackIsPlayer) {
            damage = ((attackerHealth * attackerDamage) / 5);
        } else {
            damage = ((attackerHealth * attackerDamage) / 10);
        }

        double damageDeduction = defender.getDefenceReduction();
        if (damageDeduction > 0) {
            damage = damage / damageDeduction;
        }

        double defenderHealth = defender.getHealth();
        double defenderNewHealth = defenderHealth - damage;

        if (defenderNewHealth < 0) {
            defenderNewHealth = 0;
        }

        defender.setHealthAfterDamage(defenderNewHealth, weakness);
    }

    private static void removeDeadCharacters(List<Entity> allEntities, List<Entity> entitiesToRemove, Player player) {
        List<Item> drop = new ArrayList<Item>();
        for (Entity dead: entitiesToRemove) {
            switch (dead.getType()) {
                case "zombie_toast" :
                    ZombieToast zombieToast = (ZombieToast)dead;
                    drop = zombieToast.drop(player.getGameMode());
                    break;
                case "spider":
                    Spider spider = (Spider)dead;
                    drop = spider.drop(player.getGameMode());
                    break;
                case "mercenary":
                    Mercenary mercenary = (Mercenary)dead;
                    drop = mercenary.drop(player.getGameMode());
                    break;
                case "hydra":
                    Hydra hydra = (Hydra)dead;
                    drop = hydra.drop(player.getGameMode());
                    break;
            }
            if (drop != null) {
                player.getInventory().getInventory().addAll(drop);
            }
            allEntities.remove(dead);
        }
    }

    private static boolean battleConditions(Entity entity, Position playerPosition) {

        if (entity instanceof BattleInterface && !entity.getType().equals("player")
                && entity.getPosition().equals(playerPosition)) {
            return true;
        }
        return false;
    }

    private static void battleSimulation(BattleInterface friendly, BattleInterface enemy, String gameMode,
            List<Entity> entitiesToRemove, Entity entity) {
        Boolean attackIsPlayer = false;
        if (friendly instanceof Player) {
            attackIsPlayer = true;
        }
        damageCalculations(friendly, enemy, attackIsPlayer);

        if (gameMode.equals("Standard") || gameMode.equals("Hard")) {
            damageCalculations(enemy, friendly, false);
        }

        double friendlyHealth = friendly.getHealth();
        double enemyHealth = enemy.getHealth();
        /**
         * If player's health is <= 0, remove from allEntities
         */

        if (friendlyHealth <= 0) {
            if (friendly instanceof Player) {
                if (((Player)friendly).getInventory().itemExists("one_ring")) {
                    OneRing one_ring = (OneRing)((Player)friendly).getInventory().itemGetter("one_ring");
                    one_ring.revive((Player)friendly);
                } else {
                    entitiesToRemove.add((Entity)friendly);  
                }
            } else {
                entitiesToRemove.add((Entity)friendly);   
            }    
        }

        /**
         * 
         * If enemy's health is <= 0, remove from allEntities
         */
        if (enemyHealth <= 0) {
            entitiesToRemove.add(entity);
        }
    }
}
