/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2jmobius.gameserver.network.serverpackets;

import org.l2jmobius.commons.network.PacketWriter;
import org.l2jmobius.gameserver.model.clan.ClanMember;
import org.l2jmobius.gameserver.network.OutgoingPackets;

/**
 * @author -Wooden-
 */
public class PledgeReceiveMemberInfo implements IClientOutgoingPacket
{
	private final ClanMember _member;
	
	public PledgeReceiveMemberInfo(ClanMember member)
	{
		_member = member;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.PLEDGE_RECEIVE_MEMBER_INFO.writeId(packet);
		packet.writeD(_member.getPledgeType());
		packet.writeS(_member.getName());
		packet.writeS(_member.getTitle()); // title
		packet.writeD(_member.getPowerGrade()); // power
		// clan or subpledge name
		if (_member.getPledgeType() != 0)
		{
			packet.writeS(_member.getClan().getSubPledge(_member.getPledgeType()).getName());
		}
		else
		{
			packet.writeS(_member.getClan().getName());
		}
		packet.writeS(_member.getApprenticeOrSponsorName()); // name of this member's apprentice/sponsor
		return true;
	}
}
