package me.mchiappinam.pdghlobbybussola;

import java.util.ArrayList;
import java.util.List;

import me.mchiappinam.pdghlobbybussola.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Main extends JavaPlugin implements PluginMessageListener {
	public static Inventory menu = Bukkit.createInventory(null, 45, "§e» §9§lServidores PDGH §e«");
	public int playerscreativo = 0;
	public int playersdayz2 = 0;
	public int playersdayzhardcore = 0;
	public int playersfullpvp = 0;
	public int playershg1 = 0;
	public int playershg2 = 0;
	public int playerspvp = 0;
	public int playerslobby = 0;
	public int playersaedf = 0;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
	    getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	    setScoreBoard();
		getServer().getConsoleSender().sendMessage("§3[PDGHLobbyBussola] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHLobbyBussola] §2Acesse: http://pdgh.com.br/");
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHLobbyBussola] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHLobbyBussola] §2Acesse: http://pdgh.com.br/");
	}
	
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
       
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
       
        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF();
            int playerCount = in.readInt();
            if(server.contains("creativo")) {
            	playerscreativo=playerCount;
            }else if(server.contains("dayz2")) {
            	playersdayz2=playerCount;
            }else if(server.contains("dayzhardcore")) {
            	playersdayzhardcore=playerCount;
            }else if(server.contains("fullpvp")) {
            	playersfullpvp=playerCount;
            }else if(server.contains("hg1")) {
            	playershg1=playerCount;
            }else if(server.contains("hg2")) {
            	playershg2=playerCount;
            }else if(server.contains("pvp")) {
            	playerspvp=playerCount;
            }else if(server.contains("aedf")) {
            	playersaedf=playerCount;
            }
        }
       
    }
	
	public void setScoreBoard() {
		final Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
		final Objective stats = score.registerNewObjective("1", "2");
		stats.setDisplaySlot(DisplaySlot.SIDEBAR);
		stats.setDisplayName("§3§l» PDGH Network « §e§l(0)");

		final Score creativo = stats.getScore(Bukkit.getOfflinePlayer("§e§lCreativo§c:"));
		final Score dayz2 = stats.getScore(Bukkit.getOfflinePlayer("§e§lDayZ 2§c:"));
		final Score dayzhardcore = stats.getScore(Bukkit.getOfflinePlayer("§a§lDayZ HC.§c:"));
		final Score fullpvp = stats.getScore(Bukkit.getOfflinePlayer("§e§lFull PvP§c:"));
		final Score hg1 = stats.getScore(Bukkit.getOfflinePlayer("§e§lHG 1§c:"));
		final Score hg2 = stats.getScore(Bukkit.getOfflinePlayer("§e§lHG 2§c:"));
		final Score pvp = stats.getScore(Bukkit.getOfflinePlayer("§e§lPvP§c:"));
		final Score lobby = stats.getScore(Bukkit.getOfflinePlayer("§e§lLobby§c:"));
		final Score aedf = stats.getScore(Bukkit.getOfflinePlayer("§5§l✲A.E.D.F.§c:"));

		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			public void run() {
				getCount("creativo");
				getCount("dayz2");
				getCount("dayzhardcore");
				getCount("fullpvp");
				getCount("hg1");
				getCount("hg2");
				getCount("pvp");
				getCount("aedf");
				playerslobby=getServer().getOnlinePlayers().length;
				int total = playerscreativo+playersdayz2+playersdayzhardcore+playersfullpvp+playershg1+playershg2+playerspvp+playerslobby+playersaedf;
				stats.setDisplayName("§3§l» PDGH Network « §e§l("+total+")");
				creativo.setScore(playerscreativo);
				dayz2.setScore(playersdayz2);
				dayzhardcore.setScore(playersdayzhardcore);
				fullpvp.setScore(playersfullpvp);
				hg1.setScore(playershg1);
				hg2.setScore(playershg2);
				pvp.setScore(playerspvp);
				lobby.setScore(playerslobby);
				aedf.setScore(playersaedf);
			}
		}, 0, 4*20);
		
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			public void run() {
				for(Player p : getServer().getOnlinePlayers())
					p.setScoreboard(score);
			}
		}, 0, 1*20);
	}
   
    public void getCount(String server) {
       
        if (server == null) {
            server = "ALL";
        }
       
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);

        getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
        
    }
   
    public ItemStack bussola() {
		ItemStack a0 = new ItemStack(Material.COMPASS, 1);
		List<String> l0 = new ArrayList<String>();
	    ItemMeta b0 = a0.getItemMeta();
	    b0.setDisplayName("§aClique para escolher o servidor");
	    b0.setLore(l0);
	    a0.setItemMeta(b0);
		return a0;
    }
   
    public ItemStack cerca() {
		ItemStack a0 = new ItemStack(Material.IRON_FENCE, 1);
		List<String> l0 = new ArrayList<String>();
	    ItemMeta b0 = a0.getItemMeta();
	    b0.setDisplayName(" ");
	    b0.setLore(l0);
	    a0.setItemMeta(b0);
		return a0;
    }
   
    public ItemStack book() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta)book.getItemMeta();
        List<String> pages = new ArrayList<String>();
        pages.add("§9\nOlá, seja bem vindo aos servidores da\n§9§lPDGH Network§9\n\n\n§c§l>> Guia Básico <<\n\n§3Para entrar em algum servidor da §lPDGH§3, clique na bússola que se encontra no seu inventário.");
        pages.add("§3\nDepois de entrar em algum servidor, você terá diversos recursos.\n\nTemos em nossos servidores, um sistema de ajuda básica ao jogador. Para ver como funciona, digite o comando §l/ajuda");
        pages.add("§c§l>> Links Uteis <<\n\n§3Site: pdgh.com.br\n\nFórum: pdgh.com.br/forum\n\nChangeLog: pdgh.com.br/changelog\n\nTeamSpeak: pdgh.com.br/teamspeak");
        pages.add("§c§l> Algumas Regras <\n\n§3Abuso de BUG = ban permanente.\n\nConta fake = ban permanente em ambas contas.\n\nDivulgação = ban permanente.");
        pages.add("§3Uso de hacker = ban permanente.\n\nOfensa = ban por 1200 minutos.\n\nFlood = ban por 600 minutos.\n\nVeja todas as regras no site: pdgh.com.br/regras");
        pages.add("§9\nTemos mais servidores do que esses que se encontram na lista da bússola. Veja todos os nossos servidores em nosso site: §lwww.pdgh.com.br§9\n\nTenha um excelente jogo!");
        meta.setTitle("§aBem vindo aos nossos servidores§f");
        meta.setAuthor("§c§lPDGH Minecraft Servers");
        meta.setPages(pages);
        book.setItemMeta(meta);
		return book;
    }

	protected void tags() {
		ItemStack a0 = new ItemStack(Material.DIAMOND_BLOCK, 1);
	    ItemMeta b0 = a0.getItemMeta();
		List<String> l0 = new ArrayList<String>();
	    b0.setDisplayName("§a["+playerscreativo+"] §e§lCreativo");
	    l0.add(" ");
	    l0.add("§6Clique para entrar no servidor");
	    b0.setLore(l0);
	    a0.setItemMeta(b0);
	    menu.setItem(23, a0);

		ItemStack a1 = new ItemStack(Material.IRON_AXE, 1);
	    ItemMeta b1 = a1.getItemMeta();
	    b1.setDisplayName("§a["+playersdayz2+"] §e§lDay§c§lZ§e§l2");
	    b1.setLore(l0);
	    a1.setItemMeta(b1);
	    menu.setItem(12, a1);

		ItemStack a2 = new ItemStack(Material.DIAMOND_AXE, 1);
	    ItemMeta b2 = a2.getItemMeta();
		List<String> l2 = new ArrayList<String>();
	    b2.setDisplayName("§e§lDay§c§lZ§e§l3 §a(Minecraft original)");
	    l2.add(" ");
	    l2.add("§6Clique para entrar no servidor");
	    l2.add("§a» Apenas Minecraft original «");
	    b2.setLore(l2);
	    a2.setItemMeta(b2);
	    menu.setItem(14, a2);

		ItemStack a7 = new ItemStack(Material.FIRE, 1);
	    ItemMeta b7 = a7.getItemMeta();
		//List<String> l3 = new ArrayList<String>();
	    b7.setDisplayName("§a["+playersdayzhardcore+"] §a§lDay§c§lZ §a§lHardcore");
	    /**l3.add(" ");
	    l3.add("§6Clique para entrar no servidor");
	    l3.add("§a» Apenas com o AntiHack PDGH «");
	    l3.add(" ");
	    l3.add("§fAdquira seu AntiHack no site:");
	    l3.add("§fwww.pdgh.com.br/antihack");*/
	    b7.setLore(l0);
	    a7.setItemMeta(b7);
	    menu.setItem(4, a7);

		ItemStack a3 = new ItemStack(Material.DIAMOND_SWORD, 1);
	    ItemMeta b3 = a3.getItemMeta();
	    b3.setDisplayName("§a["+playersfullpvp+"] §e§lFull PvP");
	    b3.setLore(l0);
	    a3.setItemMeta(b3);
	    menu.setItem(22, a3);

		ItemStack a4 = new ItemStack(Material.MUSHROOM_SOUP, 1);
	    ItemMeta b4 = a4.getItemMeta();
	    b4.setDisplayName("§a["+playershg1+"] §e§lHunger Games §c§l1");
	    b4.setLore(l0);
	    a4.setItemMeta(b4);
	    menu.setItem(30, a4);

		ItemStack a5 = new ItemStack(Material.BOWL, 1);
	    ItemMeta b5 = a5.getItemMeta();
	    b5.setDisplayName("§a["+playershg2+"] §e§lHunger Games §c§l2");
	    b5.setLore(l0);
	    a5.setItemMeta(b5);
	    menu.setItem(32, a5);

		ItemStack a6 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
	    ItemMeta b6 = a6.getItemMeta();
	    b6.setDisplayName("§a["+playerspvp+"] §e§lPvP");
	    b6.setLore(l0);
	    a6.setItemMeta(b6);
	    menu.setItem(21, a6);

		ItemStack a8 = new ItemStack(Material.PORTAL, 1);
	    ItemMeta b8 = a8.getItemMeta();
		List<String> l8 = new ArrayList<String>();
	    b8.setDisplayName("§a["+playersaedf+"] §5§l✲✲ A Era do Futuro ✲✲");
	    l8.add(" ");
	    l8.add("§6Clique para entrar no servidor");
	    l8.add("§a» Apenas com o ModPack PDGH «");
	    l8.add(" ");
	    l8.add("§fAdquira seu ModPack no site:");
	    l8.add("§fwww.pdgh.com.br/minecraft");
	    l8.add("§fOBS: No site, clique no botão do Minecraft da Era do Futuro.");
	    b8.setLore(l8);
	    a8.setItemMeta(b8);
	    menu.setItem(40, a8);
	}

	public void sendCreativo(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("creativo");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}

	public void sendDayZ2(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("dayz2");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}

	public void sendDayZHardcore(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("dayzhardcore");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}

	public void sendFullPvP(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("fullpvp");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}

	public void sendHungerGames1(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("hg1");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}

	public void sendHungerGames2(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("hg2");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}

	public void sendPvP(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("pvp");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}

	public void sendAEDF(Player p) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("aedf");
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
	}
	
}