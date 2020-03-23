package no.helponline.Utils;

import no.helponline.Utils.Enum.Role;
import no.helponline.Utils.Enum.Zone;

import java.util.HashMap;
import java.util.UUID;

public class Guild {
    String name;
    Zone zone;
    UUID guildUUID;
    boolean invited_only, friendly_fire;
    HashMap<UUID, Role> members = new HashMap<>();
    Role permissionInvite;
    private int chunks;
    boolean open;

    /**
     * Creating a new Guild from the command
     *
     * @param name String name of the Guild
     * @param zone Zone the Guild belongs to
     * @param guildUUID Guilds unique ID
     * @param invited_only boolean Only invited Players may join
     * @param friendly_fire boolean Members may damage each other inside the Guild
     * @param playerUUID UUID of the Player creating the Guild
     * @param role Role of the Player creating the Guild
     */
    public Guild(String name, Zone zone, UUID guildUUID, boolean invited_only, boolean friendly_fire, UUID playerUUID, Role role) {
        this.name = name;
        this.zone = zone;
        this.guildUUID = guildUUID;
        this.invited_only = invited_only;
        this.friendly_fire = friendly_fire;
        this.permissionInvite = Role.Members;
        this.members.put(playerUUID, role);
        this.open = false;
    }

    /**
     * Creating default zones
     *
     * @param name
     * @param zone
     * @param guildUUID
     * @param invited_only
     * @param friendly_fire
     */
    public Guild(String name, Zone zone, UUID guildUUID, boolean invited_only, boolean friendly_fire) {
        this.name = name;
        this.zone = zone;
        this.guildUUID = guildUUID;
        this.invited_only = invited_only;
        this.friendly_fire = friendly_fire;
        this.permissionInvite = Role.Members;
        this.open = false;
    }

    /**
     * Loading from Database
     *
     * @param guildUUID
     * @param name
     * @param zone
     * @param invite_only
     * @param friendly_fire
     * @param invite_permission
     * @param open
     * @param members
     */
    public Guild(UUID guildUUID, String name, Zone zone, boolean invite_only, boolean friendly_fire, Role invite_permission, boolean open, HashMap<UUID, Role> members) {
        this.guildUUID = guildUUID;
        this.name = name;
        this.zone = zone;
        this.invited_only = invite_only;
        this.friendly_fire = friendly_fire;
        this.permissionInvite = invite_permission;
        this.members = members;
        this.open = open;
    }

    public UUID getGuildUUID() {
        return guildUUID;
    }

    public String getName() {
        return name;
    }

    public Zone getZone() {
        return zone;
    }

    public HashMap<UUID, Role> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInvitedOnly(boolean bol) {
        this.invited_only = bol;
    }

    public void setFriendlyFire(boolean bol) {
        this.friendly_fire = bol;
    }

    public Role getPermissionInvite() {
        return permissionInvite;
    }

    public boolean getInvitedOnly() {
        return invited_only;
    }

    public int getChunks() {
        return chunks;
    }

    public void addChunks() {
        chunks++;
    }

    public void removeChunks() {
        chunks--;
    }

    public boolean getFriendlyFire() {
        return friendly_fire;
    }

    public void setMaster(UUID member) {
        members.put(member,Role.Master);
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean getOpen() {
        return this.open;
    }
}
