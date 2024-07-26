package com.telegram.bot;

import static org.telegram.abilitybots.api.objects.Locality.USER;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;
import static org.telegram.abilitybots.api.util.AbilityUtils.getChatId;

import com.telegram.bot.constants.BotCommand;
import com.telegram.bot.constants.Constants;
import com.telegram.bot.handler.MessageFactory;
import java.util.function.BiConsumer;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Flag;
import org.telegram.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class GermanoBot extends AbilityBot {

  private static final String BOT_TOKEN = "7277366517:AAEeLitCCl4pwRHpA3tFPMfN3QCA0vLVbcc";
  private static final String BOT_USERNAME = "Learn_DeutschBot";
  private final MessageFactory messageFactory;

  @Override
  public long creatorId() {
    return 1;
  }

  public GermanoBot() {
    super(BOT_TOKEN, BOT_USERNAME);
    messageFactory = new MessageFactory(sender);
  }

  public Ability startBot() {
    return Ability
        .builder()
        .name(BotCommand.START.commandName)
        .info(Constants.START_DESCRIPTION.getText())
        .locality(USER)
        .privacy(PUBLIC)
        .action(ctx -> messageFactory.start(ctx.chatId()))
        .build();
  }

  public Reply replyToButtons(){
    BiConsumer<BaseAbilityBot, Update> action = (abilityBot, upd) -> messageFactory.replyToButtons(getChatId(upd), upd.getMessage());
    return Reply.of(action, Flag.TEXT, upd -> messageFactory.isUserActive(getChatId(upd)));
  }
}