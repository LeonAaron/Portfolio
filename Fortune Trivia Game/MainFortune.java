package GiftOfFortune;

import java.util.ArrayList;

public class MainFortune {

    public static void main(String[] args) {
        //Main file to run fortune game
        
        ArrayList<QuestionFortune> questions = createQuestions();

        //Choice to play with specific, all -> (No input for category), or random categories -> (Question.generateRandomCategory())
        FortuneGame game = new FortuneGame(questions, 3, 10, 0, "Phrase");
        game.play();
    }


    public static ArrayList<QuestionFortune> createQuestions() {

        //Creates questions of Phrase Category
        new QuestionFortune("A common greeting.", "HAVE A GOOD DAY", "Phrase");
        new QuestionFortune("A saying that means something is very simple to do.", "EASY AS PIE", "Phrase");
        new QuestionFortune("A common saying often used in stressful situations.", "TAKE IT EASY", "Phrase");
        new QuestionFortune("Often used when someone is in a hurry.", "TIME IS MONEY", "Phrase");
        new QuestionFortune("A phrase used to encourage someone to be more relaxed.", "TAKE A BREATH", "Phrase");
        new QuestionFortune("A phrase used when you don't understand something.", "I'M LOST FOR WORDS", "Phrase");
        new QuestionFortune("A phrase meaning a difficult task is made easier.", "A PIECE OF CAKE", "Phrase");
        new QuestionFortune("Used to express gratitude.", "THANK YOU VERY MUCH", "Phrase");
        new QuestionFortune("A phrase to describe being out of luck.", "DOWN ON YOUR LUCK", "Phrase");
        new QuestionFortune("A phrase used when you need a break.", "NEED A BREATHER", "Phrase");
        new QuestionFortune("A phrase sometimes used to express surprise.", "BLOWN AWAY", "Phrase");
        new QuestionFortune("Means to do something without preparation.", "ON A WHIM", "Phrase");
        new QuestionFortune("A phrase that means to stop trying to control something.", "LET IT BE", "Phrase");
        new QuestionFortune("A phrase meaning to make the most of the situation.", "SEIZE THE DAY", "Phrase");
        new QuestionFortune("A phrase that means to do something just in time.", "IN THE NICK OF TIME", "Phrase");
        new QuestionFortune("Used when someone is feeling sad.", "FEELING BLUE", "Phrase");
        new QuestionFortune("A phrase meaning to not worry about things.", "DON'T SWEAT IT", "Phrase");
        new QuestionFortune("A phrase meaning to be excited and eager.", "BUBBLING WITH EXCITEMENT", "Phrase");
        new QuestionFortune("A phrase meaning to work hard and persist despite difficulties.", "BITE THE BULLET", "Phrase");


        //Movie Title Category
        new QuestionFortune("A blockbuster movie featuring dinosaurs.", "JURASSIC PARK", "Movie Title");
        new QuestionFortune("A thrilling space adventure.", "STAR WARS", "Movie Title");
        new QuestionFortune("A journey through time and space.", "BACK TO THE FUTURE", "Movie Title");
        new QuestionFortune("A magical and adventurous movie about a young wizard at a magical school.", "HARRY POTTER", "Movie Title");
        new QuestionFortune("A dark and thrilling movie featuring a superhero who fights crime in Gotham.", "THE DARK KNIGHT", "Movie Title");
        new QuestionFortune("A dramatic and tragic movie about a ship sinking after hitting an iceberg.", "TITANIC", "Movie Title");
        new QuestionFortune("A heartwarming and animated movie about a king of the African savannah.", "THE LION KING", "Movie Title");
        new QuestionFortune("A fun and imaginative movie where toys come to life when their owner is not around.", "TOY STORY", "Movie Title");
        new QuestionFortune("A thrilling and action-packed movie about a giant ape climbing a skyscraper.", "KING KONG", "Movie Title");
        new QuestionFortune("A clever and suspenseful movie about a group of thieves who pull off a heist.", "OCEAN'S ELEVEN", "Movie Title");
        new QuestionFortune("A quirky and humorous movie about a person who is forced to relive the same day over and over.", "GROUNDHOG DAY", "Movie Title");
        new QuestionFortune("An action-packed and heroic movie about a man who becomes a spider-themed superhero.", "SPIDER-MAN", "Movie Title");
        new QuestionFortune("A charming and futuristic movie about a robot who cleans up Earth.", "WALL-E", "Movie Title");
        new QuestionFortune("A gritty and intense movie about an assassin who must kill his target with his skill and wits.", "JOHN WICK", "Movie Title");
        new QuestionFortune("A gripping and dystopian movie about a young woman who becomes a warrior in a post-apocalyptic world.", "THE HUNGER GAMES", "Movie Title");
        new QuestionFortune("A classic and thought-provoking movie about a man who builds a time machine and travels through time.", "THE TIME MACHINE", "Movie Title");
        new QuestionFortune("A whimsical and magical movie about a boy who never grows up and lives in a fantastical world.", "PETER PAN", "Movie Title");
        new QuestionFortune("An adventurous and swashbuckling movie about a group of pirates searching for treasure on the high seas.", "PIRATES OF THE CARIBBEAN", "Movie Title");
        new QuestionFortune("A thrilling and action-packed movie about a group of superheroes trying to save the world.", "THE AVENGERS", "Movie Title");
    
        //Animal Category
        new QuestionFortune("A large, gray mammal with big ears and a trunk.", "ELEPHANT", "Animal");
        new QuestionFortune("A domesticated animal often kept as a pet, known for its loyalty.", "DOG", "Animal");
        new QuestionFortune("A large cat with a mane, often called the king of the jungle.", "LION", "Animal");
        new QuestionFortune("A tall animal with a long neck, commonly found in African savannas.", "GIRAFFE", "Animal");
        new QuestionFortune("A small rodent that stores food in its cheeks.", "HAMSTER", "Animal");
        new QuestionFortune("A mammal that can fly, often associated with caves.", "BAT", "Animal");
        new QuestionFortune("A marine animal known for its intelligence and playful nature.", "DOLPHIN", "Animal");
        new QuestionFortune("A flightless bird, native to Australia, known for its speed.", "EMU", "Animal");
        new QuestionFortune("A large, gray bird known for its ability to swim underwater.", "DUCK", "Animal");
        new QuestionFortune("A large, striped wild cat, native to Asia.", "TIGER", "Animal");
        new QuestionFortune("A small, furry mammal with a bushy tail, often seen in trees.", "SQUIRREL", "Animal");
        new QuestionFortune("A slow-moving reptile with a hard shell, often seen in gardens.", "TURTLE", "Animal");
        new QuestionFortune("A small insect known for its ability to jump long distances.", "GRASSHOPPER", "Animal");
        new QuestionFortune("A mammal that carries its young in a pouch.", "KANGAROO", "Animal");
        new QuestionFortune("A reptile known for its ability to change color to blend in.", "CHAMELEON", "Animal");
        new QuestionFortune("A large, cold-blooded predator often found in swamps and rivers.", "CROCODILE", "Animal");
        new QuestionFortune("A small, nocturnal primate with large eyes, native to Madagascar.", "LEMUR", "Animal");
        new QuestionFortune("A small, striped feline known for its nocturnal behavior and distinctive scent.", "SKUNK", "Animal");
        new QuestionFortune("A large, woolly mammal that provides milk and meat, often kept on farms.", "COW", "Animal");
        new QuestionFortune("A creature known for its ability to spin webs and catch prey.", "SPIDER", "Animal");

        //Country Category
        new QuestionFortune("A country known for its pyramids and ancient civilization.", "EGYPT", "Country");
        new QuestionFortune("The land of the rising sun, known for its advanced technology and cherry blossoms.", "JAPAN", "Country");
        new QuestionFortune("A vast country with kangaroos, the Great Barrier Reef, and the Outback.", "AUSTRALIA", "Country");
        new QuestionFortune("A country in South America known for its rainforests and the Amazon River.", "BRAZIL", "Country");
        new QuestionFortune("A country famous for its historical landmarks like the Eiffel Tower and the Louvre.", "FRANCE", "Country");
        new QuestionFortune("The largest country in the world, known for its vast landscapes and Siberian tundra.", "RUSSIA", "Country");
        new QuestionFortune("A country in North America known for its maple syrup and winter sports.", "CANADA", "Country");
        new QuestionFortune("A country famous for its ancient temples, including Angkor Wat.", "CAMBODIA", "Country");
        new QuestionFortune("A European country known for its windmills, tulips, and wooden clogs.", "NETHERLANDS", "Country");
        new QuestionFortune("A country in Africa known for its diverse wildlife and safaris.", "KENYA", "Country");
        new QuestionFortune("A South Asian country famous for its beaches, tea, and colonial history.", "SRI LANKA", "Country");
        new QuestionFortune("A Mediterranean island nation known for its ancient history and stunning coastline.", "GREECE", "Country");
        new QuestionFortune("A Scandinavian country known for its fjords, Vikings, and high quality of life.", "NORWAY", "Country");
        new QuestionFortune("A country in Western Europe known for its beer, waffles, and chocolates.", "BELGIUM", "Country");
        new QuestionFortune("A country in the Middle East known for its oil reserves and modern cities like Dubai.", "UNITED ARAB EMIRATES", "Country");
        new QuestionFortune("A country in South America known for its Tango music and beef exports.", "ARGENTINA", "Country");
        new QuestionFortune("A landlocked country in Central Asia known for its nomadic culture and mountains.", "KAZAKHSTAN", "Country");
        new QuestionFortune("A country known for its royal family, Big Ben, and the English Channel.", "UNITED KINGDOM", "Country");
        new QuestionFortune("A country famous for its iconic pyramids, mummies, and the Nile River.", "EGYPT", "Country");
        new QuestionFortune("A country in the Caribbean known for its beaches, reggae music, and vibrant culture.", "JAMAICA", "Country");

        //What Are You Doing
        new QuestionFortune("Turning pages for information or enjoyment.", "READING", "What Are You Doing?");
        new QuestionFortune("Combining ingredients to make a meal.", "COOKING", "What Are You Doing?");
        new QuestionFortune("Staring at a screen for entertainment.", "WATCHING TV", "What Are You Doing?");
        new QuestionFortune("Moving your body to stay fit.", "EXERCISING", "What Are You Doing?");
        new QuestionFortune("Controlling a vehicle on the road.", "DRIVING", "What Are You Doing?");
        new QuestionFortune("Looking for something specific.", "SEARCHING", "What Are You Doing?");
        new QuestionFortune("Speaking to a group of people.", "GIVING A SPEECH", "What Are You Doing?");
        new QuestionFortune("Eating out at a restaurant.", "DINING OUT", "What Are You Doing?");
        new QuestionFortune("Having a discussion with someone.", "CONVERSING", "What Are You Doing?");
        new QuestionFortune("Talking informally with friends.", "CHATTING", "What Are You Doing?");
        new QuestionFortune("Paying attention to a sound or music.", "LISTENING", "What Are You Doing?");
        new QuestionFortune("Walking slowly for leisure.", "STROLLING", "What Are You Doing?");
        new QuestionFortune("Looking at something closely.", "WATCHING", "What Are You Doing?");
        new QuestionFortune("Lying in the sun to tan.", "SUNBATHING", "What Are You Doing?");
        new QuestionFortune("Inputing words on a device.", "TYPING", "What Are You Doing?");
        new QuestionFortune("Making your surroundings tidy.", "CLEANING", "What Are You Doing?");
        new QuestionFortune("Discovering new places outdoors.", "EXPLORING", "What Are You Doing?");
        new QuestionFortune("Clearing your mind in silence.", "MEDITATING", "What Are You Doing?");
        new QuestionFortune("Making a hot drink to wake up.", "BREWING COFFEE", "What Are You Doing?");
        new QuestionFortune("Focusing on a task or project.", "WORKING", "What Are You Doing?");

        //Returns ArrayList of all questions
        return QuestionFortune.allQuestions;
    }
}
