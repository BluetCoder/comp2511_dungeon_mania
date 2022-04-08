                                                                                                                        Assumptions
                                                                                                                     Milestone 1 and 2
Boulder
1. Boulder movement is only stopped by static entities except for floor switches.
2. Boulder, when pushed to occupy the same position as an enemy will kill the enemy in one move.

Zombie Toast Spawner
1. Only spawns zombies on an empty cardinal adjacent position. If all cardinal adjacent positions are occupied by another entity, the zombie does not spawn after set ticks for that spawner.

Zombie
1. Zombie health set at 1000hp
2. Attack Stat equals 1
3. If a zombie spawns with armour, the stats apply to the zombie.

Spider
4. Max spiders to occupy the dungeon is 4. 
5. Spider health set at 1000hp
6. Spider can only spawn in a position that is within the dungeon boundaries
7. Attack Stat equals 1
8. Spawns every 25 ticks

Player
1. Player health is halved for hard game mode
2. Currently 4000 hp for standard and 2500 hp for hard
3. Player attack stat set to take 1hp from enemies
4. Player inventory can hold unlimited items unless specified in specs

Bow and Arrows
1. Arrows are only consumed to build a bow. Any remaining arrows in the player's inventory are not consumed when using the bow.
2. Bow allows player to attack twice in one round of attack, through 2x multiplier
3. Bow durability is 3

Battle
1. Player must be in the same position as the enemy to initiate a battle. 
2. Once the player is in a battle they cannot move and mercenaries gain a battle radius 3x3 when players in battle. Reverts back to 1x1.
3. Enemies will only attack the player and allies. 
4. Player always attacks first.
5. If enemy has a weakness prioritises weapon it is weak to, otherwise priotises first weapon from player’s inventory

Build
1. Building priorities in the following order: Sun Stone, Treasure, Key when applicable. 
2. You can only build one bow and shield at a time

Mercenary
1. 1 coin to bribe mercenary
2. Spawns every 30 ticks
3. Mercenary Spawns at entrance which is set to be player initial spawn point

Sword 
1. Sword is durable for a maximum of 4
2. Attack multiplier is 1.5

The One Ring 
1. Drop Rate equals 2%

Portals
1. If portal exit’s are not blocked by a static entity, exit the portal on the opposite side that you entered.
    1. Enter from up, Exit from down (Vice Versa)
    2. Enter from left, Exit from right (Vice Versa)
2. If portal exit blocked, player isnt teleport and instead simply walks through the portal.

Shield 
1. Once a shield is crafted, it is equipped automatically. Reduces enemy attack damage by 2
2. Durability equals 4

Armour 
1. Once enemy is defeated armour appears in inventory
2. Once an amour is collected, it is equipped automatically. Reduces enemy attack damage 2
3. 10% of zombie, zombies and assassins spawns with armour

Equipment 
1. If armour and shield are both equipped, the damage reduction is stacked . Durability is reduced by 1 simultaneously for both armour and shield.

Bomb
1. Bomb once activated detonates around a 3x3 area, with the bomb in the centre.
2. Bombs cannot be picked up once placed down
3. Bombs destroy everything except the following:
    1. Exit
    2. Player

Potion
1. If player’s health is full, health potions cannot be consumed
2. Invincibility potion is active for 25 ticks
3. Invisibility potion is active for 50 ticks 


                                                                                                                  Milestone 3
Sceptre
1. Activated through dungeon tick
2. All mercenaries adjacent to player are mind controlled

Hydra
1. Spawn point is player’s initial spawn point
2. Spawns every 50 ticks in hard mode
3. 50/50 percent for health increase
4. Hydra health equals 2000
5. Hydra attack equals 1
6. When a new head grows after attacked plus 2000hp to current health

Assassin
1. Health equals 2500
2. Attack stat is 2.5

Anduril, Flame of the West
1. 2% drop rate in standard and peaceful mode and 5% in hardmode
2. Durability is 8
3. Attack is 1.5, unless specified in specs (triple damage to bosses)

Midnight Armour
1. Durability is 8
2. Damage reduction is 2
3. Attack modifier is 1.25
