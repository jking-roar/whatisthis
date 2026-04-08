# Savant statement of "there are 5 townsfolk in a row" in a nine player game of CatFishing.

# script:
#
# Catfishing
#
# Townsfolk
# Chef -- You start knowing how many pairs of evil players there are.
# Investigator -- You start knowing that 1 of 2 players is a particular Minion.
# Grandmother -- You start knowing a good player & their character. If the Demon kills them, you die too.
# Balloonist -- Each night, you learn a player of a different character type than last night. [+0 or +1 Outsider]
# Snake Charmer -- Each night, choose an alive player: a chosen Demon swaps characters & alignments with you & is then poisoned.
# Dreamer -- Each night, choose a player (not yourself or Travellers): you learn 1 good & 1 evil character, 1 of which is correct.
# Fortune Teller -- Each night, choose 2 players: you learn if either is a Demon. There is a good player that registers as a Demon to you.
# Gambler -- Each night*, choose a player & guess their character: if you guess wrong, you die.
# Savant -- Each day, you may visit the Storyteller to learn 2 things in private: 1 is true & 1 is false.
# Philosopher -- Once per game, at night, choose a good character: gain that ability. If this character is in play, they are drunk.
# Cannibal -- You have the ability of the recently killed executee. If they are evil, you are poisoned until a good player dies by execution.
# Amnesiac -- You do not know what your ability is. Each day, privately guess what it is: you learn how accurate you are.
# Ravenkeeper -- If you die at night, you are woken to choose a player: you learn their character.
#
# Outsiders
# Lunatic -- You think you are a Demon, but you are not. The Demon knows who you are & who you choose at night.
# Drunk -- You do not know you are the Drunk. You think you are a Townsfolk character, but you are not.
# Recluse -- You might register as evil & as a Minion or Demon, even if dead.
# Sweetheart -- When you die, 1 player is drunk from now on.
# Mutant -- If you are "mad" about being an Outsider, you might be executed.
#
# Minions
# Godfather -- You start knowing which Outsiders are in play. If 1 died today, choose a player tonight: they die. [-1 or +1 Outsider]
# Cerenovus -- Each night, choose a player & a good character: they are "mad" they are this character tomorrow, or might be executed.
# Pit-Hag -- Each night*, choose a player & a character they become (if not in play). If a Demon is made, deaths tonight are arbitrary.
# Widow -- On your 1st night, look at the Grimoire & choose a player: they are poisoned. 1 good player knows a Widow is in play.
#
# Demons
# Imp -- Each night*, choose a player: they die. If you kill yourself this way, a Minion becomes the Imp.
# Vigormortis -- Each night*, choose a player: they die. Minions you kill keep their ability & poison 1 Townsfolk neighbor. [-1 Outsider]
# Fang Gu -- Each night*, choose a player: they die. The 1st Outsider this kills becomes an evil Fang Gu & you die instead. [+1 Outsider]

# Simulate a random bag creation
from enum import Enum, auto
import random


class Townsfolk(Enum):
    """Enumeration of Townsfolk characters."""
    Chef = auto()
    Investigator = auto()
    Grandmother = auto()
    Balloonist = auto()
    SnakeCharmer = auto()
    Dreamer = auto()
    FortuneTeller = auto()
    Gambler = auto()
    Savant = auto()
    Philosopher = auto()
    Cannibal = auto()
    Amnesiac = auto()
    Ravenkeeper = auto()

    def __str__(self):
        return self.name

    @property
    def outsider_modification(self):
        if self == Townsfolk.Balloonist:
            return random.choice([0, 1])
        return 0


class Outsider(Enum):
    """Enumeration of Outsider characters."""
    Lunatic = auto()
    Drunk = auto()
    Recluse = auto()
    Sweetheart = auto()
    Mutant = auto()

    def __str__(self):
        return self.name


class Minion(Enum):
    """Enumeration of Minion characters."""
    Godfather = auto()
    Cerenovus = auto()
    PitHag = auto()
    Widow = auto()

    def __str__(self):
        return self.name

    @property
    def outsider_modification(self):
        if self == Minion.Godfather:
            return random.choice([-1, 1])
        return 0


class Demon(Enum):
    """Enumeration of Demons with their outsider modification element."""
    Imp = auto()
    Vigormortis = auto()
    FangGu = auto()

    def __str__(self):
        return self.name

    @property
    def outsider_modification(self):
        modifications = {
            Demon.Imp: 0,
            Demon.Vigormortis: -1,
            Demon.FangGu: 1,
        }
        return modifications[self]


def setup_bag(num_players):
    num_demons = 1
    base_minions = 1 if num_players < 10 else 2 if num_players < 13 else 3
    base_outsiders = (num_players - 7) % 3
    num_townsfolk = num_players - num_demons - base_minions - base_outsiders

    # Random demon
    demon = random.choice(list(Demon))
    minions = random.sample(list(Minion), base_minions)
    initial_townsfolk = random.sample(list(Townsfolk), num_townsfolk)

    # Check if Balloonist is in initial townsfolk
    balloonist_in_initial = Townsfolk.Balloonist in initial_townsfolk

    # Calculate total outsider modifications
    total_outsider_modification = demon.outsider_modification + sum(
        minion.outsider_modification for minion in minions) + sum(
        town.outsider_modification for town in initial_townsfolk)
    final_outsiders = max(0, base_outsiders + total_outsider_modification)

    # Adjust townsfolk count based on final outsider count
    final_townsfolk_count = num_players - num_demons - base_minions - final_outsiders

    # Sample townsfolk, excluding Balloonist if it was in initial
    available_townsfolk = [t for t in Townsfolk if t != Townsfolk.Balloonist] if balloonist_in_initial else list(
        Townsfolk)
    final_townsfolk = random.sample(available_townsfolk, final_townsfolk_count - (1 if balloonist_in_initial else 0))

    # Add Balloonist back if it was in initial townsfolk
    if balloonist_in_initial:
        final_townsfolk.append(Townsfolk.Balloonist)

    outsiders = random.sample(list(Outsider), final_outsiders)

    bag = [demon] + minions + final_townsfolk + outsiders
    assert len(bag) == num_players, f"Bag size {len(bag)} != player count {num_players}"
    return bag


run_count = 10000
max_tf_counts = {}
for _ in range(run_count):
    bag = setup_bag(9)
    random.shuffle(bag)
    # compute the number of townsfolk in a row
    index_demon = next(i for i, character in enumerate(bag) if isinstance(character, Demon))
    rotated_bag = bag[index_demon:] + bag[:index_demon]

    max_townsfolk_in_row = 0
    current_townsfolk_in_row = 0
    for character in bag:
        if isinstance(character, Townsfolk):
            current_townsfolk_in_row += 1
            max_townsfolk_in_row = max(max_townsfolk_in_row, current_townsfolk_in_row)
        else:
            current_townsfolk_in_row = 0

    max_tf_counts[max_townsfolk_in_row] = max_tf_counts.get(max_townsfolk_in_row, 0) + 1

print("Max townsfolk in a row distribution over {} runs:".format(run_count))
for count in sorted(max_tf_counts.keys()):
    probability = (max_tf_counts[count] / run_count) * 100
    print(f"{count} townsfolk in a row: {max_tf_counts[count]} times ({probability:.2f}%)")

