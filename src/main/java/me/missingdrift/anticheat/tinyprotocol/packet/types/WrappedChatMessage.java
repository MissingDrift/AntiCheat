package me.missingdrift.anticheat.tinyprotocol.packet.types;

import lombok.Getter;
import me.missingdrift.anticheat.tinyprotocol.api.NMSObject;
import me.missingdrift.anticheat.tinyprotocol.api.ProtocolVersion;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.Reflections;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types.WrappedClass;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types.WrappedField;
import org.bukkit.entity.Player;


@Getter
public class WrappedChatMessage extends NMSObject {
    private static final String type = Type.CHATMESSAGE;

    private String chatMessage;
    private Object[] objects;

    private static final WrappedClass chatMessageClass = Reflections.getNMSClass("ChatMessage");
    private static final WrappedField messageField = chatMessageClass.getFieldByType(String.class, 0);
    private static final WrappedField objectsField = chatMessageClass.getFieldByType(Object[].class, 0);

    public WrappedChatMessage(String chatMessage, Object... object) {
        this.chatMessage = chatMessage;
        this.objects = object;

        if(ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.v1_16)) {
            if(object.length > 0)
                setObject(chatMessageClass.getConstructorAtIndex(1).newInstance(chatMessage, new Object[]{object}));
            else setObject(chatMessageClass.getConstructorAtIndex(0).newInstance(chatMessage));
        } else {
            setObject(chatMessageClass.getConstructorAtIndex(0).newInstance(chatMessage, new Object[]{object}));
        }
    }

    public WrappedChatMessage(String chatMessage) {
        this(chatMessage, new Object[0]);
    }

    public WrappedChatMessage(Object object) {
        super(object);
    }

    @Override
    public void process(Player player, ProtocolVersion version) {
        chatMessage = fetch(messageField);
        objects = fetch(objectsField);
    }

    @Override
    public void updateObject() {
        messageField.set(getObject(), chatMessage);
        objectsField.set(getObject(), objects);
    }
}