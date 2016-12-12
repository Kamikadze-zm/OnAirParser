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
import ru.kamikadze_zm.onair.command.WaitTime;
import ru.kamikadze_zm.onair.command.WaitTimeActive;

public class Parser {

    private static final Logger LOG = LogManager.getLogger(Parser.class);

    public static List<Command> parse(File file) {
        if (!file.getName().endsWith(".air")) {
            throw new OnAirParserException("Неверное расширение файла. Требуется *.air");
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()),
                    Charset.forName("cp1251"));
            return parse(lines);
        } catch (IOException e) {
            LOG.warn("Cannot open file: " + file.getAbsolutePath(), e);
            throw new OnAirParserException("Не удалось открыть файл: " + file.getAbsolutePath());
        }
    }

    public static List<Command> parse(List<String> lines) {
        List<Command> commands = new ArrayList<>();
        for (String l : lines) {
            if (l.isEmpty()) {
                continue;
            }
            CommandKey commandKey = Command.parseCommandKey(l);
            Command command;
            switch (commandKey) {
                case COMMENT:
                    command = new Comment(l);
                    break;
                case MOVIE:
                    command = new Movie(l);
                    break;
                case PAUSE:
                    command = new Pause(l);
                    break;
                case SWITCH_SHEDULE:
                    command = new SwitchShedule();
                    break;
                case TITLE_MOVIE:
                    command = new TitleMovie(l);
                    break;
                case TITLE_OBJ_LOAD:
                    command = new TitleObjLoad(l);
                    break;
                case TITLE_OBJ_OFF:
                    command = new TitleObjOff(l);
                    break;
                case TITLE_OBJ_ON:
                    command = new TitleObjOn(l);
                    break;
                case TITLING_ON:
                    command = new TitlingOn();
                    break;
                case WAIT_TIME:
                    command = new WaitTime(l);
                    break;
                case WAIT_TIME_ACTIVE:
                    command = new WaitTimeActive(l);
                    break;
                case MARK_START:
                    command = new MarkStart(l);
                    break;
                case MARK_STOP:
                    command = new MarkStop(l);
                    break;
                default:
                    command = new UnknownCommand(l);
            }
            commands.add(command);
        }
        return commands;
    }

    public static List<String> buildSchedule(List<Command> commands) {
        List<String> schedule = new ArrayList<>();
        for (Command c : commands) {
            schedule.add(c.toSheduleRow());
        }
        return schedule;
    }
}
