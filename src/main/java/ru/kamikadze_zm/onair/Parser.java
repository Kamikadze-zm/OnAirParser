package ru.kamikadze_zm.onair;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kamikadze_zm.onair.command.Command;
import ru.kamikadze_zm.onair.command.Command.CommandKey;
import ru.kamikadze_zm.onair.command.Comment;
import ru.kamikadze_zm.onair.command.MarkStart;
import ru.kamikadze_zm.onair.command.MarkStop;
import ru.kamikadze_zm.onair.command.Movie;
import ru.kamikadze_zm.onair.command.Pause;
import ru.kamikadze_zm.onair.command.SwitchShedule;
import ru.kamikadze_zm.onair.command.TitleMovie;
import ru.kamikadze_zm.onair.command.TitleObjLoad;
import ru.kamikadze_zm.onair.command.TitleObjOff;
import ru.kamikadze_zm.onair.command.TitleObjOn;
import ru.kamikadze_zm.onair.command.TitlingOn;
import ru.kamikadze_zm.onair.command.UnknownCommand;
import ru.kamikadze_zm.onair.command.WaitFollow;
import ru.kamikadze_zm.onair.command.WaitOperator;
import ru.kamikadze_zm.onair.command.WaitTime;
import ru.kamikadze_zm.onair.command.WaitTimeActive;
import ru.kamikadze_zm.onair.command.parameter.Duration;

/**
 *
 * Парсер текстового расписания *.air
 */
public class Parser {

    private static final Logger LOG = LogManager.getLogger(Parser.class);

    private Parser() {
    }

    /**
     * Парсит файл расписания
     *
     * @param file файл расписания *.air
     * @return Список команд
     * @throws OnAirParserException в случаях неверного расширения файла или ошибок чтения файла
     */
    public static List<Command> parse(File file) throws OnAirParserException {
        return parse(getLinesFromFile(file));
    }

    /**
     * Парсит список строк расписания
     *
     * @param lines список команд в виде строк
     * @return Список команд
     */
    public static List<Command> parse(List<String> lines) {
        List<Command> commands = new ArrayList<>();
        for (String l : lines) {
            //пропуск пустых строк
            if (l.isEmpty()) {
                continue;
            }
            commands.add(parseCommand(l));
        }
        return commands;
    }

    /**
     * Парсит файл расписания в список команд со временем их старта
     *
     * @param file файл расписания *.air
     * @return Список команд со временем их старта
     * @throws OnAirParserException в случаях неверного расширения файла или ошибок чтения файла
     */
    public static List<CommandTime> parseWithTime(File file) throws OnAirParserException {
        return parseWithTime(getLinesFromFile(file));
    }

    /**
     * Парсит список строк расписания в список команд со временем их старта
     *
     * @param lines список команд в виде строк
     * @return Список команд со временем их старта
     */
    public static List<CommandTime> parseWithTime(List<String> lines) {
        List<CommandTime> commands = new ArrayList<>();
        Duration currTime = new Duration();
        for (String l : lines) {
            //пропуск пустых строк
            if (l.isEmpty()) {
                continue;
            }
            Command c = parseCommand(l);
            if (CommandKey.WAIT_TIME == c.getCommandKey() || CommandKey.WAIT_TIME_ACTIVE == c.getCommandKey()) {
                currTime = ((WaitTime) c).getTime();
            }
            commands.add(new CommandTime(c, currTime));
            currTime = currTime.add(c.getDuration());
        }
        return commands;
    }

    /**
     * Формирует список строк расписания
     *
     * @param commands список команд
     * @return список строк расписания
     */
    public static List<String> buildSchedule(List<Command> commands) {
        List<String> schedule = new ArrayList<>();
        for (Command c : commands) {
            schedule.add(c.toSheduleRow());
        }
        return schedule;
    }

    private static List<String> getLinesFromFile(File file) throws OnAirParserException {
        if (!file.getName().endsWith(".air")) {
            throw new OnAirParserException("Неверное расширение файла: " + file.getName() + ". Требуется *.air");
        }

        try {
            //читаем файл построчно в виндовс кодировке
            return Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.forName("cp1251"));
        } catch (IOException e) {
            LOG.warn("Cannot open file: " + file.getAbsolutePath(), e);
            throw new OnAirParserException("Не удалось открыть файл: " + file.getAbsolutePath());
        }
    }

    private static Command parseCommand(String line) {
        //парсим ключ команды
        CommandKey commandKey = Command.parseCommandKey(line);
        Command command;
        //создаём соответствующую команду
        switch (commandKey) {
            case COMMENT:
                command = new Comment(line);
                break;
            case MOVIE:
                command = new Movie(line);
                break;
            case PAUSE:
                command = new Pause(line);
                break;
            case SWITCH_SHEDULE:
                command = new SwitchShedule();
                break;
            case TITLE_MOVIE:
                command = new TitleMovie(line);
                break;
            case TITLE_OBJ_LOAD:
                command = new TitleObjLoad(line);
                break;
            case TITLE_OBJ_OFF:
                command = new TitleObjOff(line);
                break;
            case TITLE_OBJ_ON:
                command = new TitleObjOn(line);
                break;
            case TITLING_ON:
                command = new TitlingOn();
                break;
            case WAIT_TIME:
                command = new WaitTime(line);
                break;
            case WAIT_TIME_ACTIVE:
                command = new WaitTimeActive(line);
                break;
            case MARK_START:
                command = new MarkStart(line);
                break;
            case MARK_STOP:
                command = new MarkStop(line);
                break;
            case WAIT_OPERATOR:
                command = new WaitOperator(line);
                break;
            case WAIT_FOLLOW:
                command = new WaitFollow(line);
                break;
            default:
                command = new UnknownCommand(line);
        }
        return command;
    }

}
