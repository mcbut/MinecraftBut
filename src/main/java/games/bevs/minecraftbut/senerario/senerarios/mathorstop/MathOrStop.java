package games.bevs.minecraftbut.senerario.senerarios.mathorstop;

import com.google.common.collect.Lists;
import games.bevs.minecraftbut.commons.utils.CC;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MathOrStop extends Senerario
{
    /**
     * Time before question has to be answered
     */
    @Optional
    private long mathSprintSeconds = 30l;
    @Optional
    private long secondsLeft = 0;

    private int grade = 0;
    private int questionIndex = 0;

    //Grade, Questions
    private HashMap<Integer, List<Question>> questions;

    public MathOrStop(ButWorld butWorld)
    {
        super("Math or Stop", butWorld, Material.BARRIER, new String[] {"Answer the math questions or rip"}, "Sprock");

        populateQuestions();
    }

    public void populateQuestions()
    {
        this.questions = new HashMap<>();

        //generate baby grade questions
        this.addQuestion(0, new Question("How many lines are there: ////", "4"));
        this.addQuestion(0, new Question("How many lines are there: //////", "6"));
        this.addQuestion(0, new Question("How many lines are there: // + ///", "5"));
        this.addQuestion(0, new Question("How many lines are there: // - /", "1"));

        //generate first grade questions
        for(int i = 0; i < 10; i++)
        {
            int value1 = MathUtils.getRandom().nextInt(10);
            int value2 = MathUtils.getRandom().nextInt(10);
            this.addQuestion(1, new Question(value1 + " + " + value2, (value1 + value2) + ""));
        }

        //generate second grade questions
        for(int i = 0; i < 10; i++)
        {
            int value1 = MathUtils.getRandom().nextInt(10);
            int value2 = MathUtils.getRandom().nextInt(10);
            this.addQuestion(2, new Question(value1 + " - " + value2, (value1 - value2) + ""));
        }

        //generate third grade questions
        for(int i = 0; i < 10; i++)
        {
            int value1 = MathUtils.getRandom().nextInt(100);
            int value2 = MathUtils.getRandom().nextInt(100);
            this.addQuestion(3, new Question(value1 + " - " + value2, (value1 - value2) + ""));
        }


        //generate fourth grade questions
        for(int i = 0; i < 10; i++)
        {
            int value1 = MathUtils.getRandom().nextInt(10);
            int value2 = MathUtils.getRandom().nextInt(10);
            this.addQuestion(4, new Question(value1 + " * " + value2, (value1 * value2) + ""));
        }

        //generate fith grade questions
        this.addQuestion(5, new Question( "1 / 2", "0.5"));
        this.addQuestion(5, new Question( "1 / 4", "0.25"));
        this.addQuestion(5, new Question( "1 / 10", "0.1"));
        this.addQuestion(5, new Question( "1 / 20", "0.05"));
        this.addQuestion(5, new Question( "1 / 5", "0.2"));
        this.addQuestion(5, new Question( "3 / 10", "0.3"));


        //generate sixth grade questions
        this.addQuestion(6, new Question("2^4", "16"));
        this.addQuestion(6, new Question("5^3", "125"));
        this.addQuestion(6, new Question("7^2", "49"));
        this.addQuestion(6, new Question("3^2", "9"));
        this.addQuestion(6, new Question("301^1", "301"));
        this.addQuestion(6, new Question("25531^0", "1"));

        //seven grade
        this.addQuestion(7, new Question("2^7/2^2", "32"));
        this.addQuestion(7, new Question("2^4/2^2", "4"));
        this.addQuestion(7, new Question("2^1/2^2", "0.5"));

        //Eight grade
        this.addQuestion(8, new Question("6 / 2(1 + 2)", "9"));
        this.addQuestion(8, new Question("(x + 8) / 2 = 10", "12"));
        this.addQuestion(8, new Question("sin(pi)", "1"));
        this.addQuestion(8, new Question("cos(pi)", "-1"));
        this.addQuestion(8, new Question("lim(2/x + 1)", "1"));

        for(int grade = 0; grade < 9; grade++)
        {
            shuffleGradeQuestions(grade);
        }
    }


    @Override
    protected void onStart()
    {
        this.secondsLeft = this.mathSprintSeconds;
        this.questionIndex = 0;
        this.grade = 0;

        this.repeat(() -> {
            if(this.secondsLeft == 0)
            {
                Bukkit.getServer().shutdown();
                return;
            }


            Question question = this.getCurrentQuestion();
            Bukkit.broadcastMessage(CC.iGray + "Teacher (" + this.secondsLeft + "): " + CC.gray + question.question);

            float precentage = (float) this.secondsLeft / (float) this.mathSprintSeconds;

            this.secondsLeft--;
        }, 20l);
    }

    @Override
    protected void onFinish()
    {

    }

    private void addQuestion(int grade, Question question)
    {
        List<Question> questionList = this.questions.getOrDefault(grade, Lists.newArrayList());

        questionList.add(question);


        this.questions.put(grade, questionList);
    }

    public int getSizeInGrade(int grade)
    {
        return this.questions.get(grade).size();
    }

    private void shuffleGradeQuestions(int grade)
    {
        List<Question> questionList = this.questions.getOrDefault(grade, Lists.newArrayList());

        Collections.shuffle(questionList);

        this.questions.put(grade, questionList);
    }

    public void correct(Player player)
    {
        this.secondsLeft = this.mathSprintSeconds;
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);
    }

    public void wrong(Player player)
    {

        int punishment = MathUtils.getRandom().nextInt(5);
        if(punishment == 0)
        {
            for(int i = 0; i < 5; i++)
                player.getWorld().spawn(player.getLocation(), Creeper.class);
        }
        else if(punishment == 1)
        {
            player.teleport(player.getLocation().add(0, -2, 0));
        }
        else if(punishment == 2)
        {
            player.setWalkSpeed(0.01f);
            this.delay(() -> {
                player.setWalkSpeed(0.2f);
            }, 20l * 10l);
        }
        else if(punishment == 3)
        {
            player.getWorld().strikeLightning(player.getLocation());
        }
        else if(punishment == 4)
        {
            ItemStack dirty = new ItemStack(Material.DEAD_BUSH, 64);
            dirty.setAmount(64);

            for(int sec = 0; sec < 5; sec++)
                this.delay(() -> {
                    for(int i = 0; i < 5; i++)
                        player.getWorld().dropItemNaturally(player.getLocation(), dirty);
                }, 20l * sec);

        }
        else {
            Vector vector = player.getEyeLocation().getDirection();

            vector = vector.multiply(10);
            vector.setY(2.1);

            player.setVelocity(vector);
        }

        player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1f, 1f);
    }

    public Question getCurrentQuestion()
    {
        return this.questions.get(this.grade).get(questionIndex);
    }

    public void guessToQuestion(Player player, String guess)
    {
        Question question = getCurrentQuestion();
        if(question.answer.equalsIgnoreCase(guess.trim()))
        {
            //correct
            this.correct(player);
            //Next Question

            this.questionIndex++;
            if(this.getSizeInGrade(this.grade) > this.questionIndex)
            {
                this.questionIndex = 0;
                this.grade++;
            }
        }
        else
        {
            //Wrong
            this.wrong(player);
        }
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        String message = e.getMessage();

        //lazy
        try
        {
            int value = Integer.parseInt(message);
            this.guessToQuestion(player, message);
        } catch(Exception ext) { }

        //lazy
        try
        {
            double value = Double.parseDouble(message);
            this.guessToQuestion(player, message);
        } catch(Exception ext) { }
    }


    @AllArgsConstructor
    class Question
    {
        public String question;
        public String answer;
    }
}
