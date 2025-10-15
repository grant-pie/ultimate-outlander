<script setup>

import { onMounted, ref, watch } from 'vue'

import Card from './components/Card.vue'
import ItemsTable from './components/ItemsTable.vue';
import ContainerContentsTable from './components/ContainerContentsTable.vue';

import ItemTypes from "./data/item_types.json";

const input = ref('');
const msg = ref('');
const isDragging = ref(false)
const items = ref([]);
const containers = ref([]);

const testJournal = `[10/14/2025 06:22]  System: WorldMap loading...
[10/14/2025 06:22]  System: Loading WorldMap markers..
[10/14/2025 06:22]  System: ..userMarkers.usr (25)
[10/14/2025 06:22]  System: ..Banks_and_Healers.xml (39)
[10/14/2025 06:22]  System: ..Dungeons.xml (13)
[10/14/2025 06:22]  System: ..Dungeon_Gates.xml (413)
[10/14/2025 06:22]  System: ..Healer_Caravans.xml (42)
[10/14/2025 06:22]  System: ..Kings_Faire.xml (50)
[10/14/2025 06:22]  System: ..Moongates.xml (10)
[10/14/2025 06:22]  System: ..POI.xml (251)
[10/14/2025 06:22]  System: ..PvP_Locations.xml (79)
[10/14/2025 06:22]  System: ..Shrines.xml (9)
[10/14/2025 06:22]  System: ..Sub-Dungeons.xml (5)
[10/14/2025 06:22]  System: ..Townships.xml (10)
[10/14/2025 06:22]  System: WorldMap markers loaded (946)
[10/14/2025 06:22]  System: Welcome GawdHammer!
[10/14/2025 06:22]  GawdHammer: Legendary Backstabber
[10/14/2025 06:22]  GawdHammer: Lord GawdHammer
[10/14/2025 06:22]  a wisp: a wisp
[10/14/2025 06:22]  a great hart: a great hart
[10/14/2025 06:22]  a horse: a horse
[10/14/2025 06:22]  an orc: an orc
[10/14/2025 06:22]  a grizzly bear: a grizzly bear
[10/14/2025 06:22]  a giant rat: a giant rat
[10/14/2025 06:22]  a headless: a headless
[10/14/2025 06:22]  a crow: a crow
[10/14/2025 06:22]  a cougar: a cougar
[10/14/2025 06:22]  an eagle: an eagle
[10/14/2025 06:22]  a hind: a hind
[10/14/2025 06:22]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 06:22]  a black bear: a black bear
[10/14/2025 06:22]  a great hart: a great hart
[10/14/2025 06:22]  a pack horse: (tame)
[10/14/2025 06:22]  a pack horse: [274/2000 stones]
[10/14/2025 06:22]  a pack horse: [68/125 items]
[10/14/2025 06:22]  a pack horse: a pack horse
[10/14/2025 06:22]  a pack horse: (tame)
[10/14/2025 06:22]  a pack horse: [453/2000 stones]
[10/14/2025 06:22]  a pack horse: [125/125 items]
[10/14/2025 06:22]  a pack horse: a pack horse
[10/14/2025 06:22]  System: WorldMap loaded!
[10/14/2025 06:22]  System: You have rejoined the party.
[10/14/2025 06:22]  System: You refresh your house upon login.
[10/14/2025 06:22]  System: Opening door...
[10/14/2025 06:22]  System: As the owner, you refresh the house and its contents.
[10/14/2025 06:22]  an orc: an orc
[10/14/2025 06:23]  System: Target the container to Grab items into.
[10/14/2025 06:23]  System: Grab Bag set: 1283801044
[10/14/2025 06:23]  You see: runebook: Towns Extended
[10/14/2025 06:23]  You see: [mastercrafted by GnenrriricIDITEM]
[10/14/2025 06:23]  You see: [locked down]
[10/14/2025 06:23]  System: What do you wish to track?
[10/14/2025 06:23]  System: You will only be able to track individuals and creatures currently within this house.
[10/14/2025 06:23]  GawdHammer: You begin hunting.
[10/14/2025 06:23]  GawdHammer: Insufficient mana for this spell.
[10/14/2025 06:23]  System: Opening door...
[10/14/2025 06:23]  System: As the owner, you refresh the house and its contents.
[10/14/2025 06:23]  You see: a storage shelf
[10/14/2025 06:23]  You see: [secure]
[10/14/2025 06:23]  System: Resupply complete.
[10/14/2025 06:24]  GawdHammer: Kal Ort Por
[10/14/2025 06:24]  System: One or more of your pack animals is not allowed in this location.
[10/14/2025 06:24]  GawdHammer: You dismiss your mount.
[10/14/2025 06:24]  You see: a fire ant corpse
[10/14/2025 06:24]  You see: a scarab corpse
[10/14/2025 06:24]  You see: a skeleton guardian corpse
[10/14/2025 06:24]  You see: a skeleton guardian corpse
[10/14/2025 06:24]  You see: the remains of GrizzlyAdams
[10/14/2025 06:24]  You see: a ghoul corpse
[10/14/2025 06:24]  You see: a ghoul corpse
[10/14/2025 06:24]  You see: a necromancer corpse
[10/14/2025 06:24]  You see: a ghoul corpse
[10/14/2025 06:24]  You see: a necromancer corpse
[10/14/2025 06:24]  You see: a ghoul corpse
[10/14/2025 06:24]  You see: a scarab corpse
[10/14/2025 06:24]  You see: a necromancer corpse
[10/14/2025 06:24]  a skeletal archer: a skeletal archer
[10/14/2025 06:24]  a fire ant: a fire ant
[10/14/2025 06:24]  a ghoul: a ghoul
[10/14/2025 06:24]  System: Your skill in Tactics has increased by 78.0.  It is now 78.0.
[10/14/2025 06:24]  a scarab: a scarab
[10/14/2025 06:24]  a skeletal guardian: a skeletal guardian
[10/14/2025 06:24]  a skeletal guardian: a skeletal guardian
[10/14/2025 06:24]  You see: a necromancer corpse
[10/14/2025 06:24]  You see: a skeleton guardian corpse
[10/14/2025 06:24]  GawdHammer: -5
[10/14/2025 06:24]  You see: a lich corpse
[10/14/2025 06:24]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:24]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:24]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:24]  You see: a lich corpse
[10/14/2025 06:24]  You see: a lich corpse
[10/14/2025 06:24]  a lich: a lich
[10/14/2025 06:24]  Raven: Wilderness Hero
[10/14/2025 06:24]  Raven: [Nudist, EMBR]
[10/14/2025 06:24]  Raven: Lord Raven
[10/14/2025 06:24]  Shrapnel: (bonded)
[10/14/2025 06:24]  Shrapnel: Shrapnel
[10/14/2025 06:24]  Laserhawk: (bonded)
[10/14/2025 06:24]  Laserhawk: Laserhawk
[10/14/2025 06:24]  a fire ant: a fire ant
[10/14/2025 06:24]  a scarab: a scarab
[10/14/2025 06:24]  a skeletal guardian: a skeletal guardian
[10/14/2025 06:24]  You see: a lich corpse
[10/14/2025 06:24]  a skeletal marksman: a skeletal marksman
[10/14/2025 06:24]  You see: a skeletal marksman corpse
[10/14/2025 06:24]  a skeletal marksman: a skeletal marksman
[10/14/2025 06:24]  a scorpion: a scorpion
[10/14/2025 06:24]  a skeletal marksman: a skeletal marksman
[10/14/2025 06:24]  System: RochelleRochelle has completed the achievement: Affluent (Advanced).
[10/14/2025 06:24]  a lich: a lich
[10/14/2025 06:24]  a nightmare: a nightmare
[10/14/2025 06:24]  a cave bat: a cave bat
[10/14/2025 06:24]  a mummy: a mummy
[10/14/2025 06:24]  a sand crawler: a sand crawler
[10/14/2025 06:24]  You see: a rag witch corpse
[10/14/2025 06:24]  a sand crawler: a sand crawler
[10/14/2025 06:24]  a lich: a lich
[10/14/2025 06:24]  a lich magus: a lich magus
[10/14/2025 06:24]  You see: a rag witch corpse
[10/14/2025 06:24]  a nightmare: a nightmare
[10/14/2025 06:24]  You see: the remains of Reg Diorgen
[10/14/2025 06:24]  a mummy: a mummy
[10/14/2025 06:24]  You see: a mummy corpse
[10/14/2025 06:24]  a mummy: a mummy
[10/14/2025 06:24]  You see: a rag witch corpse
[10/14/2025 06:24]  an ossuarian skirmisher: an ossuarian skirmisher
[10/14/2025 06:24]  You see: a rag witch corpse
[10/14/2025 06:24]  You see: an ossuarian skirmisher corpse
[10/14/2025 06:24]  an ossuarian slinger: an ossuarian slinger
[10/14/2025 06:24]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:24]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:24]  [Razor]: Steps: 0
[10/14/2025 06:24]  [Razor]: Steps: 0
[10/14/2025 06:24]  System: You are no longer hindered.
[10/14/2025 06:24]  System: You have 29 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 29
[10/14/2025 06:24]  You see: an ossuarian hunter corpse
[10/14/2025 06:24]  System: You have 28 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 28
[10/14/2025 06:24]  You see: an ossuarian skirmisher corpse
[10/14/2025 06:24]  System: You have 27 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 27
[10/14/2025 06:24]  System: You have 26 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 26
[10/14/2025 06:24]  System: You have 25 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 25
[10/14/2025 06:24]  System: You have 24 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 24
[10/14/2025 06:24]  System: You have 23 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 23
[10/14/2025 06:24]  System: You have 22 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 22
[10/14/2025 06:24]  System: You have 21 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 21
[10/14/2025 06:24]  System: You have 20 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 20
[10/14/2025 06:24]  You see: a lich magus corpse
[10/14/2025 06:24]  You see: an ostard corpse
[10/14/2025 06:24]  You see: an ossuarian huntress
[10/14/2025 06:24]  System: You have 19 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 19
[10/14/2025 06:24]  an ossuarian lion warrior: an ossuarian lion warrior
[10/14/2025 06:24]  System: You have 18 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 18
[10/14/2025 06:24]  System: You have 17 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 17
[10/14/2025 06:24]  an ossuarian huntress: an ossuarian huntress
[10/14/2025 06:24]  System: You have 16 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 16
[10/14/2025 06:24]  System: You have 15 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 15
[10/14/2025 06:24]  System: You have 14 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 14
[10/14/2025 06:24]  System: You have 13 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 13
[10/14/2025 06:24]  System: You have 12 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 12
[10/14/2025 06:24]  System: You have 11 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 11
[10/14/2025 06:24]  System: You have 10 stealth steps remaining.
[10/14/2025 06:24]  Razor: [10 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 10
[10/14/2025 06:24]  System: You have 9 stealth steps remaining.
[10/14/2025 06:24]  Razor: [9 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 9
[10/14/2025 06:24]  a lich magus: a lich magus
[10/14/2025 06:24]  System: You have 8 stealth steps remaining.
[10/14/2025 06:24]  Razor: [8 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 8
[10/14/2025 06:24]  System: Xiktil has completed the achievement: Undead Slayer (Advanced).
[10/14/2025 06:24]  System: You have 7 stealth steps remaining.
[10/14/2025 06:24]  Razor: [7 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 7
[10/14/2025 06:24]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:24]  System: You will automatically stealth when out of steps.
[10/14/2025 06:24]  Razor: [Auto-stealth ready.]
[10/14/2025 06:24]  System: You have 6 stealth steps remaining.
[10/14/2025 06:24]  Razor: [6 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 6
[10/14/2025 06:24]  System: You have 5 stealth steps remaining.
[10/14/2025 06:24]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:24]  [Razor]: Steps: 5
[10/14/2025 06:24]  an ossuarian pyromancer: an ossuarian pyromancer
[10/14/2025 06:24]  System: You have 4 stealth steps remaining.
[10/14/2025 06:24]  Razor: [4 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 4
[10/14/2025 06:24]  System: You have 3 stealth steps remaining.
[10/14/2025 06:24]  Razor: [3 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 3
[10/14/2025 06:24]  System: You have 2 stealth steps remaining.
[10/14/2025 06:24]  Razor: [2 Steps...]
[10/14/2025 06:24]  [Razor]: Steps: 2
[10/14/2025 06:24]  System: You have 1 stealth steps remaining.
[10/14/2025 06:24]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:24]  [Razor]: Steps: 1
[10/14/2025 06:24]  System: You have 0 stealth steps remaining.
[10/14/2025 06:24]  System: You feel ready to continue stealthing.
[10/14/2025 06:24]  Razor: [Stop!]
[10/14/2025 06:24]  [Razor]: Steps: 0
[10/14/2025 06:24]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:24]  System: You have 23 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 23
[10/14/2025 06:24]  System: You have 22 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 22
[10/14/2025 06:24]  System: You have 21 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 21
[10/14/2025 06:24]  System: You have 20 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 20
[10/14/2025 06:24]  System: You have 19 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 19
[10/14/2025 06:24]  System: You have 18 stealth steps remaining.
[10/14/2025 06:24]  [Razor]: Steps: 18
[10/14/2025 06:25]  System: You have 17 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 17
[10/14/2025 06:25]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:25]  System: You have 16 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 16
[10/14/2025 06:25]  System: You have 15 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 15
[10/14/2025 06:25]  System: You have 14 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 14
[10/14/2025 06:25]  an ossuarian hunter: an ossuarian hunter
[10/14/2025 06:25]  System: You have 13 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 13
[10/14/2025 06:25]  an ossuarian hunter: an ossuarian hunter
[10/14/2025 06:25]  System: You have 12 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 12
[10/14/2025 06:25]  System: You have 11 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 11
[10/14/2025 06:25]  System: You have 10 stealth steps remaining.
[10/14/2025 06:25]  Razor: [10 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 10
[10/14/2025 06:25]  System: You have 9 stealth steps remaining.
[10/14/2025 06:25]  Razor: [9 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 9
[10/14/2025 06:25]  an ossuarian embalmer: an ossuarian embalmer
[10/14/2025 06:25]  System: You have 8 stealth steps remaining.
[10/14/2025 06:25]  Razor: [8 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 8
[10/14/2025 06:25]  System: You have 7 stealth steps remaining.
[10/14/2025 06:25]  Razor: [7 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 7
[10/14/2025 06:25]  System: You have 6 stealth steps remaining.
[10/14/2025 06:25]  Razor: [6 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 6
[10/14/2025 06:25]  System: You have 5 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:25]  [Razor]: Steps: 5
[10/14/2025 06:25]  System: You have 4 stealth steps remaining.
[10/14/2025 06:25]  Razor: [4 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 4
[10/14/2025 06:25]  System: You will automatically stealth when out of steps.
[10/14/2025 06:25]  Razor: [Auto-stealth ready.]
[10/14/2025 06:25]  System: You have 3 stealth steps remaining.
[10/14/2025 06:25]  Razor: [3 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 3
[10/14/2025 06:25]  System: You have 2 stealth steps remaining.
[10/14/2025 06:25]  Razor: [2 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 2
[10/14/2025 06:25]  System: You have 1 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:25]  [Razor]: Steps: 1
[10/14/2025 06:25]  System: You have 0 stealth steps remaining.
[10/14/2025 06:25]  System: You feel ready to continue stealthing.
[10/14/2025 06:25]  Razor: [Stop!]
[10/14/2025 06:25]  [Razor]: Steps: 0
[10/14/2025 06:25]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:25]  System: You have 23 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 23
[10/14/2025 06:25]  System: You have 22 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 22
[10/14/2025 06:25]  System: You have 21 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 21
[10/14/2025 06:25]  System: You have 20 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 20
[10/14/2025 06:25]  an ossuarian pyromancer: an ossuarian pyromancer
[10/14/2025 06:25]  an ossuarian pyromancer: an ossuarian pyromancer
[10/14/2025 06:25]  System: You have 19 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 19
[10/14/2025 06:25]  System: You have 18 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 18
[10/14/2025 06:25]  System: You have 17 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 17
[10/14/2025 06:25]  System: You have 16 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 16
[10/14/2025 06:25]  System: You have 15 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 15
[10/14/2025 06:25]  System: You have 14 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 14
[10/14/2025 06:25]  System: You have 13 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 13
[10/14/2025 06:25]  System: You have 12 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 12
[10/14/2025 06:25]  System: You have 11 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 11
[10/14/2025 06:25]  System: You have 10 stealth steps remaining.
[10/14/2025 06:25]  Razor: [10 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 10
[10/14/2025 06:25]  System: You have 9 stealth steps remaining.
[10/14/2025 06:25]  Razor: [9 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 9
[10/14/2025 06:25]  System: You have 8 stealth steps remaining.
[10/14/2025 06:25]  Razor: [8 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 8
[10/14/2025 06:25]  System: You have 7 stealth steps remaining.
[10/14/2025 06:25]  Razor: [7 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 7
[10/14/2025 06:25]  System: You have 6 stealth steps remaining.
[10/14/2025 06:25]  Razor: [6 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 6
[10/14/2025 06:25]  System: You have 5 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:25]  System: You will automatically stealth when out of steps.
[10/14/2025 06:25]  Razor: [Auto-stealth ready.]
[10/14/2025 06:25]  [Razor]: Steps: 5
[10/14/2025 06:25]  System: You have 4 stealth steps remaining.
[10/14/2025 06:25]  Razor: [4 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 4
[10/14/2025 06:25]  an ossuarian slinger: an ossuarian slinger
[10/14/2025 06:25]  System: You have 3 stealth steps remaining.
[10/14/2025 06:25]  Razor: [3 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 3
[10/14/2025 06:25]  System: You have 2 stealth steps remaining.
[10/14/2025 06:25]  Razor: [2 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 2
[10/14/2025 06:25]  System: You have 1 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:25]  [Razor]: Steps: 1
[10/14/2025 06:25]  an ossuarian slinger: an ossuarian slinger
[10/14/2025 06:25]  System: You have 0 stealth steps remaining.
[10/14/2025 06:25]  System: You feel ready to continue stealthing.
[10/14/2025 06:25]  Razor: [Stop!]
[10/14/2025 06:25]  [Razor]: Steps: 0
[10/14/2025 06:25]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:25]  System: You have 23 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 23
[10/14/2025 06:25]  System: You have 22 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 22
[10/14/2025 06:25]  System: You have 21 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 21
[10/14/2025 06:25]  System: You have 20 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 20
[10/14/2025 06:25]  System: You have 19 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 19
[10/14/2025 06:25]  System: You have 18 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 18
[10/14/2025 06:25]  System: You have 17 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 17
[10/14/2025 06:25]  System: You have 16 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 16
[10/14/2025 06:25]  System: You have 15 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 15
[10/14/2025 06:25]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:25]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:25]  System: You have 14 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 14
[10/14/2025 06:25]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:25]  System: You have 13 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 13
[10/14/2025 06:25]  System: You have 12 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 12
[10/14/2025 06:25]  an ossuarian dervish: an ossuarian dervish
[10/14/2025 06:25]  System: You have 11 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 11
[10/14/2025 06:25]  System: You have 10 stealth steps remaining.
[10/14/2025 06:25]  Razor: [10 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 10
[10/14/2025 06:25]  System: You have 9 stealth steps remaining.
[10/14/2025 06:25]  Razor: [9 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 9
[10/14/2025 06:25]  System: You have 8 stealth steps remaining.
[10/14/2025 06:25]  Razor: [8 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 8
[10/14/2025 06:25]  System: You have 7 stealth steps remaining.
[10/14/2025 06:25]  Razor: [7 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 7
[10/14/2025 06:25]  System: You have 6 stealth steps remaining.
[10/14/2025 06:25]  Razor: [6 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 6
[10/14/2025 06:25]  System: You have 5 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:25]  [Razor]: Steps: 5
[10/14/2025 06:25]  System: You have 4 stealth steps remaining.
[10/14/2025 06:25]  Razor: [4 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 4
[10/14/2025 06:25]  System: You have 3 stealth steps remaining.
[10/14/2025 06:25]  Razor: [3 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 3
[10/14/2025 06:25]  System: You have 2 stealth steps remaining.
[10/14/2025 06:25]  Razor: [2 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 2
[10/14/2025 06:25]  System: You will automatically stealth when out of steps.
[10/14/2025 06:25]  Razor: [Auto-stealth ready.]
[10/14/2025 06:25]  System: You have 1 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:25]  [Razor]: Steps: 1
[10/14/2025 06:25]  System: You have 0 stealth steps remaining.
[10/14/2025 06:25]  System: You feel ready to continue stealthing.
[10/14/2025 06:25]  Razor: [Stop!]
[10/14/2025 06:25]  [Razor]: Steps: 0
[10/14/2025 06:25]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:25]  System: You have 23 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 23
[10/14/2025 06:25]  System: You have 22 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 22
[10/14/2025 06:25]  System: You have 21 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 21
[10/14/2025 06:25]  an ossuarian dervish: an ossuarian dervish
[10/14/2025 06:25]  System: You have 20 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 20
[10/14/2025 06:25]  [Razor]: Steps: 20
[10/14/2025 06:25]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:25]  a cave bat: a cave bat
[10/14/2025 06:25]  a sand muck: a sand muck
[10/14/2025 06:25]  System: You have 19 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 19
[10/14/2025 06:25]  a sand muck: a sand muck
[10/14/2025 06:25]  System: You have 18 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 18
[10/14/2025 06:25]  System: You have 17 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 17
[10/14/2025 06:25]  System: You have 16 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 16
[10/14/2025 06:25]  System: You have 15 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 15
[10/14/2025 06:25]  System: You have 14 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 14
[10/14/2025 06:25]  System: You have 13 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 13
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  System: You have 12 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 12
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  System: You have 11 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 11
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  System: You have 10 stealth steps remaining.
[10/14/2025 06:25]  Razor: [10 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 10
[10/14/2025 06:25]  System: You have 9 stealth steps remaining.
[10/14/2025 06:25]  Razor: [9 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 9
[10/14/2025 06:25]  System: You have 8 stealth steps remaining.
[10/14/2025 06:25]  Razor: [8 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 8
[10/14/2025 06:25]  a jackal spirit: a jackal spirit
[10/14/2025 06:25]  System: You have 7 stealth steps remaining.
[10/14/2025 06:25]  Razor: [7 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 7
[10/14/2025 06:25]  System: You have 6 stealth steps remaining.
[10/14/2025 06:25]  Razor: [6 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 6
[10/14/2025 06:25]  System: You may now use another smoke bomb.
[10/14/2025 06:25]  System: You have 5 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:25]  [Razor]: Steps: 5
[10/14/2025 06:25]  System: You have 4 stealth steps remaining.
[10/14/2025 06:25]  Razor: [4 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 4
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  System: You have 3 stealth steps remaining.
[10/14/2025 06:25]  Razor: [3 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 3
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  System: You have 2 stealth steps remaining.
[10/14/2025 06:25]  Razor: [2 Steps...]
[10/14/2025 06:25]  [Razor]: Steps: 2
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  a jackal spirit: a jackal spirit
[10/14/2025 06:25]  a hierarch: a hierarch
[10/14/2025 06:25]  System: You have 1 stealth steps remaining.
[10/14/2025 06:25]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:25]  System: You will automatically stealth when out of steps.
[10/14/2025 06:25]  Razor: [Auto-stealth ready.]
[10/14/2025 06:25]  [Razor]: Steps: 1
[10/14/2025 06:25]  System: You have 0 stealth steps remaining.
[10/14/2025 06:25]  System: You feel ready to continue stealthing.
[10/14/2025 06:25]  Razor: [Stop!]
[10/14/2025 06:25]  [Razor]: Steps: 0
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:25]  System: You have 23 stealth steps remaining.
[10/14/2025 06:25]  [Razor]: Steps: 23
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  a hierarch: a hierarch
[10/14/2025 06:25]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  You see: a phantasm corpse
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  a phantasm: a phantasm
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  a jackal spirit: a jackal spirit
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  You see: a jackal spirit corpse
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  You see: a hierarch corpse
[10/14/2025 06:25]  a jackal spirit: a jackal spirit
[10/14/2025 06:25]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:25]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:25]  System: You are no longer hindered.
[10/14/2025 06:25]  You see: gold coin : 400
[10/14/2025 06:25]  [Razor]: New target set.
[10/14/2025 06:25]  Razor: Target: a sand muck
[10/14/2025 06:26]  System: Gimbledorf has completed the achievement: Beastmaster (Intermediate).
[10/14/2025 06:26]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:26]  System: You will automatically stealth when out of steps.
[10/14/2025 06:26]  Razor: [Auto-stealth ready.]
[10/14/2025 06:26]  System: You have 29 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 29
[10/14/2025 06:26]  System: You have 28 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 28
[10/14/2025 06:26]  Razor: Attack: a sand muck
[10/14/2025 06:26]  System: You have 27 stealth steps remaining.
[10/14/2025 06:26]  System: You strike your target from the shadows!
[10/14/2025 06:26]  GawdHammer: You steal 136 gold coin.
[10/14/2025 06:26]  System: You steal 136 gold coin.
[10/14/2025 06:26]  GawdHammer: (Shadow Aspect 63,979.9/250000 xp)
[10/14/2025 06:26]  System: You have lost a little karma.
[10/14/2025 06:26]  System: You have gained a lot of karma.
[10/14/2025 06:26]  GawdHammer: (Shadow Aspect 63,991.3/250000 xp)
[10/14/2025 06:26]  System: -4000
[10/14/2025 06:26]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:26]  You see: a sand muck corpse
[10/14/2025 06:26]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:26]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:26]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:26]  System: You have 23 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 23
[10/14/2025 06:26]  a hierarch: a hierarch
[10/14/2025 06:26]  System: You have 22 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 22
[10/14/2025 06:26]  System: You have 21 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 21
[10/14/2025 06:26]  You see: a jackal spirit corpse
[10/14/2025 06:26]  You see: a hierarch corpse
[10/14/2025 06:26]  System: You have 20 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 20
[10/14/2025 06:26]  You see: a hierarch corpse
[10/14/2025 06:26]  You see: a jackal spirit corpse
[10/14/2025 06:26]  System: You have 19 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 19
[10/14/2025 06:26]  You see: a hierarch corpse
[10/14/2025 06:26]  System: You have 18 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 18
[10/14/2025 06:26]  You see: a jackal spirit corpse
[10/14/2025 06:26]  System: You have 17 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 17
[10/14/2025 06:26]  You see: a hierarch corpse
[10/14/2025 06:26]  You see: a jackal spirit corpse
[10/14/2025 06:26]  a phantasm: a phantasm
[10/14/2025 06:26]  System: You have 16 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 16
[10/14/2025 06:26]  You see: a phantasm corpse
[10/14/2025 06:26]  System: You have 15 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 15
[10/14/2025 06:26]  You see: a hierarch corpse
[10/14/2025 06:26]  You see: a phantasm corpse
[10/14/2025 06:26]  a jackal spirit: a jackal spirit
[10/14/2025 06:26]  System: You have 14 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 14
[10/14/2025 06:26]  System: You have 13 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 13
[10/14/2025 06:26]  System: You have 12 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 12
[10/14/2025 06:26]  System: You have 11 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 11
[10/14/2025 06:26]  System: You have 10 stealth steps remaining.
[10/14/2025 06:26]  Razor: [10 Steps...]
[10/14/2025 06:26]  [Razor]: New target set.
[10/14/2025 06:26]  Razor: Target: a jackal spirit
[10/14/2025 06:26]  [Razor]: Steps: 10
[10/14/2025 06:26]  System: You will automatically stealth when out of steps.
[10/14/2025 06:26]  Razor: [Auto-stealth ready.]
[10/14/2025 06:26]  Razor: Attack: a jackal spirit
[10/14/2025 06:26]  System: You strike your target from the shadows!
[10/14/2025 06:26]  GawdHammer: You steal 293 gold coin.
[10/14/2025 06:26]  System: You steal 293 gold coin.
[10/14/2025 06:26]  GawdHammer: (Shadow Aspect 63,997.1/250000 xp)
[10/14/2025 06:26]  System: You have lost a little karma.
[10/14/2025 06:26]  System: You have gained a very large amount of karma.
[10/14/2025 06:26]  GawdHammer: (Shadow Aspect 64,020.6/250000 xp)
[10/14/2025 06:26]  System: -8000
[10/14/2025 06:26]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:26]  You see: a jackal spirit corpse
[10/14/2025 06:26]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:26]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:26]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:26]  System: You have 23 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 23
[10/14/2025 06:26]  System: You have 22 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 22
[10/14/2025 06:26]  System: You have 21 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 21
[10/14/2025 06:26]  System: You have 20 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 20
[10/14/2025 06:26]  System: You have 19 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 19
[10/14/2025 06:26]  System: You have 18 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 18
[10/14/2025 06:26]  System: You have 17 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: Steps: 17
[10/14/2025 06:26]  System: You have 16 stealth steps remaining.
[10/14/2025 06:26]  [Razor]: New target set.
[10/14/2025 06:26]  Razor: Target: a hierarch
[10/14/2025 06:26]  [Razor]: Steps: 16
[10/14/2025 06:26]  System: You will automatically stealth when out of steps.
[10/14/2025 06:26]  Razor: [Auto-stealth ready.]
[10/14/2025 06:26]  Razor: Attack: a hierarch
[10/14/2025 06:26]  System: You strike your target from the shadows!
[10/14/2025 06:26]  GawdHammer: You steal 335 gold coin.
[10/14/2025 06:26]  System: You steal 335 gold coin.
[10/14/2025 06:26]  GawdHammer: (Shadow Aspect 64,026.9/250000 xp)
[10/14/2025 06:26]  System: You have lost a little karma.
[10/14/2025 06:26]  System: You have gained a very large amount of karma.
[10/14/2025 06:26]  GawdHammer: (Shadow Aspect 64,052.4/250000 xp)
[10/14/2025 06:26]  System: -6000
[10/14/2025 06:26]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:26]  You see: a hierarch corpse
[10/14/2025 06:26]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:26]  a jackal spirit: a jackal spirit
[10/14/2025 06:26]  System: You may now use another smoke bomb.
[10/14/2025 06:26]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:26]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:26]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:26]  a jackal spirit: *ritual strike*
[10/14/2025 06:26]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:26]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:27]  System: You have 23 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 23
[10/14/2025 06:27]  System: You have 22 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 22
[10/14/2025 06:27]  System: You have 21 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 21
[10/14/2025 06:27]  System: You have 20 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 20
[10/14/2025 06:27]  System: You have 19 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 19
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  a jackal spirit: a jackal spirit
[10/14/2025 06:27]  System: You have 18 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 18
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  System: You have 17 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 17
[10/14/2025 06:27]  a hierarch: a hierarch
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  System: You have 16 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 16
[10/14/2025 06:27]  System: You have 15 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: New target set.
[10/14/2025 06:27]  Razor: Target: a phantasm
[10/14/2025 06:27]  [Razor]: Steps: 15
[10/14/2025 06:27]  a jackal spirit: *ritual strike*
[10/14/2025 06:27]  Razor: Attack: a phantasm
[10/14/2025 06:27]  System: You will automatically stealth when out of steps.
[10/14/2025 06:27]  Razor: [Auto-stealth ready.]
[10/14/2025 06:27]  System: You strike your target from the shadows!
[10/14/2025 06:27]  GawdHammer: You steal 280 gold coin.
[10/14/2025 06:27]  System: You steal 280 gold coin.
[10/14/2025 06:27]  GawdHammer: You steal common black goods.
[10/14/2025 06:27]  System: You steal common black goods.
[10/14/2025 06:27]  GawdHammer: (Shadow Aspect 64,058.6/250000 xp)
[10/14/2025 06:27]  System: You have lost a little karma.
[10/14/2025 06:27]  System: You have gained a very large amount of karma.
[10/14/2025 06:27]  GawdHammer: (Shadow Aspect 64,083.6/250000 xp)
[10/14/2025 06:27]  System: -6000
[10/14/2025 06:27]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:27]  You see: a phantasm corpse
[10/14/2025 06:27]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:27]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:27]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:27]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:27]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:27]  System: You have 23 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 23
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  System: You have 22 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 22
[10/14/2025 06:27]  System: You have 21 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 21
[10/14/2025 06:27]  System: You have 20 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 20
[10/14/2025 06:27]  System: You have 19 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 19
[10/14/2025 06:27]  System: You have 18 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 18
[10/14/2025 06:27]  System: You have 17 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 17
[10/14/2025 06:27]  System: You have 16 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 16
[10/14/2025 06:27]  System: You have 15 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 15
[10/14/2025 06:27]  System: You have 14 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 14
[10/14/2025 06:27]  System: You have 13 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 13
[10/14/2025 06:27]  System: You have 12 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 12
[10/14/2025 06:27]  System: You have 11 stealth steps remaining.
[10/14/2025 06:27]  [Razor]: Steps: 11
[10/14/2025 06:27]  System: You have 10 stealth steps remaining.
[10/14/2025 06:27]  Razor: [10 Steps...]
[10/14/2025 06:27]  [Razor]: Steps: 10
[10/14/2025 06:27]  System: You have 9 stealth steps remaining.
[10/14/2025 06:27]  Razor: [9 Steps...]
[10/14/2025 06:27]  [Razor]: Steps: 9
[10/14/2025 06:27]  [Razor]: New target set.
[10/14/2025 06:27]  Razor: Target: a hierarch
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  Razor: Attack: a hierarch
[10/14/2025 06:27]  System: You strike your target from the shadows!
[10/14/2025 06:27]  GawdHammer: You steal 299 gold coin.
[10/14/2025 06:27]  System: You steal 299 gold coin.
[10/14/2025 06:27]  GawdHammer: (Shadow Aspect 64,089.9/250000 xp)
[10/14/2025 06:27]  System: You have lost a little karma.
[10/14/2025 06:27]  System: You have gained a very large amount of karma.
[10/14/2025 06:27]  GawdHammer: (Shadow Aspect 64,115.3/250000 xp)
[10/14/2025 06:27]  System: -6000
[10/14/2025 06:27]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:27]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:27]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:27]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  a hierarch: a hierarch
[10/14/2025 06:27]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:27]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:27]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:27]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:27]  System: Mule Boy has completed the achievement: Versatility (Basic).
[10/14/2025 06:27]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  You see: a hierarch corpse
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  You see: a jackal spirit corpse
[10/14/2025 06:27]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:27]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:27]  [Razor]: Queuing dragdrop request 1... 1.2 seconds left.
[10/14/2025 06:27]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  a hierarch: a hierarch
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  a hierarch: a hierarch
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:28]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:28]  a sand muck: a sand muck
[10/14/2025 06:28]  a cave bat: a cave bat
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  a hierarch: a hierarch
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  a jackal spirit: a jackal spirit
[10/14/2025 06:28]  You see: a sand muck corpse
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  a phantasm: a phantasm
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:28]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:28]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:28]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:28]  System: ThyGroves has completed the achievement: Paragon Slayer (Basic).
[10/14/2025 06:28]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:28]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:28]  System: You have 23 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 23
[10/14/2025 06:28]  System: You have 22 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 22
[10/14/2025 06:28]  System: You have 21 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 21
[10/14/2025 06:28]  System: You have 20 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 20
[10/14/2025 06:28]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:28]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:28]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  System: You have 19 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 19
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  System: You have 18 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 18
[10/14/2025 06:28]  System: You have 17 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 17
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  System: You have 16 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 16
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  System: You have 15 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 15
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  System: You have 14 stealth steps remaining.
[10/14/2025 06:28]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:28]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:28]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  [Razor]: Steps: 14
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  System: You have 13 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 13
[10/14/2025 06:28]  System: You have 12 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 12
[10/14/2025 06:28]  System: You have 11 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 11
[10/14/2025 06:28]  System: You have 10 stealth steps remaining.
[10/14/2025 06:28]  Razor: [10 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 10
[10/14/2025 06:28]  System: You have 9 stealth steps remaining.
[10/14/2025 06:28]  Razor: [9 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 9
[10/14/2025 06:28]  System: You have 8 stealth steps remaining.
[10/14/2025 06:28]  Razor: [8 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 8
[10/14/2025 06:28]  System: You have 7 stealth steps remaining.
[10/14/2025 06:28]  Razor: [7 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 7
[10/14/2025 06:28]  [Razor]: New target set.
[10/14/2025 06:28]  Razor: Target: a phantasm
[10/14/2025 06:28]  System: You will automatically stealth when out of steps.
[10/14/2025 06:28]  Razor: [Auto-stealth ready.]
[10/14/2025 06:28]  System: You have 6 stealth steps remaining.
[10/14/2025 06:28]  Razor: [6 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 6
[10/14/2025 06:28]  System: You have 5 stealth steps remaining.
[10/14/2025 06:28]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:28]  [Razor]: Steps: 5
[10/14/2025 06:28]  System: You have 4 stealth steps remaining.
[10/14/2025 06:28]  Razor: [4 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 4
[10/14/2025 06:28]  System: You have 3 stealth steps remaining.
[10/14/2025 06:28]  Razor: [3 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 3
[10/14/2025 06:28]  a sand muck: a sand muck
[10/14/2025 06:28]  System: You have 2 stealth steps remaining.
[10/14/2025 06:28]  Razor: [2 Steps...]
[10/14/2025 06:28]  [Razor]: Steps: 2
[10/14/2025 06:28]  You see: an ossuarian executioner corpse
[10/14/2025 06:28]  a cave bat: a cave bat
[10/14/2025 06:28]  System: You have 1 stealth steps remaining.
[10/14/2025 06:28]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:28]  [Razor]: Steps: 1
[10/14/2025 06:28]  System: You have 0 stealth steps remaining.
[10/14/2025 06:28]  System: You feel ready to continue stealthing.
[10/14/2025 06:28]  Razor: [Stop!]
[10/14/2025 06:28]  [Razor]: Steps: 0
[10/14/2025 06:28]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:28]  System: You have 23 stealth steps remaining.
[10/14/2025 06:28]  [Razor]: Steps: 23
[10/14/2025 06:28]  System: You have 22 stealth steps remaining.
[10/14/2025 06:28]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:28]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:28]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:28]  Razor: Attack: a phantasm
[10/14/2025 06:28]  [Razor]: Steps: 22
[10/14/2025 06:28]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:28]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:28]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:28]  System: You strike your target from the shadows!
[10/14/2025 06:28]  GawdHammer: You steal 321 gold coin.
[10/14/2025 06:28]  System: You steal 321 gold coin.
[10/14/2025 06:28]  GawdHammer: (Shadow Aspect 64,121.6/250000 xp)
[10/14/2025 06:28]  System: You have lost a little karma.
[10/14/2025 06:28]  System: You have gained a very large amount of karma.
[10/14/2025 06:28]  GawdHammer: (Shadow Aspect 64,146.5/250000 xp)
[10/14/2025 06:28]  System: -6000
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:28]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 06:28]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  You see: a phantasm corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a jackal spirit corpse
[10/14/2025 06:28]  You see: a hierarch corpse
[10/14/2025 06:29]  a blightwalker: a blightwalker
[10/14/2025 06:29]  an apophite spirit: an apophite spirit
[10/14/2025 06:29]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:29]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:29]  You see: a phantasm corpse
[10/14/2025 06:29]  System: After Midnight has completed the achievement: Nature Slayer (Basic).
[10/14/2025 06:29]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:29]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:29]  System: You have 23 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 23
[10/14/2025 06:29]  a phantasm: a phantasm
[10/14/2025 06:29]  System: You have 22 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 22
[10/14/2025 06:29]  System: You have 21 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 21
[10/14/2025 06:29]  System: You have 20 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 20
[10/14/2025 06:29]  System: You have 19 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 19
[10/14/2025 06:29]  System: You have 18 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 18
[10/14/2025 06:29]  System: You have 17 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 17
[10/14/2025 06:29]  System: You have 16 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 16
[10/14/2025 06:29]  System: You have 15 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 15
[10/14/2025 06:29]  System: You have 14 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 14
[10/14/2025 06:29]  System: You have 13 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 13
[10/14/2025 06:29]  System: You have 12 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 12
[10/14/2025 06:29]  [Razor]: New target set.
[10/14/2025 06:29]  Razor: Target: a blightwalker
[10/14/2025 06:29]  Razor: Attack: a blightwalker
[10/14/2025 06:29]  System: You strike your target from the shadows!
[10/14/2025 06:29]  GawdHammer: You steal an arcane scroll.
[10/14/2025 06:29]  System: You steal an arcane scroll.
[10/14/2025 06:29]  GawdHammer: You steal 310 gold coin.
[10/14/2025 06:29]  System: You steal 310 gold coin.
[10/14/2025 06:29]  GawdHammer: (Shadow Aspect 64,152.6/250000 xp)
[10/14/2025 06:29]  System: You have lost a little karma.
[10/14/2025 06:29]  a blightwalker: -7079
[10/14/2025 06:29]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:29]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:29]  System: You are no longer hindered.
[10/14/2025 06:29]  System: You have 29 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 29
[10/14/2025 06:29]  System: You have 28 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 28
[10/14/2025 06:29]  System: You have 27 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 27
[10/14/2025 06:29]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:29]  System: You will automatically stealth when out of steps.
[10/14/2025 06:29]  Razor: [Auto-stealth ready.]
[10/14/2025 06:29]  System: You strike your target from the shadows!
[10/14/2025 06:29]  System: You have already stolen from this creature.
[10/14/2025 06:29]  System: You have gained a very large amount of karma.
[10/14/2025 06:29]  GawdHammer: (Shadow Aspect 64,177.1/250000 xp)
[10/14/2025 06:29]  System: -2921
[10/14/2025 06:29]  You see: a blightwalker corpse
[10/14/2025 06:29]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:29]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:29]  [Razor]: New target set.
[10/14/2025 06:29]  Razor: Target: an apophite spirit
[10/14/2025 06:29]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:29]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:29]  System: You have 23 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 23
[10/14/2025 06:29]  System: You have 22 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 22
[10/14/2025 06:29]  System: You have 21 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 21
[10/14/2025 06:29]  System: You have 20 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 20
[10/14/2025 06:29]  System: You have 19 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 19
[10/14/2025 06:29]  [Razor]: New target set.
[10/14/2025 06:29]  System: You have 18 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 18
[10/14/2025 06:29]  System: You have 17 stealth steps remaining.
[10/14/2025 06:29]  [Razor]: Steps: 17
[10/14/2025 06:29]  System: RochelleRochelle has completed the achievement: Monster Hunter Dignitary.
[10/14/2025 06:29]  Razor: Attack: an apophite spirit
[10/14/2025 06:29]  System: You strike your target from the shadows!
[10/14/2025 06:29]  System: You fail to steal from the creature.
[10/14/2025 06:29]  System: You have lost a little karma.
[10/14/2025 06:29]  an apophite spirit: Thys! Ththisashyles.
[10/14/2025 06:29]  an apophite spirit: Revenge!
[10/14/2025 06:29]  System: You have gained a very large amount of karma.
[10/14/2025 06:29]  GawdHammer: (Shadow Aspect 64,204.6/250000 xp)
[10/14/2025 06:29]  System: -7000
[10/14/2025 06:29]  a spirit asp: a spirit asp
[10/14/2025 06:29]  a spirit asp: a spirit asp
[10/14/2025 06:29]  a spirit asp: a spirit asp
[10/14/2025 06:29]  a spirit asp: a spirit asp
[10/14/2025 06:29]  a spirit asp: a spirit asp
[10/14/2025 06:29]  You see: an apophite spirit corpse
[10/14/2025 06:29]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:29]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:29]  System: Real Perdi has completed the achievement: Guardian of Nusero (Basic).
[10/14/2025 06:30]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:30]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:30]  System: You have 23 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 23
[10/14/2025 06:30]  [Razor]: New target set.
[10/14/2025 06:30]  Razor: Target: a phantasm
[10/14/2025 06:30]  System: You have 22 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 22
[10/14/2025 06:30]  System: You have 21 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 21
[10/14/2025 06:30]  an ossuarian deathbringer: an ossuarian deathbringer
[10/14/2025 06:30]  System: You have 20 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 20
[10/14/2025 06:30]  You see: a hierarch corpse
[10/14/2025 06:30]  System: You have 19 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 19
[10/14/2025 06:30]  System: You have 18 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 18
[10/14/2025 06:30]  System: You have 17 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 17
[10/14/2025 06:30]  a hierarch: a hierarch
[10/14/2025 06:30]  Razor: Attack: a phantasm
[10/14/2025 06:30]  System: You have 16 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 16
[10/14/2025 06:30]  System: You strike your target from the shadows!
[10/14/2025 06:30]  GawdHammer: You steal 317 gold coin.
[10/14/2025 06:30]  System: You steal 317 gold coin.
[10/14/2025 06:30]  GawdHammer: (Shadow Aspect 64,210.9/250000 xp)
[10/14/2025 06:30]  System: You have lost a little karma.
[10/14/2025 06:30]  System: You have gained a very large amount of karma.
[10/14/2025 06:30]  GawdHammer: (Shadow Aspect 64,235.8/250000 xp)
[10/14/2025 06:30]  System: -6000
[10/14/2025 06:30]  You see: a hierarch corpse
[10/14/2025 06:30]  You see: a phantasm corpse
[10/14/2025 06:30]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:30]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:30]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:30]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 06:30]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:30]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:30]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:30]  : sandstone column
[10/14/2025 06:30]  : stone ruins
[10/14/2025 06:30]  : stone ruins
[10/14/2025 06:30]  You see: a blightwalker corpse
[10/14/2025 06:30]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:30]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:30]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:30]  System: You may now use another smoke bomb.
[10/14/2025 06:30]  You see: a hierarch corpse
[10/14/2025 06:30]  an ossuarian deathbringer: an ossuarian deathbringer
[10/14/2025 06:30]  You see: a hierarch corpse
[10/14/2025 06:30]  an ossuarian firebrand: an ossuarian firebrand
[10/14/2025 06:30]  a hierarch: a hierarch
[10/14/2025 06:30]  System: Ezmerellda has completed the achievement: Guardian of Shadowspire (Intermediate).
[10/14/2025 06:30]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:30]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:30]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:30]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:30]  System: You have 23 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 23
[10/14/2025 06:30]  System: You have 22 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 22
[10/14/2025 06:30]  System: You have 21 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 21
[10/14/2025 06:30]  System: You have 20 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 20
[10/14/2025 06:30]  System: You have 19 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 19
[10/14/2025 06:30]  [Razor]: New target set.
[10/14/2025 06:30]  Razor: Target: an ossuarian deathbringer
[10/14/2025 06:30]  System: You have 18 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 18
[10/14/2025 06:30]  System: You have 17 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 17
[10/14/2025 06:30]  System: You have 16 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 16
[10/14/2025 06:30]  System: Gimbledorf has completed the achievement: Paragon Hunter (Basic).
[10/14/2025 06:30]  Razor: Attack: an ossuarian deathbringer
[10/14/2025 06:30]  System: You strike your target from the shadows!
[10/14/2025 06:30]  GawdHammer: You steal 198 gold coin.
[10/14/2025 06:30]  System: You steal 198 gold coin.
[10/14/2025 06:30]  GawdHammer: (Shadow Aspect 64,239.9/250000 xp)
[10/14/2025 06:30]  System: You have lost a little karma.
[10/14/2025 06:30]  System: You have gained a lot of karma.
[10/14/2025 06:30]  GawdHammer: (Shadow Aspect 64,256.2/250000 xp)
[10/14/2025 06:30]  System: -8000
[10/14/2025 06:30]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:30]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:30]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:30]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:30]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:30]  System: You have 23 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 23
[10/14/2025 06:30]  System: You have 22 stealth steps remaining.
[10/14/2025 06:30]  [Razor]: Steps: 22
[10/14/2025 06:30]  You see: a blightwalker corpse
[10/14/2025 06:31]  System: You have 21 stealth steps remaining.
[10/14/2025 06:31]  [Razor]: Steps: 21
[10/14/2025 06:31]  System: You have 20 stealth steps remaining.
[10/14/2025 06:31]  [Razor]: Steps: 20
[10/14/2025 06:31]  You see: a khamsin corpse
[10/14/2025 06:31]  System: You have 19 stealth steps remaining.
[10/14/2025 06:31]  [Razor]: Steps: 19
[10/14/2025 06:31]  You see: a khamsin corpse
[10/14/2025 06:31]  [Razor]: New target set.
[10/14/2025 06:31]  Razor: Target: a hierarch
[10/14/2025 06:31]  a blightwalker: a blightwalker
[10/14/2025 06:31]  Razor: Attack: a hierarch
[10/14/2025 06:31]  System: You will automatically stealth when out of steps.
[10/14/2025 06:31]  Razor: [Auto-stealth ready.]
[10/14/2025 06:31]  System: You strike your target from the shadows!
[10/14/2025 06:31]  System: You fail to steal from the creature.
[10/14/2025 06:31]  System: You have lost a little karma.
[10/14/2025 06:31]  System: You have gained a very large amount of karma.
[10/14/2025 06:31]  GawdHammer: (Shadow Aspect 64,281.6/250000 xp)
[10/14/2025 06:31]  System: -6000
[10/14/2025 06:31]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:31]  You see: a hierarch corpse
[10/14/2025 06:31]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:31]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:31]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:31]  System: You have 23 stealth steps remaining.
[10/14/2025 06:31]  [Razor]: Steps: 23
[10/14/2025 06:31]  You see: a khamsin corpse
[10/14/2025 06:31]  Dexerela: Legendary Explorer
[10/14/2025 06:31]  Dexerela: [Councilor, Yew]
[10/14/2025 06:31]  Dexerela: Lady Dexerela
[10/14/2025 06:31]  System: You have 22 stealth steps remaining.
[10/14/2025 06:31]  [Razor]: Steps: 22
[10/14/2025 06:31]  System: You have 21 stealth steps remaining.
[10/14/2025 06:31]  [Razor]: Steps: 21
[10/14/2025 06:31]  [Razor]: New target set.
[10/14/2025 06:31]  Razor: Target: an ossuarian firebrand
[10/14/2025 06:31]  Razor: Attack: an ossuarian firebrand
[10/14/2025 06:31]  System: You strike your target from the shadows!
[10/14/2025 06:31]  GawdHammer: You steal 294 gold coin.
[10/14/2025 06:31]  System: You steal 294 gold coin.
[10/14/2025 06:31]  GawdHammer: (Shadow Aspect 64,287.5/250000 xp)
[10/14/2025 06:31]  System: You have lost a little karma.
[10/14/2025 06:31]  System: You have gained a very large amount of karma.
[10/14/2025 06:31]  GawdHammer: (Shadow Aspect 64,311.1/250000 xp)
[10/14/2025 06:31]  System: -8000
[10/14/2025 06:31]  You see: an ossuarian firebrand corpse
[10/14/2025 06:31]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:31]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:31]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:31]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:31]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:31]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:31]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:31]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:31]  a blightwalker: a blightwalker
[10/14/2025 06:31]  You see: an ossuarian sightless seer corpse
[10/14/2025 06:31]  You see: a khamsin corpse
[10/14/2025 06:31]  You see: a khamsin corpse
[10/14/2025 06:31]  You see: a khamsin corpse
[10/14/2025 06:31]  You see: a khamsin corpse
[10/14/2025 06:31]  You see: an ossuarian executioner corpse
[10/14/2025 06:31]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:31]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:31]  [Razor]: New target set.
[10/14/2025 06:31]  Razor: Target: a blightwalker
[10/14/2025 06:31]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:32]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:32]  System: You have 23 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 23
[10/14/2025 06:32]  System: You have 22 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 22
[10/14/2025 06:32]  System: You have 21 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 21
[10/14/2025 06:32]  System: You have 20 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 20
[10/14/2025 06:32]  System: You have 19 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 19
[10/14/2025 06:32]  [Razor]: New target set.
[10/14/2025 06:32]  System: You will automatically stealth when out of steps.
[10/14/2025 06:32]  Razor: [Auto-stealth ready.]
[10/14/2025 06:32]  Razor: Attack: a blightwalker
[10/14/2025 06:32]  System: You strike your target from the shadows!
[10/14/2025 06:32]  System: You fail to steal from the creature.
[10/14/2025 06:32]  System: You have lost a little karma.
[10/14/2025 06:32]  a blightwalker: -8723
[10/14/2025 06:32]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:32]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:32]  System: You are no longer hindered.
[10/14/2025 06:32]  System: You strike your target from the shadows!
[10/14/2025 06:32]  System: You fail to steal from the creature.
[10/14/2025 06:32]  System: You have lost a little karma.
[10/14/2025 06:32]  System: You have gained a very large amount of karma.
[10/14/2025 06:32]  GawdHammer: (Shadow Aspect 64,335.5/250000 xp)
[10/14/2025 06:32]  System: -1277
[10/14/2025 06:32]  You see: a blightwalker corpse
[10/14/2025 06:32]  System: The world will save in 15 seconds.
[10/14/2025 06:32]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 06:32]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:32]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:32]  System: a wild crab has completed the achievement: Caravanner (Basic).
[10/14/2025 06:32]  System: Grinpol has completed the achievement: Seafarer Initiate.
[10/14/2025 06:32]  System: Mikkys has completed the achievement: Leatherworker (Basic).
[10/14/2025 06:32]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:32]  You see: a khamsin corpse
[10/14/2025 06:32]  You see: an ossuarian sightless seer corpse
[10/14/2025 06:32]  You see: an ossuarian embalmer corpse
[10/14/2025 06:32]  You see: an ossuarian embalmer corpse
[10/14/2025 06:32]  You see: an ossuarian firebrand corpse
[10/14/2025 06:32]  You see: a hierarch corpse
[10/14/2025 06:32]  You see: an ossuarian sightless seer corpse
[10/14/2025 06:32]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:32]  You see: a hierarch corpse
[10/14/2025 06:32]  You see: a phantasm corpse
[10/14/2025 06:32]  a phantasm: a phantasm
[10/14/2025 06:32]  System: The world is saving, please wait.
[10/14/2025 06:32]  System: World save complete. The entire process took 3.2 seconds.
[10/14/2025 06:32]  You see: an apophite spirit corpse
[10/14/2025 06:32]  a blightwalker: a blightwalker
[10/14/2025 06:32]  You see: a blightwalker corpse
[10/14/2025 06:32]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:32]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:32]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:32]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:32]  System: You have 23 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 23
[10/14/2025 06:32]  System: You have 22 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 22
[10/14/2025 06:32]  System: You have 21 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: Steps: 21
[10/14/2025 06:32]  System: You have 20 stealth steps remaining.
[10/14/2025 06:32]  [Razor]: New target set.
[10/14/2025 06:32]  Razor: Target: a phantasm
[10/14/2025 06:32]  [Razor]: Steps: 20
[10/14/2025 06:32]  Razor: Attack: a phantasm
[10/14/2025 06:32]  System: You strike your target from the shadows!
[10/14/2025 06:32]  GawdHammer: You steal 288 gold coin.
[10/14/2025 06:32]  System: You steal 288 gold coin.
[10/14/2025 06:32]  GawdHammer: (Shadow Aspect 64,341.7/250000 xp)
[10/14/2025 06:32]  System: You have lost a little karma.
[10/14/2025 06:32]  System: You have gained a very large amount of karma.
[10/14/2025 06:32]  GawdHammer: (Shadow Aspect 64,366.7/250000 xp)
[10/14/2025 06:32]  System: -6000
[10/14/2025 06:32]  You see: a phantasm corpse
[10/14/2025 06:32]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:32]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:33]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:33]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:33]  System: You have 23 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 23
[10/14/2025 06:33]  System: You have 22 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 22
[10/14/2025 06:33]  System: You have 21 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 21
[10/14/2025 06:33]  System: You have 20 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 20
[10/14/2025 06:33]  System: You have 19 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 19
[10/14/2025 06:33]  System: You have 18 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 18
[10/14/2025 06:33]  System: You have 17 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 17
[10/14/2025 06:33]  System: You have 16 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 16
[10/14/2025 06:33]  System: You have 15 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 15
[10/14/2025 06:33]  System: You have 14 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: New target set.
[10/14/2025 06:33]  Razor: Target: a blightwalker
[10/14/2025 06:33]  [Razor]: Steps: 14
[10/14/2025 06:33]  System: You may now use another smoke bomb.
[10/14/2025 06:33]  Razor: Attack: a blightwalker
[10/14/2025 06:33]  System: You will automatically stealth when out of steps.
[10/14/2025 06:33]  Razor: [Auto-stealth ready.]
[10/14/2025 06:33]  System: You strike your target from the shadows!
[10/14/2025 06:33]  GawdHammer: You steal uncommon black goods.
[10/14/2025 06:33]  System: You steal uncommon black goods.
[10/14/2025 06:33]  GawdHammer: You steal 311 gold coin.
[10/14/2025 06:33]  System: You steal 311 gold coin.
[10/14/2025 06:33]  GawdHammer: (Shadow Aspect 64,372.8/250000 xp)
[10/14/2025 06:33]  System: You have lost a little karma.
[10/14/2025 06:33]  a blightwalker: -9863
[10/14/2025 06:33]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:33]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:33]  System: You are no longer hindered.
[10/14/2025 06:33]  System: You have 29 stealth steps remaining.
[10/14/2025 06:33]  System: You strike your target from the shadows!
[10/14/2025 06:33]  System: You have already stolen from this creature.
[10/14/2025 06:33]  System: You have gained a very large amount of karma.
[10/14/2025 06:33]  GawdHammer: (Shadow Aspect 64,397.2/250000 xp)
[10/14/2025 06:33]  System: -137
[10/14/2025 06:33]  You see: a blightwalker corpse
[10/14/2025 06:33]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:33]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:33]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:33]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:33]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:33]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:33]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:33]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:33]  System: Being perfectly rested, you shove something invisible out of the way.
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a phantasm corpse
[10/14/2025 06:33]  You see: a phantasm corpse
[10/14/2025 06:33]  You see: a phantasm corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  a jackal spirit: a jackal spirit
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: a phantasm corpse
[10/14/2025 06:33]  a hierarch: a hierarch
[10/14/2025 06:33]  You see: a phantasm corpse
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  a jackal spirit: a jackal spirit
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:33]  a jackal spirit: a jackal spirit
[10/14/2025 06:33]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:33]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:33]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:33]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:33]  You see: a hierarch corpse
[10/14/2025 06:33]  [Razor]: New target set.
[10/14/2025 06:33]  Razor: Target: a jackal spirit
[10/14/2025 06:33]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:33]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:33]  System: You have 23 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 23
[10/14/2025 06:33]  System: You have 22 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 22
[10/14/2025 06:33]  System: You have 21 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 21
[10/14/2025 06:33]  You see: a phantasm corpse
[10/14/2025 06:33]  System: You have 20 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 20
[10/14/2025 06:33]  You see: a phantasm corpse
[10/14/2025 06:33]  System: You have 19 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 19
[10/14/2025 06:33]  You see: a jackal spirit corpse
[10/14/2025 06:33]  System: You have 18 stealth steps remaining.
[10/14/2025 06:33]  [Razor]: Steps: 18
[10/14/2025 06:34]  System: You have 17 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 17
[10/14/2025 06:34]  System: You have 16 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 16
[10/14/2025 06:34]  System: You have 15 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 15
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 14 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 14
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  System: You have 13 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 13
[10/14/2025 06:34]  System: You have 12 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 12
[10/14/2025 06:34]  You see: a sand muck corpse
[10/14/2025 06:34]  System: You have 11 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 11
[10/14/2025 06:34]  System: You have 10 stealth steps remaining.
[10/14/2025 06:34]  Razor: [10 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 10
[10/14/2025 06:34]  System: You have 9 stealth steps remaining.
[10/14/2025 06:34]  Razor: [9 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 9
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  System: You have 8 stealth steps remaining.
[10/14/2025 06:34]  Razor: [8 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 8
[10/14/2025 06:34]  System: You will automatically stealth when out of steps.
[10/14/2025 06:34]  Razor: [Auto-stealth ready.]
[10/14/2025 06:34]  System: You have 7 stealth steps remaining.
[10/14/2025 06:34]  Razor: [7 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 7
[10/14/2025 06:34]  System: You have 6 stealth steps remaining.
[10/14/2025 06:34]  Razor: [6 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 6
[10/14/2025 06:34]  System: You have 5 stealth steps remaining.
[10/14/2025 06:34]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:34]  [Razor]: Steps: 5
[10/14/2025 06:34]  System: You have 4 stealth steps remaining.
[10/14/2025 06:34]  Razor: [4 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 4
[10/14/2025 06:34]  System: You have 3 stealth steps remaining.
[10/14/2025 06:34]  Razor: [3 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 3
[10/14/2025 06:34]  a sand muck: a sand muck
[10/14/2025 06:34]  System: You have 2 stealth steps remaining.
[10/14/2025 06:34]  Razor: [2 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 2
[10/14/2025 06:34]  a sand muck: a sand muck
[10/14/2025 06:34]  System: You have 1 stealth steps remaining.
[10/14/2025 06:34]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:34]  [Razor]: New target set.
[10/14/2025 06:34]  Razor: Target: a jackal spirit
[10/14/2025 06:34]  [Razor]: Steps: 1
[10/14/2025 06:34]  Razor: Attack: a jackal spirit
[10/14/2025 06:34]  System: You strike your target from the shadows!
[10/14/2025 06:34]  GawdHammer: You steal 264 gold coin.
[10/14/2025 06:34]  System: You steal 264 gold coin.
[10/14/2025 06:34]  GawdHammer: (Shadow Aspect 64,403.1/250000 xp)
[10/14/2025 06:34]  System: You have lost a little karma.
[10/14/2025 06:34]  System: You have gained a very large amount of karma.
[10/14/2025 06:34]  GawdHammer: (Shadow Aspect 64,426.6/250000 xp)
[10/14/2025 06:34]  System: -8000
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:34]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:34]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:34]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:34]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:34]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:34]  a phantasm: a phantasm
[10/14/2025 06:34]  System: You may now use another smoke bomb.
[10/14/2025 06:34]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:34]  System: You must wait a few moments to use another skill.
[10/14/2025 06:34]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:34]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:34]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:34]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:34]  System: You have 23 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 23
[10/14/2025 06:34]  System: You have 22 stealth steps remaining.
[10/14/2025 06:34]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:34]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:34]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:34]  [Razor]: Steps: 22
[10/14/2025 06:34]  System: You have 21 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 21
[10/14/2025 06:34]  System: You have 20 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 20
[10/14/2025 06:34]  System: You have 19 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 19
[10/14/2025 06:34]  a phantasm: *laments*
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  ApfelStrudel: wtf
[10/14/2025 06:34]  System: You have 18 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 18
[10/14/2025 06:34]  System: You have 17 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 17
[10/14/2025 06:34]  System: You have 16 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 16
[10/14/2025 06:34]  GawdHammer: -40
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  System: You have 15 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +5
[10/14/2025 06:34]  System: some damage has been healed : 5
[10/14/2025 06:34]  [Razor]: Steps: 15
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 14 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +5
[10/14/2025 06:34]  System: some damage has been healed : 5
[10/14/2025 06:34]  [Razor]: Steps: 14
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 13 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 13
[10/14/2025 06:34]  System: You have 12 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 12
[10/14/2025 06:34]  System: You have 11 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 11
[10/14/2025 06:34]  System: You have 10 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +5
[10/14/2025 06:34]  System: some damage has been healed : 5
[10/14/2025 06:34]  Razor: [10 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 10
[10/14/2025 06:34]  System: You have 9 stealth steps remaining.
[10/14/2025 06:34]  Razor: [9 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 9
[10/14/2025 06:34]  System: You have 8 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +5
[10/14/2025 06:34]  System: some damage has been healed : 5
[10/14/2025 06:34]  Razor: [8 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 8
[10/14/2025 06:34]  System: You have 7 stealth steps remaining.
[10/14/2025 06:34]  Razor: [7 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 7
[10/14/2025 06:34]  System: You have 6 stealth steps remaining.
[10/14/2025 06:34]  Razor: [6 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 6
[10/14/2025 06:34]  System: You have 5 stealth steps remaining.
[10/14/2025 06:34]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:34]  [Razor]: Steps: 5
[10/14/2025 06:34]  System: You have 4 stealth steps remaining.
[10/14/2025 06:34]  Razor: [4 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 4
[10/14/2025 06:34]  System: You have 3 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +5
[10/14/2025 06:34]  System: some damage has been healed : 5
[10/14/2025 06:34]  Razor: [3 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 3
[10/14/2025 06:34]  You see: a hierarch corpse
[10/14/2025 06:34]  System: You have 2 stealth steps remaining.
[10/14/2025 06:34]  Razor: [2 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 2
[10/14/2025 06:34]  System: You will automatically stealth when out of steps.
[10/14/2025 06:34]  Razor: [Auto-stealth ready.]
[10/14/2025 06:34]  You see: a hierarch corpse
[10/14/2025 06:34]  System: You have 1 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +5
[10/14/2025 06:34]  System: some damage has been healed : 5
[10/14/2025 06:34]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:34]  [Razor]: Steps: 1
[10/14/2025 06:34]  System: You have 0 stealth steps remaining.
[10/14/2025 06:34]  System: You feel ready to continue stealthing.
[10/14/2025 06:34]  Razor: [Stop!]
[10/14/2025 06:34]  [Razor]: Steps: 0
[10/14/2025 06:34]  You see: a hierarch corpse
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:34]  System: You have 23 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +5
[10/14/2025 06:34]  System: some damage has been healed : 5
[10/14/2025 06:34]  [Razor]: Steps: 23
[10/14/2025 06:34]  System: You have 22 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 22
[10/14/2025 06:34]  System: You have 21 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 21
[10/14/2025 06:34]  System: You have 20 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 20
[10/14/2025 06:34]  System: You have 19 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 19
[10/14/2025 06:34]  System: You have 18 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 18
[10/14/2025 06:34]  System: You have 17 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 17
[10/14/2025 06:34]  System: You have 16 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 16
[10/14/2025 06:34]  System: You have 15 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 15
[10/14/2025 06:34]  System: You have 14 stealth steps remaining.
[10/14/2025 06:34]  GawdHammer: +3
[10/14/2025 06:34]  System: some damage has been healed : 3
[10/14/2025 06:34]  [Razor]: Steps: 14
[10/14/2025 06:34]  System: You have 13 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 13
[10/14/2025 06:34]  System: You have 12 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 12
[10/14/2025 06:34]  System: You have 11 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 11
[10/14/2025 06:34]  System: You have 10 stealth steps remaining.
[10/14/2025 06:34]  Razor: [10 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 10
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 9 stealth steps remaining.
[10/14/2025 06:34]  Razor: [9 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 9
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 8 stealth steps remaining.
[10/14/2025 06:34]  Razor: [8 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 8
[10/14/2025 06:34]  System: You have 7 stealth steps remaining.
[10/14/2025 06:34]  Razor: [7 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 7
[10/14/2025 06:34]  System: You have 6 stealth steps remaining.
[10/14/2025 06:34]  Razor: [6 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 6
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  System: You have 5 stealth steps remaining.
[10/14/2025 06:34]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:34]  [Razor]: Steps: 5
[10/14/2025 06:34]  You see: a phantasm corpse
[10/14/2025 06:34]  System: You have 4 stealth steps remaining.
[10/14/2025 06:34]  Razor: [4 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 4
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 3 stealth steps remaining.
[10/14/2025 06:34]  Razor: [3 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 3
[10/14/2025 06:34]  System: You have 2 stealth steps remaining.
[10/14/2025 06:34]  Razor: [2 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 2
[10/14/2025 06:34]  System: You will automatically stealth when out of steps.
[10/14/2025 06:34]  Razor: [Auto-stealth ready.]
[10/14/2025 06:34]  System: You have 1 stealth steps remaining.
[10/14/2025 06:34]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:34]  [Razor]: Steps: 1
[10/14/2025 06:34]  System: You have 0 stealth steps remaining.
[10/14/2025 06:34]  System: You feel ready to continue stealthing.
[10/14/2025 06:34]  Razor: [Stop!]
[10/14/2025 06:34]  [Razor]: Steps: 0
[10/14/2025 06:34]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:34]  System: You have 23 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 23
[10/14/2025 06:34]  System: You have 22 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 22
[10/14/2025 06:34]  System: You have 21 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 21
[10/14/2025 06:34]  System: You have 20 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 20
[10/14/2025 06:34]  System: You have 19 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 19
[10/14/2025 06:34]  System: You have 18 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 18
[10/14/2025 06:34]  System: You have 17 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 17
[10/14/2025 06:34]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:34]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:34]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:34]  System: You have 16 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 16
[10/14/2025 06:34]  System: You have 15 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 15
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 14 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 14
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 13 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 13
[10/14/2025 06:34]  You see: a jackal spirit corpse
[10/14/2025 06:34]  System: You have 12 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 12
[10/14/2025 06:34]  System: You have 11 stealth steps remaining.
[10/14/2025 06:34]  [Razor]: Steps: 11
[10/14/2025 06:34]  System: You have 10 stealth steps remaining.
[10/14/2025 06:34]  Razor: [10 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 10
[10/14/2025 06:34]  System: You have 9 stealth steps remaining.
[10/14/2025 06:34]  Razor: [9 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 9
[10/14/2025 06:34]  You see: a hierarch corpse
[10/14/2025 06:34]  System: You have 8 stealth steps remaining.
[10/14/2025 06:34]  Razor: [8 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 8
[10/14/2025 06:34]  System: You have 7 stealth steps remaining.
[10/14/2025 06:34]  Razor: [7 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 7
[10/14/2025 06:34]  System: You have 6 stealth steps remaining.
[10/14/2025 06:34]  Razor: [6 Steps...]
[10/14/2025 06:34]  [Razor]: Steps: 6
[10/14/2025 06:34]  System: You have 5 stealth steps remaining.
[10/14/2025 06:34]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:34]  [Razor]: Steps: 5
[10/14/2025 06:35]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:35]  System: You have 4 stealth steps remaining.
[10/14/2025 06:35]  Razor: [4 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 4
[10/14/2025 06:35]  an ossuarian deathbringer: an ossuarian deathbringer
[10/14/2025 06:35]  System: You have 3 stealth steps remaining.
[10/14/2025 06:35]  Razor: [3 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 3
[10/14/2025 06:35]  System: You have 2 stealth steps remaining.
[10/14/2025 06:35]  Razor: [2 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 2
[10/14/2025 06:35]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:35]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:35]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:35]  System: You will automatically stealth when out of steps.
[10/14/2025 06:35]  Razor: [Auto-stealth ready.]
[10/14/2025 06:35]  System: You have 1 stealth steps remaining.
[10/14/2025 06:35]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:35]  [Razor]: Steps: 1
[10/14/2025 06:35]  System: You have 0 stealth steps remaining.
[10/14/2025 06:35]  System: You feel ready to continue stealthing.
[10/14/2025 06:35]  Razor: [Stop!]
[10/14/2025 06:35]  [Razor]: Steps: 0
[10/14/2025 06:35]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:35]  System: You have 23 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 23
[10/14/2025 06:35]  You see: a blightwalker corpse
[10/14/2025 06:35]  System: You have 22 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 22
[10/14/2025 06:35]  You see: a blightwalker corpse
[10/14/2025 06:35]  System: You have 21 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 21
[10/14/2025 06:35]  System: You have 20 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 20
[10/14/2025 06:35]  System: You have 19 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 19
[10/14/2025 06:35]  You see: a jackal spirit corpse
[10/14/2025 06:35]  an ossuarian deathbringer: an ossuarian deathbringer
[10/14/2025 06:35]  System: You have 18 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 18
[10/14/2025 06:35]  System: You have 17 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 17
[10/14/2025 06:35]  System: You have 16 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 16
[10/14/2025 06:35]  an apophite spirit: an apophite spirit
[10/14/2025 06:35]  System: You have 15 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 15
[10/14/2025 06:35]  You see: an apophite spirit corpse
[10/14/2025 06:35]  You see: a phantasm corpse
[10/14/2025 06:35]  You see: a phantasm corpse
[10/14/2025 06:35]  System: You have 14 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 14
[10/14/2025 06:35]  System: You have 13 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 13
[10/14/2025 06:35]  System: You have 12 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 12
[10/14/2025 06:35]  System: You have 11 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 11
[10/14/2025 06:35]  System: You have 10 stealth steps remaining.
[10/14/2025 06:35]  Razor: [10 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 10
[10/14/2025 06:35]  System: You have 9 stealth steps remaining.
[10/14/2025 06:35]  Razor: [9 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 9
[10/14/2025 06:35]  System: You have 8 stealth steps remaining.
[10/14/2025 06:35]  Razor: [8 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 8
[10/14/2025 06:35]  System: You have 7 stealth steps remaining.
[10/14/2025 06:35]  Razor: [7 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 7
[10/14/2025 06:35]  System: You have 6 stealth steps remaining.
[10/14/2025 06:35]  Razor: [6 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 6
[10/14/2025 06:35]  System: You have 5 stealth steps remaining.
[10/14/2025 06:35]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:35]  [Razor]: Steps: 5
[10/14/2025 06:35]  a phantasm: a phantasm
[10/14/2025 06:35]  [Razor]: New target set.
[10/14/2025 06:35]  Razor: Target: an apophite spirit
[10/14/2025 06:35]  System: You will automatically stealth when out of steps.
[10/14/2025 06:35]  Razor: [Auto-stealth ready.]
[10/14/2025 06:35]  System: You have 4 stealth steps remaining.
[10/14/2025 06:35]  Razor: [4 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 4
[10/14/2025 06:35]  System: You have 3 stealth steps remaining.
[10/14/2025 06:35]  Razor: [3 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 3
[10/14/2025 06:35]  System: You have 2 stealth steps remaining.
[10/14/2025 06:35]  Razor: [2 Steps...]
[10/14/2025 06:35]  [Razor]: Steps: 2
[10/14/2025 06:35]  System: You have 1 stealth steps remaining.
[10/14/2025 06:35]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:35]  [Razor]: Steps: 1
[10/14/2025 06:35]  System: You have 0 stealth steps remaining.
[10/14/2025 06:35]  System: You feel ready to continue stealthing.
[10/14/2025 06:35]  Razor: [Stop!]
[10/14/2025 06:35]  [Razor]: Steps: 0
[10/14/2025 06:35]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:35]  System: You have 23 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 23
[10/14/2025 06:35]  an ossuarian deathbringer: an ossuarian deathbringer
[10/14/2025 06:35]  [Razor]: New target set.
[10/14/2025 06:35]  Razor: Attack: an apophite spirit
[10/14/2025 06:35]  System: You strike your target from the shadows!
[10/14/2025 06:35]  GawdHammer: You steal 353 gold coin.
[10/14/2025 06:35]  System: You steal 353 gold coin.
[10/14/2025 06:35]  GawdHammer: You steal very common black goods.
[10/14/2025 06:35]  System: You steal very common black goods.
[10/14/2025 06:35]  GawdHammer: (Shadow Aspect 64,433.4/250000 xp)
[10/14/2025 06:35]  System: You have lost a little karma.
[10/14/2025 06:35]  System: You have gained a very large amount of karma.
[10/14/2025 06:35]  GawdHammer: (Shadow Aspect 64,461.0/250000 xp)
[10/14/2025 06:35]  System: -7000
[10/14/2025 06:35]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:35]  a spirit asp: a spirit asp
[10/14/2025 06:35]  a spirit asp: a spirit asp
[10/14/2025 06:35]  a spirit asp: a spirit asp
[10/14/2025 06:35]  a spirit asp: a spirit asp
[10/14/2025 06:35]  a spirit asp: a spirit asp
[10/14/2025 06:35]  You see: an apophite spirit corpse
[10/14/2025 06:35]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:35]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:35]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:35]  System: You have 23 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 23
[10/14/2025 06:35]  System: You have 22 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 22
[10/14/2025 06:35]  [Razor]: New target set.
[10/14/2025 06:35]  Razor: Target: a phantasm
[10/14/2025 06:35]  System: You have 21 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 21
[10/14/2025 06:35]  System: You have 20 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 20
[10/14/2025 06:35]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:35]  You see: a hierarch corpse
[10/14/2025 06:35]  System: You have 19 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 19
[10/14/2025 06:35]  System: You have 18 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 18
[10/14/2025 06:35]  System: You have 17 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 17
[10/14/2025 06:35]  System: You have 16 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 16
[10/14/2025 06:35]  a hierarch: a hierarch
[10/14/2025 06:35]  System: You have 15 stealth steps remaining.
[10/14/2025 06:35]  [Razor]: Steps: 15
[10/14/2025 06:35]  You see: a hierarch corpse
[10/14/2025 06:35]  System: You have 14 stealth steps remaining.
[10/14/2025 06:35]  Razor: Attack: a phantasm
[10/14/2025 06:35]  [Razor]: Steps: 14
[10/14/2025 06:35]  You see: an ossuarian firebrand corpse
[10/14/2025 06:35]  System: You strike your target from the shadows!
[10/14/2025 06:35]  GawdHammer: You steal 649 gold coin.
[10/14/2025 06:35]  System: You steal 649 gold coin.
[10/14/2025 06:35]  GawdHammer: (Shadow Aspect 64,467.2/250000 xp)
[10/14/2025 06:35]  System: You have lost a little karma.
[10/14/2025 06:35]  System: You have gained a very large amount of karma.
[10/14/2025 06:35]  GawdHammer: (Shadow Aspect 64,492.2/250000 xp)
[10/14/2025 06:35]  System: -6000
[10/14/2025 06:35]  You see: a phantasm corpse
[10/14/2025 06:35]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:35]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:35]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 06:35]  [Razor]: Queuing dragdrop request 2... 1.3 seconds left.
[10/14/2025 06:35]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:35]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 06:35]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:35]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:35]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:35]  You see: a hierarch corpse
[10/14/2025 06:35]  You see: a hierarch corpse
[10/14/2025 06:35]  You see: an ossuarian firebrand corpse
[10/14/2025 06:35]  a hierarch: a hierarch
[10/14/2025 06:35]  an ossuarian firebrand: an ossuarian firebrand
[10/14/2025 06:35]  You see: a blightwalker corpse
[10/14/2025 06:35]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:35]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:35]  Bapeth: Seasonal Locksmith
[10/14/2025 06:35]  Bapeth: Bapeth
[10/14/2025 06:36]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:36]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:36]  System: You have 23 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 23
[10/14/2025 06:36]  System: You have 22 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 22
[10/14/2025 06:36]  System: You have 21 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 21
[10/14/2025 06:36]  System: You have 20 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 20
[10/14/2025 06:36]  System: You have 19 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 19
[10/14/2025 06:36]  a blightwalker: a blightwalker
[10/14/2025 06:36]  System: You have 18 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 18
[10/14/2025 06:36]  System: You have 17 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 17
[10/14/2025 06:36]  System: You have 16 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 16
[10/14/2025 06:36]  System: You have 15 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 15
[10/14/2025 06:36]  System: You have 14 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 14
[10/14/2025 06:36]  You see: a khamsin corpse
[10/14/2025 06:36]  System: You have 13 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 13
[10/14/2025 06:36]  You see: a khamsin corpse
[10/14/2025 06:36]  System: You have 12 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 12
[10/14/2025 06:36]  System: You have 11 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 11
[10/14/2025 06:36]  System: You have 10 stealth steps remaining.
[10/14/2025 06:36]  Razor: [10 Steps...]
[10/14/2025 06:36]  [Razor]: Steps: 10
[10/14/2025 06:36]  System: You have 9 stealth steps remaining.
[10/14/2025 06:36]  Razor: [9 Steps...]
[10/14/2025 06:36]  [Razor]: Steps: 9
[10/14/2025 06:36]  System: You have 8 stealth steps remaining.
[10/14/2025 06:36]  Razor: [8 Steps...]
[10/14/2025 06:36]  [Razor]: New target set.
[10/14/2025 06:36]  Razor: Target: a hierarch
[10/14/2025 06:36]  [Razor]: Steps: 8
[10/14/2025 06:36]  Razor: Attack: a hierarch
[10/14/2025 06:36]  System: You strike your target from the shadows!
[10/14/2025 06:36]  GawdHammer: You steal 323 gold coin.
[10/14/2025 06:36]  System: You steal 323 gold coin.
[10/14/2025 06:36]  GawdHammer: (Shadow Aspect 64,498.5/250000 xp)
[10/14/2025 06:36]  System: You have lost a little karma.
[10/14/2025 06:36]  System: You have gained a very large amount of karma.
[10/14/2025 06:36]  GawdHammer: (Shadow Aspect 64,524.0/250000 xp)
[10/14/2025 06:36]  System: -6000
[10/14/2025 06:36]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:36]  You see: a hierarch corpse
[10/14/2025 06:36]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:36]  [Razor]: New target set.
[10/14/2025 06:36]  Razor: Target: an ossuarian firebrand
[10/14/2025 06:36]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:36]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:36]  Bapeth: Seasonal Locksmith
[10/14/2025 06:36]  Bapeth: Bapeth
[10/14/2025 06:36]  System: ex.xo has completed the achievement: Aspect Mastery (Epic).
[10/14/2025 06:36]  System: You have 23 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 23
[10/14/2025 06:36]  a khamsin: a khamsin
[10/14/2025 06:36]  Razor: Attack: an ossuarian firebrand
[10/14/2025 06:36]  System: You will automatically stealth when out of steps.
[10/14/2025 06:36]  Razor: [Auto-stealth ready.]
[10/14/2025 06:36]  System: You strike your target from the shadows!
[10/14/2025 06:36]  GawdHammer: You steal 307 gold coin.
[10/14/2025 06:36]  System: You steal 307 gold coin.
[10/14/2025 06:36]  GawdHammer: (Shadow Aspect 64,529.9/250000 xp)
[10/14/2025 06:36]  System: You have lost a little karma.
[10/14/2025 06:36]  System: You have gained a very large amount of karma.
[10/14/2025 06:36]  GawdHammer: (Shadow Aspect 64,553.5/250000 xp)
[10/14/2025 06:36]  System: -8000
[10/14/2025 06:36]  You see: an ossuarian firebrand corpse
[10/14/2025 06:36]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:36]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:36]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:36]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:36]  System: You have 23 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 23
[10/14/2025 06:36]  System: You have 22 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 22
[10/14/2025 06:36]  System: You have 21 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 21
[10/14/2025 06:36]  You see: a phantasm corpse
[10/14/2025 06:36]  System: You have 20 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 20
[10/14/2025 06:36]  [Razor]: New target set.
[10/14/2025 06:36]  Razor: Target: an ossuarian deathbringer
[10/14/2025 06:36]  System: You have 19 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 19
[10/14/2025 06:36]  System: You have 18 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 18
[10/14/2025 06:36]  System: You have 17 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 17
[10/14/2025 06:36]  System: You have 16 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 16
[10/14/2025 06:36]  System: You have 15 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 15
[10/14/2025 06:36]  System: You have 14 stealth steps remaining.
[10/14/2025 06:36]  [Razor]: Steps: 14
[10/14/2025 06:36]  Razor: Attack: an ossuarian deathbringer
[10/14/2025 06:36]  System: You strike your target from the shadows!
[10/14/2025 06:36]  GawdHammer: You steal very common black goods.
[10/14/2025 06:36]  System: You steal very common black goods.
[10/14/2025 06:36]  GawdHammer: You steal 196 gold coin.
[10/14/2025 06:36]  System: You steal 196 gold coin.
[10/14/2025 06:36]  GawdHammer: (Shadow Aspect 64,557.5/250000 xp)
[10/14/2025 06:36]  System: You have lost a little karma.
[10/14/2025 06:36]  System: You have gained a lot of karma.
[10/14/2025 06:36]  GawdHammer: (Shadow Aspect 64,573.8/250000 xp)
[10/14/2025 06:36]  System: -8000
[10/14/2025 06:36]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:37]  You see: a khamsin corpse
[10/14/2025 06:37]  You see: a khamsin corpse
[10/14/2025 06:37]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:37]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:37]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:37]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:37]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:37]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:37]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:37]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:37]  You see: an ossuarian sightless seer corpse
[10/14/2025 06:37]  a khamsin: a khamsin
[10/14/2025 06:37]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:37]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:37]  [Razor]: New target set.
[10/14/2025 06:37]  Razor: Target: a blightwalker
[10/14/2025 06:37]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:37]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:37]  System: You have 23 stealth steps remaining.
[10/14/2025 06:37]  [Razor]: Steps: 23
[10/14/2025 06:37]  System: You have 22 stealth steps remaining.
[10/14/2025 06:37]  [Razor]: Steps: 22
[10/14/2025 06:37]  System: You have 21 stealth steps remaining.
[10/14/2025 06:37]  [Razor]: Steps: 21
[10/14/2025 06:37]  System: You have 20 stealth steps remaining.
[10/14/2025 06:37]  [Razor]: Steps: 20
[10/14/2025 06:37]  System: You have 19 stealth steps remaining.
[10/14/2025 06:37]  [Razor]: Steps: 19
[10/14/2025 06:37]  System: You have 18 stealth steps remaining.
[10/14/2025 06:37]  [Razor]: Steps: 18
[10/14/2025 06:37]  System: You have 17 stealth steps remaining.
[10/14/2025 06:37]  [Razor]: Steps: 17
[10/14/2025 06:37]  Razor: Attack: a blightwalker
[10/14/2025 06:37]  System: You strike your target from the shadows!
[10/14/2025 06:37]  GawdHammer: You steal 308 gold coin.
[10/14/2025 06:37]  System: You steal 308 gold coin.
[10/14/2025 06:37]  GawdHammer: (Shadow Aspect 64,579.9/250000 xp)
[10/14/2025 06:37]  System: You have lost a little karma.
[10/14/2025 06:37]  a blightwalker: -8134
[10/14/2025 06:37]  System: You must wait a few moments to use another skill.
[10/14/2025 06:37]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:37]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:37]  System: You are no longer hindered.
[10/14/2025 06:37]  System: You strike your target from the shadows!
[10/14/2025 06:37]  System: You have already stolen from this creature.
[10/14/2025 06:37]  System: You have gained a very large amount of karma.
[10/14/2025 06:37]  GawdHammer: (Shadow Aspect 64,604.3/250000 xp)
[10/14/2025 06:37]  System: -1866
[10/14/2025 06:37]  You see: a blightwalker corpse
[10/14/2025 06:37]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:37]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:37]  System: Paperwait has completed the achievement: Tradesman Union Initiate.
[10/14/2025 06:37]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:37]  You see: a phantasm corpse
[10/14/2025 06:37]  You see: a phantasm corpse
[10/14/2025 06:37]  You see: an apophite spirit corpse
[10/14/2025 06:37]  You see: a blightwalker corpse
[10/14/2025 06:37]  a blightwalker: a blightwalker
[10/14/2025 06:37]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:37]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:37]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:37]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:38]  System: You have 23 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 23
[10/14/2025 06:38]  System: You have 22 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 22
[10/14/2025 06:38]  System: You have 21 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 21
[10/14/2025 06:38]  System: You have 20 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 20
[10/14/2025 06:38]  System: You have 19 stealth steps remaining.
[10/14/2025 06:38]  an apophite spirit: an apophite spirit
[10/14/2025 06:38]  [Razor]: Steps: 19
[10/14/2025 06:38]  System: You have 18 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 18
[10/14/2025 06:38]  [Razor]: New target set.
[10/14/2025 06:38]  Razor: Target: a blightwalker
[10/14/2025 06:38]  System: You have 17 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 17
[10/14/2025 06:38]  System: You have 16 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 16
[10/14/2025 06:38]  [Razor]: New target set.
[10/14/2025 06:38]  Razor: Target: an apophite spirit
[10/14/2025 06:38]  System: You have 15 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 15
[10/14/2025 06:38]  System: You have 14 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 14
[10/14/2025 06:38]  Razor: Attack: an apophite spirit
[10/14/2025 06:38]  System: You will automatically stealth when out of steps.
[10/14/2025 06:38]  Razor: [Auto-stealth ready.]
[10/14/2025 06:38]  System: You strike your target from the shadows!
[10/14/2025 06:38]  GawdHammer: You steal 741 gold coin.
[10/14/2025 06:38]  System: You steal 741 gold coin.
[10/14/2025 06:38]  GawdHammer: (Shadow Aspect 64,611.2/250000 xp)
[10/14/2025 06:38]  System: You have lost a little karma.
[10/14/2025 06:38]  System: You have gained a very large amount of karma.
[10/14/2025 06:38]  GawdHammer: (Shadow Aspect 64,638.8/250000 xp)
[10/14/2025 06:38]  System: -7000
[10/14/2025 06:38]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:38]  a spirit asp: a spirit asp
[10/14/2025 06:38]  a spirit asp: a spirit asp
[10/14/2025 06:38]  a spirit asp: a spirit asp
[10/14/2025 06:38]  a spirit asp: a spirit asp
[10/14/2025 06:38]  a spirit asp: a spirit asp
[10/14/2025 06:38]  You see: an apophite spirit corpse
[10/14/2025 06:38]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:38]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:38]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:38]  System: You have 23 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 23
[10/14/2025 06:38]  System: You have 22 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 22
[10/14/2025 06:38]  System: You have 21 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 21
[10/14/2025 06:38]  System: You have 20 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 20
[10/14/2025 06:38]  System: You have 19 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 19
[10/14/2025 06:38]  System: You have 18 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 18
[10/14/2025 06:38]  [Razor]: New target set.
[10/14/2025 06:38]  Razor: Target: a blightwalker
[10/14/2025 06:38]  System: You will automatically stealth when out of steps.
[10/14/2025 06:38]  Razor: [Auto-stealth ready.]
[10/14/2025 06:38]  Razor: Attack: a blightwalker
[10/14/2025 06:38]  System: You strike your target from the shadows!
[10/14/2025 06:38]  System: You fail to steal from the creature.
[10/14/2025 06:38]  System: You have lost a little karma.
[10/14/2025 06:38]  a blightwalker: -8996
[10/14/2025 06:38]  System: Vanish activated.
[10/14/2025 06:38]  System: You must wait another 5 seconds before you may use another smoke bomb.
[10/14/2025 06:38]  System: You are no longer hindered.
[10/14/2025 06:38]  System: You have 119 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 119
[10/14/2025 06:38]  System: You have 118 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 118
[10/14/2025 06:38]  System: You may now use another smoke bomb.
[10/14/2025 06:38]  System: You have 117 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 117
[10/14/2025 06:38]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:38]  System: You have 116 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 116
[10/14/2025 06:38]  System: You have 115 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 115
[10/14/2025 06:38]  System: You have 114 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 114
[10/14/2025 06:38]  System: You have 113 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 113
[10/14/2025 06:38]  System: You have 112 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 112
[10/14/2025 06:38]  System: You have 111 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 111
[10/14/2025 06:38]  System: You have 110 stealth steps remaining.
[10/14/2025 06:38]  [Razor]: Steps: 110
[10/14/2025 06:38]  [Razor]: New target set.
[10/14/2025 06:38]  System: You have 109 stealth steps remaining.
[10/14/2025 06:38]  System: You strike your target from the shadows!
[10/14/2025 06:38]  GawdHammer: You steal 332 gold coin.
[10/14/2025 06:38]  System: You steal 332 gold coin.
[10/14/2025 06:38]  GawdHammer: (Shadow Aspect 64,644.9/250000 xp)
[10/14/2025 06:38]  System: You have lost a little karma.
[10/14/2025 06:38]  System: You have gained a very large amount of karma.
[10/14/2025 06:38]  GawdHammer: (Shadow Aspect 64,669.3/250000 xp)
[10/14/2025 06:38]  System: -1004
[10/14/2025 06:38]  You see: a blightwalker corpse
[10/14/2025 06:38]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:38]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:38]  You see: an ossuarian deathbringer corpse
[10/14/2025 06:38]  You see: a phantasm corpse
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  You see: a phantasm corpse
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  You see: a phantasm corpse
[10/14/2025 06:38]  You see: a hierarch corpse
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  You see: a hierarch corpse
[10/14/2025 06:38]  You see: a hierarch corpse
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  System: HootyDoot has completed the achievement: Weaponmastery (Basic).
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  a jackal spirit: a jackal spirit
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  a jackal spirit: a jackal spirit
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  You see: a jackal spirit corpse
[10/14/2025 06:38]  You see: a hierarch corpse
[10/14/2025 06:38]  You see: a hierarch corpse
[10/14/2025 06:38]  You see: a hierarch corpse
[10/14/2025 06:38]  a phantasm: a phantasm
[10/14/2025 06:38]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:38]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:38]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:38]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:38]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:38]  You see: a phantasm corpse
[10/14/2025 06:38]  [Razor]: New target set.
[10/14/2025 06:38]  Razor: Target: a jackal spirit
[10/14/2025 06:39]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:39]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:39]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:39]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:39]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:39]  System: You have 23 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 23
[10/14/2025 06:39]  You see: a hierarch corpse
[10/14/2025 06:39]  You see: a hierarch corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  System: You have 22 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 22
[10/14/2025 06:39]  System: You have 21 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 21
[10/14/2025 06:39]  Razor: Attack: a jackal spirit
[10/14/2025 06:39]  System: You have 20 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 20
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  System: You have 19 stealth steps remaining.
[10/14/2025 06:39]  System: You strike your target from the shadows!
[10/14/2025 06:39]  GawdHammer: You steal 285 gold coin.
[10/14/2025 06:39]  System: You steal 285 gold coin.
[10/14/2025 06:39]  GawdHammer: (Shadow Aspect 64,675.2/250000 xp)
[10/14/2025 06:39]  System: You have lost a little karma.
[10/14/2025 06:39]  System: You have gained a very large amount of karma.
[10/14/2025 06:39]  GawdHammer: (Shadow Aspect 64,698.7/250000 xp)
[10/14/2025 06:39]  System: -8000
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:39]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:39]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:39]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:39]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:39]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:39]  a jackal spirit: a jackal spirit
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  You see: a sand muck corpse
[10/14/2025 06:39]  System: You must wait a few moments to use another skill.
[10/14/2025 06:39]  a sand muck: a sand muck
[10/14/2025 06:39]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:39]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:39]  a jackal spirit: a jackal spirit
[10/14/2025 06:39]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:39]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:39]  System: You have 23 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 23
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  System: You have 22 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 22
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  System: You have 21 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 21
[10/14/2025 06:39]  You see: a hierarch corpse
[10/14/2025 06:39]  a jackal spirit: a jackal spirit
[10/14/2025 06:39]  [Razor]: New target set.
[10/14/2025 06:39]  Razor: Target: a jackal spirit
[10/14/2025 06:39]  System: You have 20 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 20
[10/14/2025 06:39]  Razor: Attack: a jackal spirit
[10/14/2025 06:39]  System: You strike your target from the shadows!
[10/14/2025 06:39]  GawdHammer: You steal 287 gold coin.
[10/14/2025 06:39]  System: You steal 287 gold coin.
[10/14/2025 06:39]  GawdHammer: (Shadow Aspect 64,704.5/250000 xp)
[10/14/2025 06:39]  System: You have lost a little karma.
[10/14/2025 06:39]  System: You have gained a very large amount of karma.
[10/14/2025 06:39]  GawdHammer: (Shadow Aspect 64,728.0/250000 xp)
[10/14/2025 06:39]  System: -8000
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:39]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:39]  a cave bat: a cave bat
[10/14/2025 06:39]  You see: an ossuarian executioner corpse
[10/14/2025 06:39]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:39]  a sand muck: a sand muck
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:39]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:39]  System: You are no longer hindered.
[10/14/2025 06:39]  System: You have 29 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: New target set.
[10/14/2025 06:39]  Razor: Target: a jackal spirit
[10/14/2025 06:39]  [Razor]: Steps: 29
[10/14/2025 06:39]  You see: a sand muck corpse
[10/14/2025 06:39]  Razor: Attack: a jackal spirit
[10/14/2025 06:39]  System: You have 28 stealth steps remaining.
[10/14/2025 06:39]  [Razor]: Steps: 28
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  System: You strike your target from the shadows!
[10/14/2025 06:39]  System: You fail to steal from the creature.
[10/14/2025 06:39]  System: You have lost a little karma.
[10/14/2025 06:39]  System: You have gained a very large amount of karma.
[10/14/2025 06:39]  GawdHammer: (Shadow Aspect 64,751.4/250000 xp)
[10/14/2025 06:39]  System: -8000
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  You see: a phantasm corpse
[10/14/2025 06:39]  ApfelStrudel: Seasonal Guild
[10/14/2025 06:39]  ApfelStrudel: [Ripe, SQzD]
[10/14/2025 06:39]  ApfelStrudel: ApfelStrudel
[10/14/2025 06:39]  You see: a jackal spirit corpse
[10/14/2025 06:39]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:39]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:39]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:39]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:39]  a cave bat: a cave bat
[10/14/2025 06:39]  You see: an ossuarian executioner corpse
[10/14/2025 06:39]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:39]  a sand muck: a sand muck
[10/14/2025 06:39]  ApfelStrudel: 1 more for me
[10/14/2025 06:39]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:39]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:39]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:39]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:40]  [Razor]: New target set.
[10/14/2025 06:40]  Razor: Target: a sand muck
[10/14/2025 06:40]  System: You will automatically stealth when out of steps.
[10/14/2025 06:40]  Razor: [Auto-stealth ready.]
[10/14/2025 06:40]  System: You have 23 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 23
[10/14/2025 06:40]  an ossuarian executioner: an ossuarian executioner
[10/14/2025 06:40]  System: You have 22 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 22
[10/14/2025 06:40]  You see: a sand muck corpse
[10/14/2025 06:40]  You see: an ossuarian executioner corpse
[10/14/2025 06:40]  System: You have 21 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 21
[10/14/2025 06:40]  You see: a phantasm corpse
[10/14/2025 06:40]  System: You have 20 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 20
[10/14/2025 06:40]  System: You have 19 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 19
[10/14/2025 06:40]  You see: a phantasm corpse
[10/14/2025 06:40]  System: You have 18 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 18
[10/14/2025 06:40]  System: You have 17 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 17
[10/14/2025 06:40]  System: You have 16 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 16
[10/14/2025 06:40]  System: You have 15 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 15
[10/14/2025 06:40]  System: You have 14 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 14
[10/14/2025 06:40]  System: You have 13 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 13
[10/14/2025 06:40]  System: You have 12 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 12
[10/14/2025 06:40]  System: You have 11 stealth steps remaining.
[10/14/2025 06:40]  [Razor]: Steps: 11
[10/14/2025 06:40]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 06:40]  You see: a jackal spirit corpse
[10/14/2025 06:40]  System: You must wait another 20 seconds before you may use another smoke bomb.
[10/14/2025 06:40]  System: A swarm of dust penetrates and compromises your armor!
[10/14/2025 06:40]  GawdHammer: -20
[10/14/2025 06:40]  You see: a hierarch corpse
[10/14/2025 06:40]  a khamsin: a khamsin
[10/14/2025 06:40]  You see: an ossuarian firebrand corpse
[10/14/2025 06:40]  an ossuarian firebrand: an ossuarian firebrand
[10/14/2025 06:40]  GawdHammer: +20
[10/14/2025 06:40]  System: some damage has been healed : 20
[10/14/2025 06:40]  System: You drink a healing potion.
[10/14/2025 06:40]  You see: a blightwalker corpse
[10/14/2025 06:40]  a khamsin: a khamsin
[10/14/2025 06:40]  a khamsin: *charges*
[10/14/2025 06:40]  System: You are now under the protection of the town guards.
[10/14/2025 06:40]  [Razor]: Steps: 0
[10/14/2025 06:40]  GawdHammer: You summon your mount.
[10/14/2025 06:40]  a daemon: (summoned)
[10/14/2025 06:40]  a daemon: a daemon
[10/14/2025 06:40]  a daemon: (summoned)
[10/14/2025 06:40]  a daemon: a daemon
[10/14/2025 06:40]  Caffeinater: [Count Chocula, MILK]
[10/14/2025 06:40]  Caffeinater: Caffeinater
[10/14/2025 06:40]  Ridgley: Ridgley the tanner
[10/14/2025 06:40]  Hiroshi: Hiroshi the butcher
[10/14/2025 06:40]  Tertia: Tertia the bowyer
[10/14/2025 06:40]  Takiyah: Takiyah the carpenter
[10/14/2025 06:40]  Yesmina: Yesmina the bard
[10/14/2025 06:40]  Joye: Joye the powder monkey
[10/14/2025 06:40]  Merry Chrissmas: [Recruit, G.G]
[10/14/2025 06:40]  Merry Chrissmas: Merry Chrissmas
[10/14/2025 06:40]  an eagle: an eagle
[10/14/2025 06:40]  Maria: Maria the architect
[10/14/2025 06:40]  Rusty: Rusty the farmer
[10/14/2025 06:40]  Townsend: Townsend the alchemist
[10/14/2025 06:40]  [Razor]: Steps: 0
[10/14/2025 06:40]  Migina: Migina the provisioner
[10/14/2025 06:40]  Jacelyn: Jacelyn the scribe
[10/14/2025 06:40]  System: 68° 22'N, 177° 53'W
[10/14/2025 06:40]  System: Your skill in Tactics has decreased by 78.0.  It is now 0.0.
[10/14/2025 06:40]  GawdHammer: Kal Ort Por
[10/14/2025 06:40]  You see: gold coin : 500
[10/14/2025 06:40]  System: Now tracking: Cornwallis X (77 spaces to target)
[10/14/2025 06:40]  System: Your concentration is disturbed, thus ruining thy spell.
[10/14/2025 06:40]  Braden: Braden the tailor
[10/14/2025 06:40]  Jacinda: Jacinda the healer
[10/14/2025 06:40]  System: Your armor is no longer pierced.
[10/14/2025 06:40]  Bitalo: Bitalo the beggar
[10/14/2025 06:40]  Camelia: Camelia the jeweler
[10/14/2025 06:40]  Troglodyte Rind: Troglodyte Rind
[10/14/2025 06:40]  Zaide: Zaide the tinker
[10/14/2025 06:40]  Cayla: Cayla the mage
[10/14/2025 06:40]  Matana: Matana the innkeeper
[10/14/2025 06:40]  Ted Mistwind: Ted Mistwind
[10/14/2025 06:40]  System: Distance to destination: 65 steps.
[10/14/2025 06:40]  Cam: Cam the Town Crier
[10/14/2025 06:40]  Like: Like the tavern keeper
[10/14/2025 06:40]  Deva: Deva the waiter
[10/14/2025 06:40]  Lev: Lev the fisherman
[10/14/2025 06:40]  Trind: Trind the banker
[10/14/2025 06:40]  Chapman: Chapman the real estate broker
[10/14/2025 06:40]  System: Now tracking: Cornwallis X (58 spaces to target)
[10/14/2025 06:40]  Claire: Claire the baker
[10/14/2025 06:40]  Gerik: Gerik the cook
[10/14/2025 06:40]  System: You may now use another smoke bomb.
[10/14/2025 06:40]  madSurfeR: Legendary Scribe
[10/14/2025 06:40]  madSurfeR: [Juicy, SQzD]
[10/14/2025 06:40]  madSurfeR: madSurfeR
[10/14/2025 06:40]  System: 68° 22'N, 177° 53'W
[10/14/2025 06:40]  GawdHammer: Kal Ort Por
[10/14/2025 06:40]  System: Distance to destination: 58 steps.
[10/14/2025 06:40]  System: Now tracking: Cornwallis X (58 spaces to target)
[10/14/2025 06:40]  System: You have left the protection of the town guards.
[10/14/2025 06:40]  a horse: a horse
[10/14/2025 06:40]  a great hart: a great hart
[10/14/2025 06:40]  a crow: a crow
[10/14/2025 06:40]  a wisp: a wisp
[10/14/2025 06:40]  a grizzly bear: a grizzly bear
[10/14/2025 06:40]  a headless: a headless
[10/14/2025 06:40]  a cougar: a cougar
[10/14/2025 06:40]  a pack horse: (tame)
[10/14/2025 06:40]  a pack horse: [274/2000 stones]
[10/14/2025 06:40]  a pack horse: [68/125 items]
[10/14/2025 06:40]  a pack horse: a pack horse
[10/14/2025 06:40]  a hind: a hind
[10/14/2025 06:40]  a giant rat: a giant rat
[10/14/2025 06:40]  a pack horse: (tame)
[10/14/2025 06:40]  a pack horse: [453/2000 stones]
[10/14/2025 06:40]  a pack horse: [125/125 items]
[10/14/2025 06:40]  a pack horse: a pack horse
[10/14/2025 06:40]  an eagle: an eagle
[10/14/2025 06:40]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 06:40]  You see: a storage shelf
[10/14/2025 06:40]  You see: [secure]
[10/14/2025 06:40]  System: Which container do you wish to restock this container from? (you may target yourself or a nearby friendly pack animal)
[10/14/2025 06:40]  System: 27 items were added.
[10/14/2025 06:40]  [Razor]: Warning: Ginseng amount is now 0!
[10/14/2025 06:40]  [Razor]: Warning: Nightshade amount is now 0!
[10/14/2025 06:40]  [Razor]: Warning: Spider's Silk amount is now 0!
[10/14/2025 06:40]  [Razor]: Warning: Mandrake Root amount is now 0!
[10/14/2025 06:40]  [Razor]: Warning: Black Pearl amount is now 0!
[10/14/2025 06:40]  [Razor]: Warning: Blood Moss amount is now 0!
[10/14/2025 06:40]  You see: pouch
[10/14/2025 06:40]  You see: (35 items, 378 stones)
[10/14/2025 06:40]  [Razor]: Ignore List cleared
[10/14/2025 06:40]  [Razor]: drop - Not holding anything
[10/14/2025 06:40]  Razor: Select sort container
[10/14/2025 06:40]  [Razor]: Select target for variable 'sort_container'
[10/14/2025 06:40]  [Razor]: 'sort_container' script variable updated to '0x4C853FD4'
[10/14/2025 06:40]  Razor: unidentified leather cap
[10/14/2025 06:40]  [Razor]: Added 1284451637 to ignore list
[10/14/2025 06:40]  Razor: unidentified leather cap
[10/14/2025 06:40]  [Razor]: Added 1284130456 to ignore list
[10/14/2025 06:40]  Razor: unidentified bone legs
[10/14/2025 06:40]  [Razor]: Added 1284403945 to ignore list
[10/14/2025 06:40]  Razor: unidentified bone skirt
[10/14/2025 06:40]  [Razor]: Added 1284264618 to ignore list
[10/14/2025 06:40]  Razor: unidentified studded arms
[10/14/2025 06:40]  [Razor]: Added 1284549517 to ignore list
[10/14/2025 06:40]  Razor: unidentified studded gloves
[10/14/2025 06:40]  [Razor]: Added 1284512742 to ignore list
[10/14/2025 06:41]  Razor: unidentified studded skirt
[10/14/2025 06:41]  [Razor]: Added 1284673316 to ignore list
[10/14/2025 06:41]  Razor: unidentified studded bustier
[10/14/2025 06:41]  [Razor]: Added 1284377286 to ignore list
[10/14/2025 06:41]  Razor: unidentified ringmail arms
[10/14/2025 06:41]  [Razor]: Added 1284535623 to ignore list
[10/14/2025 06:41]  Razor: unidentified ringmail gloves
[10/14/2025 06:41]  [Razor]: Added 1284347027 to ignore list
[10/14/2025 06:41]  Razor: unidentified ringmail helm
[10/14/2025 06:41]  [Razor]: Added 1284163499 to ignore list
[10/14/2025 06:41]  Razor: unidentified chainmail arms
[10/14/2025 06:41]  [Razor]: Added 1284279005 to ignore list
[10/14/2025 06:41]  Razor: unidentified chainmail leggings
[10/14/2025 06:41]  [Razor]: Added 1284333423 to ignore list
[10/14/2025 06:41]  Razor: unidentified platemail gloves
[10/14/2025 06:41]  [Razor]: Added 1284147484 to ignore list
[10/14/2025 06:41]  Razor: unidentified boneshard staff
[10/14/2025 06:41]  [Razor]: Added 1284652890 to ignore list
[10/14/2025 06:41]  a great hart: a great hart
[10/14/2025 06:41]  Razor: unidentified druid staff
[10/14/2025 06:41]  [Razor]: Added 1284635113 to ignore list
[10/14/2025 06:41]  Razor: unidentified recurve bow
[10/14/2025 06:41]  [Razor]: Added 1284419394 to ignore list
[10/14/2025 06:41]  Razor: unidentified magic spellbook
[10/14/2025 06:41]  [Razor]: Added 1284502824 to ignore list
[10/14/2025 06:41]  Razor: unidentified magic spellbook
[10/14/2025 06:41]  [Razor]: Added 1284666552 to ignore list
[10/14/2025 06:41]  a wolf: a wolf
[10/14/2025 06:41]  Razor: unidentified magic spellbook
[10/14/2025 06:41]  [Razor]: Added 1284228982 to ignore list
[10/14/2025 06:41]  Razor: unidentified magic spellbook
[10/14/2025 06:41]  [Razor]: Added 1284317085 to ignore list
[10/14/2025 06:41]  Razor: unidentified parry dagger
[10/14/2025 06:41]  [Razor]: Added 1284178071 to ignore list
[10/14/2025 06:41]  Razor: unidentified longsword
[10/14/2025 06:41]  [Razor]: Added 1284563115 to ignore list
[10/14/2025 06:41]  Razor: unidentified norse axe
[10/14/2025 06:41]  [Razor]: Added 1284289076 to ignore list
[10/14/2025 06:41]  Razor: sapphire : 10
[10/14/2025 06:41]  [Razor]: Added 1284178070 to ignore list
[10/14/2025 06:41]  Razor: citrine : 8
[10/14/2025 06:41]  [Razor]: Added 1284264617 to ignore list
[10/14/2025 06:41]  Razor: amber : 4
[10/14/2025 06:41]  [Razor]: Added 1284228981 to ignore list
[10/14/2025 06:41]  Razor: diamond : 2
[10/14/2025 06:41]  [Razor]: Added 1284673315 to ignore list
[10/14/2025 06:41]  Razor: ruby : 6
[10/14/2025 06:41]  [Razor]: Added 1284419393 to ignore list
[10/14/2025 06:41]  Razor: spellshielding rune (Effective magic resist increased by 50) [double click to place]
[10/14/2025 06:41]  [Razor]: Added 1284673317 to ignore list
[10/14/2025 06:41]  Razor: Death Aspect Core
[10/14/2025 06:41]  [Razor]: Added 1284178072 to ignore list
[10/14/2025 06:41]  Razor: Fortune Aspect Core
[10/14/2025 06:41]  [Razor]: Added 1284549518 to ignore list
[10/14/2025 06:41]  Razor: Blood Aspect Distillation
[10/14/2025 06:41]  [Razor]: Added 1284652891 to ignore list
[10/14/2025 06:41]  Razor: Command Aspect Distillation
[10/14/2025 06:41]  [Razor]: Added 1284451638 to ignore list
[10/14/2025 06:41]  System: You deposit 29,773 gold into your bank box.
[10/14/2025 06:41]  You see: a storage shelf
[10/14/2025 06:41]  You see: [secure]
[10/14/2025 06:41]  System: You deposit 8,327 gold into your bank box.
[10/14/2025 06:41]  [Razor]: lift - Item not found or out of range
[10/14/2025 06:41]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:41]  [Razor]: drop - Not holding anything
[10/14/2025 06:41]  Razor: Done.
[10/14/2025 06:41]  You see: a storage shelf
[10/14/2025 06:41]  You see: [secure]
[10/14/2025 06:41]  You see: pouch
[10/14/2025 06:41]  You see: (0 items, 0 stones)
[10/14/2025 06:41]  You see: a storage shelf
[10/14/2025 06:41]  You see: [secure]
[10/14/2025 06:41]  System: Which container do you wish to restock this container from? (you may target yourself or a nearby friendly pack animal)
[10/14/2025 06:41]  System: 1 items were added.
[10/14/2025 06:41]  You see: a storage shelf
[10/14/2025 06:41]  You see: [secure]
[10/14/2025 06:41]  System: Resupply complete.
[10/14/2025 06:41]  an orc: an orc
[10/14/2025 06:41]  an orc: an orc
[10/14/2025 06:42]  System: Opening door...
[10/14/2025 06:42]  System: As the owner, you refresh the house and its contents.
[10/14/2025 06:42]  a wisp: a wisp
[10/14/2025 06:42]  a great hart: a great hart
[10/14/2025 06:42]  a wolf: a wolf
[10/14/2025 06:42]  System: 16° 5'N, 25° 48'E
[10/14/2025 06:42]  GawdHammer: Kal Ort Por
[10/14/2025 06:42]  System: You are now under the protection of the town guards.
[10/14/2025 06:42]  Corrine: Corrine the Monster Hunter Society Master
[10/14/2025 06:42]  ThereIsNoSpoon: Boss Slayer
[10/14/2025 06:42]  ThereIsNoSpoon: [G.G]
[10/14/2025 06:42]  ThereIsNoSpoon: Lord ThereIsNoSpoon
[10/14/2025 06:42]  Osgood: Osgood the Adventurer Lodge Master
[10/14/2025 06:42]  farmer dan: farmer dan
[10/14/2025 06:42]  a rag witch: (summoned)
[10/14/2025 06:42]  a rag witch: a rag witch
[10/14/2025 06:42]  a rag witch: (summoned)
[10/14/2025 06:42]  a rag witch: a rag witch
[10/14/2025 06:42]  a ghost: (summoned)
[10/14/2025 06:42]  a ghost: a ghost
[10/14/2025 06:42]  Galvin: Galvin the Tradesman Union Master
[10/14/2025 06:42]  Beradine: Beradine the Order of Armorers Master
[10/14/2025 06:42]  Kaliya: Kaliya the Artificer Enclave Master
[10/14/2025 06:42]  Old Vescio: Old Vescio the Seafarer League Master
[10/14/2025 06:42]  Osgood: Osgood the Adventurer Lodge Master
[10/14/2025 06:42]  System: Your skill in Tactics has increased by 78.0.  It is now 78.0.
[10/14/2025 06:42]  Osgood: Osgood the Adventurer Lodge Master
[10/14/2025 06:42]  Osgood: The Adventurer Lodge has the following jobs available this week.
[10/14/2025 06:42]  System: You accept the job.
[10/14/2025 06:42]  System: 0 remaining jobs available for account.
[10/14/2025 06:42]  System: 68° 22'N, 177° 53'W
[10/14/2025 06:42]  System: Your skill in Tactics has decreased by 78.0.  It is now 0.0.
[10/14/2025 06:42]  GawdHammer: Kal Ort Por
[10/14/2025 06:42]  System: You have left the protection of the town guards.
[10/14/2025 06:42]  a horse: a horse
[10/14/2025 06:42]  an orc: an orc
[10/14/2025 06:42]  a crow: a crow
[10/14/2025 06:42]  a great hart: a great hart
[10/14/2025 06:42]  a wolf: a wolf
[10/14/2025 06:42]  a wisp: a wisp
[10/14/2025 06:42]  a giant spider: a giant spider
[10/14/2025 06:42]  a cougar: a cougar
[10/14/2025 06:42]  a pack horse: (tame)
[10/14/2025 06:42]  a pack horse: [344/2000 stones]
[10/14/2025 06:42]  a pack horse: [92/125 items]
[10/14/2025 06:42]  a pack horse: a pack horse
[10/14/2025 06:42]  a headless: a headless
[10/14/2025 06:42]  a giant rat: a giant rat
[10/14/2025 06:42]  a grizzly bear: a grizzly bear
[10/14/2025 06:42]  a pack horse: (tame)
[10/14/2025 06:42]  a pack horse: [453/2000 stones]
[10/14/2025 06:42]  a pack horse: [125/125 items]
[10/14/2025 06:42]  a pack horse: a pack horse
[10/14/2025 06:42]  a hind: a hind
[10/14/2025 06:42]  an eagle: an eagle
[10/14/2025 06:42]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 06:42]  a great hart: a great hart
[10/14/2025 06:42]  a black bear: a black bear
[10/14/2025 06:42]  System: Your skill in Tactics has increased by 78.0.  It is now 78.0.
[10/14/2025 06:43]  System: Opening door...
[10/14/2025 06:43]  System: As the owner, you refresh the house and its contents.
[10/14/2025 06:43]  a black bear: a black bear
[10/14/2025 06:43]  a wolf: a wolf
[10/14/2025 06:43]  System: Your skill in Tactics has decreased by 78.0.  It is now 0.0.
[10/14/2025 06:43]  GawdHammer: Kal Ort Por
[10/14/2025 06:43]  System: One or more of your pack animals is not allowed in this location.
[10/14/2025 06:43]  GawdHammer: You dismiss your mount.
[10/14/2025 06:43]  a stonescale tribal: a stonescale tribal
[10/14/2025 06:43]  a stonescale tribal: a stonescale tribal
[10/14/2025 06:43]  a drake whelp: a drake whelp
[10/14/2025 06:43]  a dragon whelp: a dragon whelp
[10/14/2025 06:43]  a drake whelp: a drake whelp
[10/14/2025 06:43]  System: Your skill in Tactics has increased by 78.0.  It is now 78.0.
[10/14/2025 06:43]  a dragon whelp: a dragon whelp
[10/14/2025 06:43]  a monitor: a monitor
[10/14/2025 06:43]  a monitor: a monitor
[10/14/2025 06:43]  a monitor: a monitor
[10/14/2025 06:43]  a drake whelp: a drake whelp
[10/14/2025 06:43]  a primordial whelp: a primordial whelp
[10/14/2025 06:43]  a dragon whelp: a dragon whelp
[10/14/2025 06:43]  You see: an earth drake corpse
[10/14/2025 06:43]  You see: an earth drake corpse
[10/14/2025 06:43]  an earth drake: an earth drake
[10/14/2025 06:43]  You see: an army ant corpse
[10/14/2025 06:43]  You see: an earth drake corpse
[10/14/2025 06:43]  You see: an army ant corpse
[10/14/2025 06:43]  a jackal spirit: (summoned)
[10/14/2025 06:43]  a jackal spirit: a jackal spirit
[10/14/2025 06:43]  You see: a drake corpse
[10/14/2025 06:43]  You see: an earth drake corpse
[10/14/2025 06:43]  PaLoudaq: Legendarily Triggered
[10/14/2025 06:43]  PaLoudaq: [Hopeless Addiction, FNT]
[10/14/2025 06:43]  PaLoudaq: Lord PaLoudaq
[10/14/2025 06:43]  a chameleon: a chameleon
[10/14/2025 06:43]  You see: a drake corpse
[10/14/2025 06:43]  You see: a drake corpse
[10/14/2025 06:43]  an earth drake: an earth drake
[10/14/2025 06:43]  an earth drake: an earth drake
[10/14/2025 06:43]  a jackal spirit: (summoned)
[10/14/2025 06:43]  a jackal spirit: a jackal spirit
[10/14/2025 06:43]  You see: an earth drake corpse
[10/14/2025 06:43]  You see: a drake corpse
[10/14/2025 06:43]  You see: a drake corpse
[10/14/2025 06:43]  You see: an earth drake corpse
[10/14/2025 06:43]  a drake: a drake
[10/14/2025 06:43]  a drake: a drake
[10/14/2025 06:43]  a drake: a drake
[10/14/2025 06:43]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:43]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:43]  [Razor]: Steps: 0
[10/14/2025 06:43]  System: You are no longer hindered.
[10/14/2025 06:43]  System: You have 29 stealth steps remaining.
[10/14/2025 06:43]  [Razor]: Steps: 29
[10/14/2025 06:43]  System: You have 28 stealth steps remaining.
[10/14/2025 06:43]  [Razor]: Steps: 28
[10/14/2025 06:43]  System: Target the container to Grab items into.
[10/14/2025 06:43]  System: Grab Bag set: 1284757672
[10/14/2025 06:43]  [Razor]: New target set.
[10/14/2025 06:43]  Razor: Target: a drake
[10/14/2025 06:43]  System: You have 27 stealth steps remaining.
[10/14/2025 06:43]  [Razor]: Steps: 27
[10/14/2025 06:43]  System: You have 26 stealth steps remaining.
[10/14/2025 06:43]  [Razor]: Steps: 26
[10/14/2025 06:43]  Razor: Attack: a drake
[10/14/2025 06:43]  System: You have 25 stealth steps remaining.
[10/14/2025 06:43]  [Razor]: Steps: 25
[10/14/2025 06:43]  System: You have 24 stealth steps remaining.
[10/14/2025 06:43]  [Razor]: Steps: 24
[10/14/2025 06:43]  System: You have 23 stealth steps remaining.
[10/14/2025 06:43]  System: You strike your target from the shadows!
[10/14/2025 06:43]  GawdHammer: You steal 197 gold coin.
[10/14/2025 06:43]  System: You steal 197 gold coin.
[10/14/2025 06:43]  GawdHammer: (Shadow Aspect 64,755.5/250000 xp)
[10/14/2025 06:43]  System: You have lost a little karma.
[10/14/2025 06:43]  System: You have gained a lot of karma.
[10/14/2025 06:43]  GawdHammer: (Shadow Aspect 64,772.0/250000 xp)
[10/14/2025 06:43]  System: -6000
[10/14/2025 06:43]  You see: a drake corpse
[10/14/2025 06:44]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:44]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:44]  You see: an adder corpse
[10/14/2025 06:44]  You see: a smoke drake corpse
[10/14/2025 06:44]  System: VonDarkmane has registered an antiquity: Terathan Goliath Figurine
[10/14/2025 06:44]  an adder: an adder
[10/14/2025 06:44]  You see: a smoke drake corpse
[10/14/2025 06:44]  GawdHammer: -39
[10/14/2025 06:44]  a smoke drake: a smoke drake
[10/14/2025 06:44]  You see: an eldritch drake corpse
[10/14/2025 06:44]  You see: an eldritch drake corpse
[10/14/2025 06:44]  You see: an eldritch drake corpse
[10/14/2025 06:44]  GawdHammer: +30
[10/14/2025 06:44]  System: some damage has been healed : 30
[10/14/2025 06:44]  System: You drink a healing potion.
[10/14/2025 06:44]  an eldritch drake: an eldritch drake
[10/14/2025 06:44]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:44]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:44]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:44]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:44]  System: You have 23 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 23
[10/14/2025 06:44]  System: You have 22 stealth steps remaining.
[10/14/2025 06:44]  GawdHammer: +5
[10/14/2025 06:44]  System: some damage has been healed : 5
[10/14/2025 06:44]  [Razor]: Steps: 22
[10/14/2025 06:44]  System: You have 21 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 21
[10/14/2025 06:44]  System: You have 20 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 20
[10/14/2025 06:44]  System: You have 19 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 19
[10/14/2025 06:44]  System: You have 18 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 18
[10/14/2025 06:44]  System: You have 17 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 17
[10/14/2025 06:44]  System: You have 16 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 16
[10/14/2025 06:44]  System: You have 15 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 15
[10/14/2025 06:44]  System: You have 14 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 14
[10/14/2025 06:44]  System: You have 13 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 13
[10/14/2025 06:44]  System: You have 12 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 12
[10/14/2025 06:44]  System: You have 11 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 11
[10/14/2025 06:44]  System: You have 10 stealth steps remaining.
[10/14/2025 06:44]  Razor: [10 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 10
[10/14/2025 06:44]  System: You have 9 stealth steps remaining.
[10/14/2025 06:44]  Razor: [9 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 9
[10/14/2025 06:44]  System: You have 8 stealth steps remaining.
[10/14/2025 06:44]  Razor: [8 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 8
[10/14/2025 06:44]  System: You have 7 stealth steps remaining.
[10/14/2025 06:44]  Razor: [7 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 7
[10/14/2025 06:44]  You see: an ember drake corpse
[10/14/2025 06:44]  System: You have 6 stealth steps remaining.
[10/14/2025 06:44]  Razor: [6 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 6
[10/14/2025 06:44]  an ember drake: an ember drake
[10/14/2025 06:44]  System: You have 5 stealth steps remaining.
[10/14/2025 06:44]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:44]  [Razor]: Steps: 5
[10/14/2025 06:44]  an ember drake: an ember drake
[10/14/2025 06:44]  System: You will automatically stealth when out of steps.
[10/14/2025 06:44]  Razor: [Auto-stealth ready.]
[10/14/2025 06:44]  System: You have 4 stealth steps remaining.
[10/14/2025 06:44]  Razor: [4 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 4
[10/14/2025 06:44]  System: You have 3 stealth steps remaining.
[10/14/2025 06:44]  Razor: [3 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 3
[10/14/2025 06:44]  System: You have 2 stealth steps remaining.
[10/14/2025 06:44]  Razor: [2 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 2
[10/14/2025 06:44]  System: You have 1 stealth steps remaining.
[10/14/2025 06:44]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:44]  [Razor]: Steps: 1
[10/14/2025 06:44]  System: You have 0 stealth steps remaining.
[10/14/2025 06:44]  System: You feel ready to continue stealthing.
[10/14/2025 06:44]  Razor: [Stop!]
[10/14/2025 06:44]  [Razor]: Steps: 0
[10/14/2025 06:44]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:44]  System: You have 23 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 23
[10/14/2025 06:44]  System: You have 22 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 22
[10/14/2025 06:44]  System: You have 21 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 21
[10/14/2025 06:44]  System: You have 20 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 20
[10/14/2025 06:44]  System: You have 19 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 19
[10/14/2025 06:44]  System: You have 18 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 18
[10/14/2025 06:44]  System: You have 17 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 17
[10/14/2025 06:44]  System: You have 16 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 16
[10/14/2025 06:44]  System: You have 15 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 15
[10/14/2025 06:44]  System: You have 14 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 14
[10/14/2025 06:44]  System: You have 13 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 13
[10/14/2025 06:44]  System: You have 12 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 12
[10/14/2025 06:44]  System: You have 11 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 11
[10/14/2025 06:44]  System: You have 10 stealth steps remaining.
[10/14/2025 06:44]  Razor: [10 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 10
[10/14/2025 06:44]  System: You have 9 stealth steps remaining.
[10/14/2025 06:44]  Razor: [9 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 9
[10/14/2025 06:44]  System: You have 8 stealth steps remaining.
[10/14/2025 06:44]  Razor: [8 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 8
[10/14/2025 06:44]  System: You have 7 stealth steps remaining.
[10/14/2025 06:44]  Razor: [7 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 7
[10/14/2025 06:44]  System: You have 6 stealth steps remaining.
[10/14/2025 06:44]  Razor: [6 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 6
[10/14/2025 06:44]  System: You have 5 stealth steps remaining.
[10/14/2025 06:44]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:44]  [Razor]: Steps: 5
[10/14/2025 06:44]  System: You have 4 stealth steps remaining.
[10/14/2025 06:44]  Razor: [4 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 4
[10/14/2025 06:44]  System: You have 3 stealth steps remaining.
[10/14/2025 06:44]  Razor: [3 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 3
[10/14/2025 06:44]  System: You will automatically stealth when out of steps.
[10/14/2025 06:44]  Razor: [Auto-stealth ready.]
[10/14/2025 06:44]  [Razor]: Steps: 3
[10/14/2025 06:44]  System: You may now use another smoke bomb.
[10/14/2025 06:44]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:44]  System: You have 2 stealth steps remaining.
[10/14/2025 06:44]  Razor: [2 Steps...]
[10/14/2025 06:44]  [Razor]: Steps: 2
[10/14/2025 06:44]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:44]  System: You have 1 stealth steps remaining.
[10/14/2025 06:44]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:44]  [Razor]: Steps: 1
[10/14/2025 06:44]  System: You have 0 stealth steps remaining.
[10/14/2025 06:44]  System: You feel ready to continue stealthing.
[10/14/2025 06:44]  Razor: [Stop!]
[10/14/2025 06:44]  [Razor]: Steps: 0
[10/14/2025 06:44]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:44]  System: You have 23 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 23
[10/14/2025 06:44]  System: You have 22 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 22
[10/14/2025 06:44]  System: You have 21 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 21
[10/14/2025 06:44]  System: You have 20 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 20
[10/14/2025 06:44]  System: You have 19 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 19
[10/14/2025 06:44]  System: You have 18 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 18
[10/14/2025 06:44]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:44]  System: You have 17 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 17
[10/14/2025 06:44]  System: You have 16 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 16
[10/14/2025 06:44]  an earth dragon: an earth dragon
[10/14/2025 06:44]  System: You have 15 stealth steps remaining.
[10/14/2025 06:44]  [Razor]: Steps: 15
[10/14/2025 06:44]  System: You have 14 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 14
[10/14/2025 06:45]  System: You have 13 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 13
[10/14/2025 06:45]  System: You have 12 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 12
[10/14/2025 06:45]  System: You have 11 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 11
[10/14/2025 06:45]  System: You have 10 stealth steps remaining.
[10/14/2025 06:45]  Razor: [10 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 10
[10/14/2025 06:45]  an earth dragon: an earth dragon
[10/14/2025 06:45]  System: You have 9 stealth steps remaining.
[10/14/2025 06:45]  Razor: [9 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 9
[10/14/2025 06:45]  System: [SR] A Stygian Rifts event will begin in 15 minutes. Players may sign up in the Arena at the Stygian Rift section of the region. Type [BattleHotbar to view the Battle Hotbar for this event.
[10/14/2025 06:45]  System: You have 8 stealth steps remaining.
[10/14/2025 06:45]  Razor: [8 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 8
[10/14/2025 06:45]  System: You have 7 stealth steps remaining.
[10/14/2025 06:45]  Razor: [7 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 7
[10/14/2025 06:45]  System: You have 6 stealth steps remaining.
[10/14/2025 06:45]  Razor: [6 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 6
[10/14/2025 06:45]  System: You have 5 stealth steps remaining.
[10/14/2025 06:45]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:45]  [Razor]: Steps: 5
[10/14/2025 06:45]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:45]  System: You have 4 stealth steps remaining.
[10/14/2025 06:45]  Razor: [4 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 4
[10/14/2025 06:45]  System: You have 3 stealth steps remaining.
[10/14/2025 06:45]  Razor: [3 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 3
[10/14/2025 06:45]  System: You have 2 stealth steps remaining.
[10/14/2025 06:45]  Razor: [2 Steps...]
[10/14/2025 06:45]  [Razor]: Steps: 2
[10/14/2025 06:45]  System: You have 1 stealth steps remaining.
[10/14/2025 06:45]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:45]  [Razor]: Steps: 1
[10/14/2025 06:45]  [Razor]: New target set.
[10/14/2025 06:45]  Razor: Target: an ophidian dragonguard
[10/14/2025 06:45]  System: You will automatically stealth when out of steps.
[10/14/2025 06:45]  Razor: [Auto-stealth ready.]
[10/14/2025 06:45]  Razor: Attack: an ophidian dragonguard
[10/14/2025 06:45]  System: You strike your target from the shadows!
[10/14/2025 06:45]  System: You fail to steal from the creature.
[10/14/2025 06:45]  System: You have lost a little karma.
[10/14/2025 06:45]  System: You have gained a very large amount of karma.
[10/14/2025 06:45]  GawdHammer: (Shadow Aspect 64,794.4/250000 xp)
[10/14/2025 06:45]  System: -8000
[10/14/2025 06:45]  You see: an ophidian dragonguard corpse
[10/14/2025 06:45]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:45]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:45]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:45]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:45]  System: You have 23 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 23
[10/14/2025 06:45]  System: You have 22 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 22
[10/14/2025 06:45]  System: You have 21 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 21
[10/14/2025 06:45]  [Razor]: New target set.
[10/14/2025 06:45]  Razor: Target: an ophidian dragonguard
[10/14/2025 06:45]  System: You have 20 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 20
[10/14/2025 06:45]  System: You have 19 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 19
[10/14/2025 06:45]  Razor: Attack: an ophidian dragonguard
[10/14/2025 06:45]  System: You have 18 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 18
[10/14/2025 06:45]  System: You have 17 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 17
[10/14/2025 06:45]  System: You have 16 stealth steps remaining.
[10/14/2025 06:45]  System: You strike your target from the shadows!
[10/14/2025 06:45]  GawdHammer: You steal 302 gold coin.
[10/14/2025 06:45]  System: You steal 302 gold coin.
[10/14/2025 06:45]  GawdHammer: (Shadow Aspect 64,799.9/250000 xp)
[10/14/2025 06:45]  System: You have lost a little karma.
[10/14/2025 06:45]  System: You have gained a very large amount of karma.
[10/14/2025 06:45]  GawdHammer: (Shadow Aspect 64,822.3/250000 xp)
[10/14/2025 06:45]  System: -8000
[10/14/2025 06:45]  You see: an ophidian dragonguard corpse
[10/14/2025 06:45]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:45]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:45]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:45]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:45]  You see: an ophidian dragonguard corpse
[10/14/2025 06:45]  an earth dragon: an earth dragon
[10/14/2025 06:45]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:45]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:45]  [Razor]: New target set.
[10/14/2025 06:45]  Razor: Target: an earth dragon
[10/14/2025 06:45]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:45]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:45]  System: You have 23 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 23
[10/14/2025 06:45]  System: You have 22 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 22
[10/14/2025 06:45]  System: You have 21 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 21
[10/14/2025 06:45]  an earth dragon: an earth dragon
[10/14/2025 06:45]  System: You have 20 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 20
[10/14/2025 06:45]  System: You have 19 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 19
[10/14/2025 06:45]  System: You have 18 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 18
[10/14/2025 06:45]  System: You have 17 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 17
[10/14/2025 06:45]  System: You have 16 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 16
[10/14/2025 06:45]  System: You will automatically stealth when out of steps.
[10/14/2025 06:45]  Razor: [Auto-stealth ready.]
[10/14/2025 06:45]  System: You have 15 stealth steps remaining.
[10/14/2025 06:45]  Razor: Attack: an earth dragon
[10/14/2025 06:45]  [Razor]: Steps: 15
[10/14/2025 06:45]  an earth dragon: an earth dragon
[10/14/2025 06:45]  System: You have 14 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 14
[10/14/2025 06:45]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:45]  System: You have 13 stealth steps remaining.
[10/14/2025 06:45]  System: You strike your target from the shadows!
[10/14/2025 06:45]  GawdHammer: You steal 390 gold coin.
[10/14/2025 06:45]  System: You steal 390 gold coin.
[10/14/2025 06:45]  GawdHammer: (Shadow Aspect 64,829.5/250000 xp)
[10/14/2025 06:45]  System: You have lost a little karma.
[10/14/2025 06:45]  an earth dragon: -7143
[10/14/2025 06:45]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:45]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:45]  System: You are no longer hindered.
[10/14/2025 06:45]  System: You have 29 stealth steps remaining.
[10/14/2025 06:45]  [Razor]: Steps: 29
[10/14/2025 06:46]  System: You have 28 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 28
[10/14/2025 06:46]  System: You have 27 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 27
[10/14/2025 06:46]  an earth dragon: an earth dragon
[10/14/2025 06:46]  System: You have 26 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 26
[10/14/2025 06:46]  System: You strike your target from the shadows!
[10/14/2025 06:46]  System: You have already stolen from this creature.
[10/14/2025 06:46]  System: You have gained a very large amount of karma.
[10/14/2025 06:46]  GawdHammer: (Shadow Aspect 64,858.3/250000 xp)
[10/14/2025 06:46]  System: -2857
[10/14/2025 06:46]  You see: an earth dragon corpse
[10/14/2025 06:46]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:46]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:46]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:46]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:46]  System: You have 23 stealth steps remaining.
[10/14/2025 06:46]  System: You must wait a few moments to use another skill.
[10/14/2025 06:46]  [Razor]: Steps: 23
[10/14/2025 06:46]  System: You have 22 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 22
[10/14/2025 06:46]  System: You have 21 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 21
[10/14/2025 06:46]  System: You have 20 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 20
[10/14/2025 06:46]  System: You have 19 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 19
[10/14/2025 06:46]  System: You have 18 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 18
[10/14/2025 06:46]  System: You have 17 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 17
[10/14/2025 06:46]  an earth dragon: an earth dragon
[10/14/2025 06:46]  System: You have 16 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 16
[10/14/2025 06:46]  System: You have 15 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 15
[10/14/2025 06:46]  You see: an ophidian dragonguard corpse
[10/14/2025 06:46]  System: You have 14 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 14
[10/14/2025 06:46]  System: You have 13 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 13
[10/14/2025 06:46]  System: You have 12 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 12
[10/14/2025 06:46]  [Razor]: New target set.
[10/14/2025 06:46]  Razor: Target: an earth dragon
[10/14/2025 06:46]  System: You have 11 stealth steps remaining.
[10/14/2025 06:46]  [Razor]: Steps: 11
[10/14/2025 06:46]  an earth dragon: an earth dragon
[10/14/2025 06:46]  System: You have 10 stealth steps remaining.
[10/14/2025 06:46]  Razor: [10 Steps...]
[10/14/2025 06:46]  [Razor]: Steps: 10
[10/14/2025 06:46]  System: You have 9 stealth steps remaining.
[10/14/2025 06:46]  Razor: [9 Steps...]
[10/14/2025 06:46]  [Razor]: Steps: 9
[10/14/2025 06:46]  System: You have 8 stealth steps remaining.
[10/14/2025 06:46]  Razor: [8 Steps...]
[10/14/2025 06:46]  Razor: Attack: an earth dragon
[10/14/2025 06:46]  [Razor]: Steps: 8
[10/14/2025 06:46]  System: You strike your target from the shadows!
[10/14/2025 06:46]  System: You fail to steal from the creature.
[10/14/2025 06:46]  System: You have lost a little karma.
[10/14/2025 06:46]  an earth dragon: -7579
[10/14/2025 06:46]  GawdHammer: -10
[10/14/2025 06:46]  an earth dragon: an earth dragon
[10/14/2025 06:46]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:46]  GawdHammer: +10
[10/14/2025 06:46]  System: some damage has been healed : 10
[10/14/2025 06:46]  System: You drink a healing potion.
[10/14/2025 06:46]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:46]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:46]  [Razor]: New target set.
[10/14/2025 06:46]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:46]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:46]  System: You strike your target from the shadows!
[10/14/2025 06:46]  GawdHammer: You steal 348 gold coin.
[10/14/2025 06:46]  System: You steal 348 gold coin.
[10/14/2025 06:46]  GawdHammer: (Shadow Aspect 64,865.4/250000 xp)
[10/14/2025 06:46]  System: You have lost a little karma.
[10/14/2025 06:46]  System: You have gained a very large amount of karma.
[10/14/2025 06:46]  GawdHammer: (Shadow Aspect 64,894.2/250000 xp)
[10/14/2025 06:46]  System: -2421
[10/14/2025 06:46]  You see: an earth dragon corpse
[10/14/2025 06:46]  : cave floor
[10/14/2025 06:46]  : cave floor
[10/14/2025 06:46]  : cave floor
[10/14/2025 06:46]  : cave floor
[10/14/2025 06:46]  You see: an earth dragon corpse
[10/14/2025 06:46]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:46]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:46]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:46]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:46]  an earth dragon: an earth dragon
[10/14/2025 06:46]  You see: an ophidian dragonguard corpse
[10/14/2025 06:46]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:46]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:46]  System: You may now use another smoke bomb.
[10/14/2025 06:47]  System: Shadownix has completed the achievement: Treasure Hunter (Advanced).
[10/14/2025 06:47]  System: The world will save in 15 seconds.
[10/14/2025 06:47]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:47]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:47]  System: You have 23 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 23
[10/14/2025 06:47]  System: You have 22 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 22
[10/14/2025 06:47]  an earth dragon: an earth dragon
[10/14/2025 06:47]  System: You have 21 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 21
[10/14/2025 06:47]  System: You have 20 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 20
[10/14/2025 06:47]  System: You have 19 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 19
[10/14/2025 06:47]  System: You have 18 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 18
[10/14/2025 06:47]  System: You have 17 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 17
[10/14/2025 06:47]  System: You have 16 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 16
[10/14/2025 06:47]  System: You have 15 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 15
[10/14/2025 06:47]  System: You have 14 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: New target set.
[10/14/2025 06:47]  Razor: Target: an earth dragon
[10/14/2025 06:47]  [Razor]: Steps: 14
[10/14/2025 06:47]  System: You have 13 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 13
[10/14/2025 06:47]  a dragon: a dragon
[10/14/2025 06:47]  System: You have 12 stealth steps remaining.
[10/14/2025 06:47]  Razor: Attack: an earth dragon
[10/14/2025 06:47]  [Razor]: Steps: 12
[10/14/2025 06:47]  System: You have 11 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 11
[10/14/2025 06:47]  System: You will automatically stealth when out of steps.
[10/14/2025 06:47]  Razor: [Auto-stealth ready.]
[10/14/2025 06:47]  a dragon: a dragon
[10/14/2025 06:47]  System: You have 10 stealth steps remaining.
[10/14/2025 06:47]  Razor: [10 Steps...]
[10/14/2025 06:47]  [Razor]: Steps: 10
[10/14/2025 06:47]  System: You have 9 stealth steps remaining.
[10/14/2025 06:47]  Razor: [9 Steps...]
[10/14/2025 06:47]  [Razor]: Steps: 9
[10/14/2025 06:47]  System: You have 8 stealth steps remaining.
[10/14/2025 06:47]  Razor: [8 Steps...]
[10/14/2025 06:47]  [Razor]: Steps: 8
[10/14/2025 06:47]  System: You strike your target from the shadows!
[10/14/2025 06:47]  GawdHammer: You steal 382 gold coin.
[10/14/2025 06:47]  System: You steal 382 gold coin.
[10/14/2025 06:47]  GawdHammer: (Shadow Aspect 64,901.4/250000 xp)
[10/14/2025 06:47]  System: You have lost a little karma.
[10/14/2025 06:47]  an earth dragon: -7414
[10/14/2025 06:47]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 06:47]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:47]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:47]  System: You are no longer hindered.
[10/14/2025 06:47]  System: You strike your target from the shadows!
[10/14/2025 06:47]  System: You have already stolen from this creature.
[10/14/2025 06:47]  System: You have gained a very large amount of karma.
[10/14/2025 06:47]  GawdHammer: (Shadow Aspect 64,930.1/250000 xp)
[10/14/2025 06:47]  System: -2586
[10/14/2025 06:47]  You see: an earth dragon corpse
[10/14/2025 06:47]  System: The world is saving, please wait.
[10/14/2025 06:47]  System: World save complete. The entire process took 3.5 seconds.
[10/14/2025 06:47]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:47]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:47]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:47]  an earth dragon: an earth dragon
[10/14/2025 06:47]  You see: an earth dragon corpse
[10/14/2025 06:47]  You see: an ophidian dragonguard corpse
[10/14/2025 06:47]  You see: an ophidian dragonguard corpse
[10/14/2025 06:47]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:47]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:47]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:47]  an earth dragon: an earth dragon
[10/14/2025 06:47]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:47]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:47]  System: VonDarkmane has registered an antiquity: Ancient Dragon Figurine
[10/14/2025 06:47]  System: VonDarkmane has completed the achievement: Dungeon Antiquities (Advanced).
[10/14/2025 06:47]  System: Lysbithe has completed the achievement: Tinker (Epic).
[10/14/2025 06:47]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:47]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:47]  System: You have 23 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 23
[10/14/2025 06:47]  System: You have 22 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 22
[10/14/2025 06:47]  System: You have 21 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 21
[10/14/2025 06:47]  System: You have 20 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 20
[10/14/2025 06:47]  System: You have 19 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 19
[10/14/2025 06:47]  [Razor]: New target set.
[10/14/2025 06:47]  Razor: Target: an earth dragon
[10/14/2025 06:47]  System: You have 18 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 18
[10/14/2025 06:47]  System: You have 17 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 17
[10/14/2025 06:47]  System: You have 16 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 16
[10/14/2025 06:47]  System: You have 15 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 15
[10/14/2025 06:47]  System: You have 14 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 14
[10/14/2025 06:47]  System: You have 13 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 13
[10/14/2025 06:47]  System: You have 12 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 12
[10/14/2025 06:47]  System: You have 11 stealth steps remaining.
[10/14/2025 06:47]  [Razor]: Steps: 11
[10/14/2025 06:47]  Razor: Attack: an earth dragon
[10/14/2025 06:47]  System: You have 10 stealth steps remaining.
[10/14/2025 06:47]  Razor: [10 Steps...]
[10/14/2025 06:47]  System: You strike your target from the shadows!
[10/14/2025 06:47]  GawdHammer: You steal 363 gold coin.
[10/14/2025 06:47]  System: You steal 363 gold coin.
[10/14/2025 06:47]  GawdHammer: (Shadow Aspect 64,937.3/250000 xp)
[10/14/2025 06:47]  System: You have lost a little karma.
[10/14/2025 06:47]  an earth dragon: -8106
[10/14/2025 06:47]  You see: an earth dragon corpse
[10/14/2025 06:47]  You see: an ophidian dragonguard corpse
[10/14/2025 06:47]  You see: an earth dragon corpse
[10/14/2025 06:47]  You see: an ophidian dragonguard corpse
[10/14/2025 06:47]  You see: an earth dragon corpse
[10/14/2025 06:48]  Razor: [Last Target]
[10/14/2025 06:48]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:48]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:48]  an earth dragon: an earth dragon
[10/14/2025 06:48]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:48]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:48]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:48]  System: You have 23 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 23
[10/14/2025 06:48]  System: You have 22 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 22
[10/14/2025 06:48]  System: You have 21 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 21
[10/14/2025 06:48]  System: You have 20 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 20
[10/14/2025 06:48]  You see: an ophidian dragonguard corpse
[10/14/2025 06:48]  System: You have 19 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 19
[10/14/2025 06:48]  System: You have 18 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 18
[10/14/2025 06:48]  System: You have 17 stealth steps remaining.
[10/14/2025 06:48]  System: You strike your target from the shadows!
[10/14/2025 06:48]  System: You have already stolen from this creature.
[10/14/2025 06:48]  System: You have gained a very large amount of karma.
[10/14/2025 06:48]  GawdHammer: (Shadow Aspect 64,966.1/250000 xp)
[10/14/2025 06:48]  System: -1894
[10/14/2025 06:48]  You see: an earth dragon corpse
[10/14/2025 06:48]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:48]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:48]  System: That is too far away.
[10/14/2025 06:48]  System: You may now use another smoke bomb.
[10/14/2025 06:48]  You see: an earth dragon corpse
[10/14/2025 06:48]  System: That is too far away.
[10/14/2025 06:48]  a dragon: a dragon
[10/14/2025 06:48]  a dragon: a dragon
[10/14/2025 06:48]  a dragon: a dragon
[10/14/2025 06:48]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:48]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:48]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:48]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:48]  System: You have 23 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 23
[10/14/2025 06:48]  System: You have 22 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 22
[10/14/2025 06:48]  System: You have 21 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 21
[10/14/2025 06:48]  System: You have 20 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 20
[10/14/2025 06:48]  System: You have 19 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 19
[10/14/2025 06:48]  You see: an earth dragon corpse
[10/14/2025 06:48]  System: You have 18 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 18
[10/14/2025 06:48]  System: You have 17 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 17
[10/14/2025 06:48]  System: You have 16 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 16
[10/14/2025 06:48]  System: You have 15 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 15
[10/14/2025 06:48]  System: You have 14 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 14
[10/14/2025 06:48]  System: You have 13 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 13
[10/14/2025 06:48]  System: You have 12 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 12
[10/14/2025 06:48]  [Razor]: New target set.
[10/14/2025 06:48]  Razor: Target: a dragon
[10/14/2025 06:48]  Razor: Attack: a dragon
[10/14/2025 06:48]  System: You will automatically stealth when out of steps.
[10/14/2025 06:48]  Razor: [Auto-stealth ready.]
[10/14/2025 06:48]  System: You have 11 stealth steps remaining.
[10/14/2025 06:48]  [Razor]: Steps: 11
[10/14/2025 06:48]  System: You have 10 stealth steps remaining.
[10/14/2025 06:48]  Razor: [10 Steps...]
[10/14/2025 06:48]  [Razor]: Steps: 10
[10/14/2025 06:48]  System: You have 9 stealth steps remaining.
[10/14/2025 06:48]  Razor: [9 Steps...]
[10/14/2025 06:48]  [Razor]: Steps: 9
[10/14/2025 06:48]  System: You have 8 stealth steps remaining.
[10/14/2025 06:48]  Razor: [8 Steps...]
[10/14/2025 06:48]  [Razor]: Steps: 8
[10/14/2025 06:48]  System: You have 7 stealth steps remaining.
[10/14/2025 06:48]  Razor: [7 Steps...]
[10/14/2025 06:48]  System: You strike your target from the shadows!
[10/14/2025 06:48]  GawdHammer: You steal 303 gold coin.
[10/14/2025 06:48]  System: You steal 303 gold coin.
[10/14/2025 06:48]  GawdHammer: (Shadow Aspect 64,972.4/250000 xp)
[10/14/2025 06:48]  System: You have lost a little karma.
[10/14/2025 06:48]  a dragon: -7976
[10/14/2025 06:48]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:48]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:48]  System: You are no longer hindered.
[10/14/2025 06:48]  [Razor]: New target set.
[10/14/2025 06:49]  System: You have 29 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 29
[10/14/2025 06:49]  You see: an earth dragon corpse
[10/14/2025 06:49]  System: You have 28 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 28
[10/14/2025 06:49]  System: You have 27 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 27
[10/14/2025 06:49]  System: You have 26 stealth steps remaining.
[10/14/2025 06:49]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:49]  System: You will automatically stealth when out of steps.
[10/14/2025 06:49]  Razor: [Auto-stealth ready.]
[10/14/2025 06:49]  [Razor]: Steps: 26
[10/14/2025 06:49]  System: You have 25 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 25
[10/14/2025 06:49]  System: You have 24 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 24
[10/14/2025 06:49]  System: You have 23 stealth steps remaining.
[10/14/2025 06:49]  System: You strike your target from the shadows!
[10/14/2025 06:49]  System: You have already stolen from this creature.
[10/14/2025 06:49]  System: You have gained a very large amount of karma.
[10/14/2025 06:49]  GawdHammer: (Shadow Aspect 64,997.9/250000 xp)
[10/14/2025 06:49]  System: -2024
[10/14/2025 06:49]  You see: a dragon corpse
[10/14/2025 06:49]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:49]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:49]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:49]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:49]  System: You have 23 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 23
[10/14/2025 06:49]  System: You have 22 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 22
[10/14/2025 06:49]  System: You have 21 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 21
[10/14/2025 06:49]  You see: an earth dragon corpse
[10/14/2025 06:49]  System: You have 20 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 20
[10/14/2025 06:49]  [Razor]: New target set.
[10/14/2025 06:49]  Razor: Target: a dragon
[10/14/2025 06:49]  System: You have 19 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 19
[10/14/2025 06:49]  Razor: Attack: a dragon
[10/14/2025 06:49]  System: You have 18 stealth steps remaining.
[10/14/2025 06:49]  System: You strike your target from the shadows!
[10/14/2025 06:49]  System: You fail to steal from the creature.
[10/14/2025 06:49]  System: You have lost a little karma.
[10/14/2025 06:49]  a dragon: -7512
[10/14/2025 06:49]  You see: an earth dragon corpse
[10/14/2025 06:49]  You see: an earth dragon corpse
[10/14/2025 06:49]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:49]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:49]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:49]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:49]  System: You have 23 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 23
[10/14/2025 06:49]  System: You have 22 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 22
[10/14/2025 06:49]  System: You have 21 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 21
[10/14/2025 06:49]  System: You have 20 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 20
[10/14/2025 06:49]  You see: a dragon corpse
[10/14/2025 06:49]  System: You have 19 stealth steps remaining.
[10/14/2025 06:49]  [Razor]: Steps: 19
[10/14/2025 06:49]  System: You strike your target from the shadows!
[10/14/2025 06:49]  System: You fail to steal from the creature.
[10/14/2025 06:49]  System: You have lost a little karma.
[10/14/2025 06:49]  System: You have gained a very large amount of karma.
[10/14/2025 06:49]  GawdHammer: (Shadow Aspect 65,023.3/250000 xp)
[10/14/2025 06:49]  System: -2488
[10/14/2025 06:49]  You see: a dragon corpse
[10/14/2025 06:49]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:49]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:49]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:49]  You see: an earth dragon corpse
[10/14/2025 06:49]  a dragon: a dragon
[10/14/2025 06:49]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:49]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:49]  You see: an earth dragon corpse
[10/14/2025 06:49]  You see: an earth dragon corpse
[10/14/2025 06:49]  System: You may now use another smoke bomb.
[10/14/2025 06:49]  an earth dragon: an earth dragon
[10/14/2025 06:49]  You see: an ophidian dragonguard corpse
[10/14/2025 06:49]  an earth dragon: an earth dragon
[10/14/2025 06:49]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:49]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:49]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:50]  [Razor]: New target set.
[10/14/2025 06:50]  Razor: Target: an ophidian shaman
[10/14/2025 06:50]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:50]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:50]  System: You have 23 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 23
[10/14/2025 06:50]  System: You have 22 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 22
[10/14/2025 06:50]  System: You have 21 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 21
[10/14/2025 06:50]  System: You have 20 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 20
[10/14/2025 06:50]  Razor: Attack: an ophidian shaman
[10/14/2025 06:50]  System: You have 19 stealth steps remaining.
[10/14/2025 06:50]  System: You strike your target from the shadows!
[10/14/2025 06:50]  GawdHammer: You steal 623 gold coin.
[10/14/2025 06:50]  System: You steal 623 gold coin.
[10/14/2025 06:50]  GawdHammer: (Shadow Aspect 65,029.2/250000 xp)
[10/14/2025 06:50]  System: You have lost a little karma.
[10/14/2025 06:50]  System: You have gained a very large amount of karma.
[10/14/2025 06:50]  GawdHammer: (Shadow Aspect 65,053.0/250000 xp)
[10/14/2025 06:50]  System: -7000
[10/14/2025 06:50]  You see: an ophidian shaman corpse
[10/14/2025 06:50]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:50]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:50]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:50]  You see: an ophidian dragonguard corpse
[10/14/2025 06:50]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:50]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:50]  System: You must wait a few moments to use another skill.
[10/14/2025 06:50]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:50]  a wyvern: a wyvern
[10/14/2025 06:50]  System: You must wait a few moments to use another skill.
[10/14/2025 06:50]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:50]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:50]  System: You must wait a few moments to use another skill.
[10/14/2025 06:50]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:50]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:50]  System: You have 23 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 23
[10/14/2025 06:50]  System: You have 22 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 22
[10/14/2025 06:50]  [Razor]: New target set.
[10/14/2025 06:50]  Razor: Target: an ophidian dragonguard
[10/14/2025 06:50]  System: You have 21 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 21
[10/14/2025 06:50]  System: You have 20 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 20
[10/14/2025 06:50]  System: You have 19 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 19
[10/14/2025 06:50]  Razor: Attack: an ophidian dragonguard
[10/14/2025 06:50]  System: You have 18 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 18
[10/14/2025 06:50]  System: You have 17 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 17
[10/14/2025 06:50]  System: You have 16 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 16
[10/14/2025 06:50]  System: You have 15 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 15
[10/14/2025 06:50]  System: You have 14 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 14
[10/14/2025 06:50]  System: You have 13 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 13
[10/14/2025 06:50]  System: JZPaPa has completed the achievement: Elemental Slayer (Basic).
[10/14/2025 06:50]  System: You have 12 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 12
[10/14/2025 06:50]  System: You have 11 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 11
[10/14/2025 06:50]  a wyvern: a wyvern
[10/14/2025 06:50]  System: You have 10 stealth steps remaining.
[10/14/2025 06:50]  Razor: [10 Steps...]
[10/14/2025 06:50]  [Razor]: Steps: 10
[10/14/2025 06:50]  System: You have 9 stealth steps remaining.
[10/14/2025 06:50]  Razor: [9 Steps...]
[10/14/2025 06:50]  [Razor]: Steps: 9
[10/14/2025 06:50]  System: You have 8 stealth steps remaining.
[10/14/2025 06:50]  Razor: [8 Steps...]
[10/14/2025 06:50]  [Razor]: Steps: 8
[10/14/2025 06:50]  System: You have 7 stealth steps remaining.
[10/14/2025 06:50]  Razor: [7 Steps...]
[10/14/2025 06:50]  System: You strike your target from the shadows!
[10/14/2025 06:50]  GawdHammer: You steal 297 gold coin.
[10/14/2025 06:50]  System: You steal 297 gold coin.
[10/14/2025 06:50]  GawdHammer: (Shadow Aspect 65,058.6/250000 xp)
[10/14/2025 06:50]  System: You have lost a little karma.
[10/14/2025 06:50]  System: You have gained a very large amount of karma.
[10/14/2025 06:50]  GawdHammer: (Shadow Aspect 65,081.0/250000 xp)
[10/14/2025 06:50]  System: -8000
[10/14/2025 06:50]  You see: an ophidian dragonguard corpse
[10/14/2025 06:50]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:50]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:50]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:50]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:50]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:50]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:50]  an earth dragon: an earth dragon
[10/14/2025 06:50]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:50]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:50]  System: You have 23 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 23
[10/14/2025 06:50]  System: You have 22 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 22
[10/14/2025 06:50]  System: You have 21 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 21
[10/14/2025 06:50]  System: You have 20 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: New target set.
[10/14/2025 06:50]  Razor: Target: an ophidian dragonguard
[10/14/2025 06:50]  [Razor]: Steps: 20
[10/14/2025 06:50]  System: You have 19 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 19
[10/14/2025 06:50]  You see: an earth dragon corpse
[10/14/2025 06:50]  System: You have 18 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 18
[10/14/2025 06:50]  System: You have 17 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 17
[10/14/2025 06:50]  System: You have 16 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 16
[10/14/2025 06:50]  System: You have 15 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 15
[10/14/2025 06:50]  Razor: Attack: an ophidian dragonguard
[10/14/2025 06:50]  System: You have 14 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 14
[10/14/2025 06:50]  You see: an ophidian shaman corpse
[10/14/2025 06:50]  You see: an earth dragon corpse
[10/14/2025 06:50]  System: You have 13 stealth steps remaining.
[10/14/2025 06:50]  [Razor]: Steps: 13
[10/14/2025 06:50]  System: You have 12 stealth steps remaining.
[10/14/2025 06:50]  System: You strike your target from the shadows!
[10/14/2025 06:50]  System: You fail to steal from the creature.
[10/14/2025 06:50]  System: You have lost a little karma.
[10/14/2025 06:50]  System: You have gained a very large amount of karma.
[10/14/2025 06:50]  GawdHammer: (Shadow Aspect 65,103.3/250000 xp)
[10/14/2025 06:50]  System: -8000
[10/14/2025 06:50]  You see: an ophidian dragonguard corpse
[10/14/2025 06:50]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:51]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:51]  an earth dragon: an earth dragon
[10/14/2025 06:51]  You see: an earth dragon corpse
[10/14/2025 06:51]  a wyvern: a wyvern
[10/14/2025 06:51]  a harvestman: a harvestman
[10/14/2025 06:51]  a cave dracolisk: a cave dracolisk
[10/14/2025 06:51]  a dusk dragon: a dusk dragon
[10/14/2025 06:51]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:51]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:51]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:51]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:51]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:51]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:51]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:51]  System: You have 23 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 23
[10/14/2025 06:51]  System: You have 22 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 22
[10/14/2025 06:51]  System: You have 21 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 21
[10/14/2025 06:51]  a bonfire wisp: a bonfire wisp
[10/14/2025 06:51]  System: You have 20 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: New target set.
[10/14/2025 06:51]  Razor: Target: an ophidian shaman
[10/14/2025 06:51]  [Razor]: Steps: 20
[10/14/2025 06:51]  System: You have 19 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 19
[10/14/2025 06:51]  System: You have 18 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 18
[10/14/2025 06:51]  System: You have 17 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 17
[10/14/2025 06:51]  a wyvern: a wyvern
[10/14/2025 06:51]  System: You have 16 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 16
[10/14/2025 06:51]  Razor: Attack: an ophidian shaman
[10/14/2025 06:51]  System: You strike your target from the shadows!
[10/14/2025 06:51]  GawdHammer: You steal 267 gold coin.
[10/14/2025 06:51]  System: You steal 267 gold coin.
[10/14/2025 06:51]  GawdHammer: (Shadow Aspect 65,109.3/250000 xp)
[10/14/2025 06:51]  System: You have lost a little karma.
[10/14/2025 06:51]  System: You have gained a very large amount of karma.
[10/14/2025 06:51]  GawdHammer: (Shadow Aspect 65,133.0/250000 xp)
[10/14/2025 06:51]  System: -7000
[10/14/2025 06:51]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:51]  You see: an ophidian shaman corpse
[10/14/2025 06:51]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:51]  System: deSiew has completed the achievement: Tinker (Epic).
[10/14/2025 06:51]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:51]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:51]  System: You have 23 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 23
[10/14/2025 06:51]  a wyvern: a wyvern
[10/14/2025 06:51]  a wyvern: a wyvern
[10/14/2025 06:51]  System: You have 22 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 22
[10/14/2025 06:51]  System: You have 21 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 21
[10/14/2025 06:51]  System: You have 20 stealth steps remaining.
[10/14/2025 06:51]  [Razor]: Steps: 20
[10/14/2025 06:52]  System: You have 19 stealth steps remaining.
[10/14/2025 06:52]  [Razor]: Steps: 19
[10/14/2025 06:52]  [Razor]: New target set.
[10/14/2025 06:52]  Razor: Target: an ophidian dragonguard
[10/14/2025 06:52]  System: You have 18 stealth steps remaining.
[10/14/2025 06:52]  [Razor]: Steps: 18
[10/14/2025 06:52]  a wyvern: a wyvern
[10/14/2025 06:52]  Razor: Attack: an ophidian dragonguard
[10/14/2025 06:52]  System: You strike your target from the shadows!
[10/14/2025 06:52]  System: Society Job Progress: 1/10 [Steal 10 Black Goods].
[10/14/2025 06:52]  GawdHammer: You steal very common black goods.
[10/14/2025 06:52]  System: You steal very common black goods.
[10/14/2025 06:52]  GawdHammer: You steal 262 gold coin.
[10/14/2025 06:52]  System: You steal 262 gold coin.
[10/14/2025 06:52]  GawdHammer: (Shadow Aspect 65,138.6/250000 xp)
[10/14/2025 06:52]  System: You have lost a little karma.
[10/14/2025 06:52]  an ophidian dragonguard: -6977
[10/14/2025 06:52]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:52]  GawdHammer: You can't seem to hide right now.
[10/14/2025 06:52]  GawdHammer: -12
[10/14/2025 06:52]  GawdHammer: * You feel extremely weak and are in severe pain! *
[10/14/2025 06:52]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:52]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:52]  [Razor]: Steps: 0
[10/14/2025 06:52]  [Razor]: Steps: 0
[10/14/2025 06:52]  System: You are no longer hindered.
[10/14/2025 06:52]  System: You have 29 stealth steps remaining.
[10/14/2025 06:52]  GawdHammer: +2
[10/14/2025 06:52]  System: some damage has been healed : 2
[10/14/2025 06:52]  [Razor]: Steps: 29
[10/14/2025 06:52]  System: You have 28 stealth steps remaining.
[10/14/2025 06:52]  GawdHammer: +2
[10/14/2025 06:52]  System: some damage has been healed : 2
[10/14/2025 06:52]  [Razor]: Steps: 28
[10/14/2025 06:52]  [Razor]: New target set.
[10/14/2025 06:52]  System: You have 27 stealth steps remaining.
[10/14/2025 06:52]  GawdHammer: +2
[10/14/2025 06:52]  System: some damage has been healed : 2
[10/14/2025 06:52]  [Razor]: Steps: 27
[10/14/2025 06:52]  System: You have 26 stealth steps remaining.
[10/14/2025 06:52]  GawdHammer: +2
[10/14/2025 06:52]  System: some damage has been healed : 2
[10/14/2025 06:52]  [Razor]: Steps: 26
[10/14/2025 06:52]  System: You have 25 stealth steps remaining.
[10/14/2025 06:52]  [Razor]: Steps: 25
[10/14/2025 06:52]  a wyvern: a wyvern
[10/14/2025 06:52]  GawdHammer: * You feel extremely weak and are in severe pain! *
[10/14/2025 06:52]  GawdHammer: -9
[10/14/2025 06:52]  System: You strike your target from the shadows!
[10/14/2025 06:52]  System: You have already stolen from this creature.
[10/14/2025 06:52]  System: You have gained a very large amount of karma.
[10/14/2025 06:52]  GawdHammer: (Shadow Aspect 65,161.0/250000 xp)
[10/14/2025 06:52]  System: -1023
[10/14/2025 06:52]  You see: an ophidian dragonguard corpse
[10/14/2025 06:52]  System: You feel cured of poison!
[10/14/2025 06:52]  System: You receive a free cure potion consumption (next available in 30 seconds).
[10/14/2025 06:52]  GawdHammer: +11
[10/14/2025 06:52]  System: some damage has been healed : 11
[10/14/2025 06:52]  System: You drink a healing potion.
[10/14/2025 06:52]  Razor: You fail to hide.
[10/14/2025 06:52]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:52]  GawdHammer: You can't seem to hide right now.
[10/14/2025 06:52]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:52]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:52]  [Razor]: New target set.
[10/14/2025 06:52]  Razor: Target: an ophidian dragonguard
[10/14/2025 06:52]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:52]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:52]  System: You have 23 stealth steps remaining.
[10/14/2025 06:52]  [Razor]: Steps: 23
[10/14/2025 06:52]  Razor: Attack: an ophidian dragonguard
[10/14/2025 06:52]  System: You have 22 stealth steps remaining.
[10/14/2025 06:52]  [Razor]: Steps: 22
[10/14/2025 06:52]  You see: an ophidian shaman corpse
[10/14/2025 06:52]  System: You have 21 stealth steps remaining.
[10/14/2025 06:52]  System: You strike your target from the shadows!
[10/14/2025 06:52]  System: Society Job Progress: 2/10 [Steal 10 Black Goods].
[10/14/2025 06:52]  GawdHammer: You steal 263 gold coin.
[10/14/2025 06:52]  System: You steal 263 gold coin.
[10/14/2025 06:52]  GawdHammer: You steal common black goods.
[10/14/2025 06:52]  System: You steal common black goods.
[10/14/2025 06:52]  GawdHammer: (Shadow Aspect 65,166.6/250000 xp)
[10/14/2025 06:52]  System: You have lost a little karma.
[10/14/2025 06:52]  System: You have gained a very large amount of karma.
[10/14/2025 06:52]  GawdHammer: (Shadow Aspect 65,188.9/250000 xp)
[10/14/2025 06:52]  System: -8000
[10/14/2025 06:52]  You see: an ophidian dragonguard corpse
[10/14/2025 06:52]  System: VonDarkmane has registered an antiquity: Pile of Lumber
[10/14/2025 06:52]  System: VonDarkmane has completed the achievement: Crafting Antiquities (Basic).
[10/14/2025 06:52]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:52]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:52]  You see: an ophidian dragonguard corpse
[10/14/2025 06:52]  a bonfire wisp: a bonfire wisp
[10/14/2025 06:52]  a wyvern: a wyvern
[10/14/2025 06:52]  a wyvern: a wyvern
[10/14/2025 06:52]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:52]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:52]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 06:52]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:52]  a wyvern: a wyvern
[10/14/2025 06:52]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:52]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:52]  GawdHammer: -39
[10/14/2025 06:52]  a wyvern: a wyvern
[10/14/2025 06:52]  GawdHammer: +28
[10/14/2025 06:52]  System: some damage has been healed : 28
[10/14/2025 06:52]  System: You drink a healing potion.
[10/14/2025 06:52]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:52]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:52]  [Razor]: New target set.
[10/14/2025 06:52]  Razor: Target: a bonfire wisp
[10/14/2025 06:53]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:53]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:53]  System: You have 23 stealth steps remaining.
[10/14/2025 06:53]  GawdHammer: +5
[10/14/2025 06:53]  System: some damage has been healed : 5
[10/14/2025 06:53]  [Razor]: Steps: 23
[10/14/2025 06:53]  System: You have 22 stealth steps remaining.
[10/14/2025 06:53]  GawdHammer: +3
[10/14/2025 06:53]  System: some damage has been healed : 3
[10/14/2025 06:53]  [Razor]: Steps: 22
[10/14/2025 06:53]  System: You have 21 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 21
[10/14/2025 06:53]  System: You have 20 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 20
[10/14/2025 06:53]  System: You have 19 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 19
[10/14/2025 06:53]  System: You have 18 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 18
[10/14/2025 06:53]  System: You may now use another smoke bomb.
[10/14/2025 06:53]  System: You have 17 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 17
[10/14/2025 06:53]  System: You have 16 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 16
[10/14/2025 06:53]  a wyvern: a wyvern
[10/14/2025 06:53]  System: You have 15 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 15
[10/14/2025 06:53]  Razor: Attack: a bonfire wisp
[10/14/2025 06:53]  System: You will automatically stealth when out of steps.
[10/14/2025 06:53]  Razor: [Auto-stealth ready.]
[10/14/2025 06:53]  System: You have 14 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 14
[10/14/2025 06:53]  System: You have 13 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 13
[10/14/2025 06:53]  System: You have 12 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 12
[10/14/2025 06:53]  System: You have 11 stealth steps remaining.
[10/14/2025 06:53]  [Razor]: Steps: 11
[10/14/2025 06:53]  System: You have 10 stealth steps remaining.
[10/14/2025 06:53]  Razor: [10 Steps...]
[10/14/2025 06:53]  [Razor]: Steps: 10
[10/14/2025 06:53]  System: You have 9 stealth steps remaining.
[10/14/2025 06:53]  Razor: [9 Steps...]
[10/14/2025 06:53]  [Razor]: Steps: 9
[10/14/2025 06:53]  System: You have 8 stealth steps remaining.
[10/14/2025 06:53]  Razor: [8 Steps...]
[10/14/2025 06:53]  [Razor]: Steps: 8
[10/14/2025 06:53]  System: You have 7 stealth steps remaining.
[10/14/2025 06:53]  GawdHammer: -2
[10/14/2025 06:53]  Razor: [7 Steps...]
[10/14/2025 06:53]  [Razor]: Steps: 7
[10/14/2025 06:53]  System: You have 6 stealth steps remaining.
[10/14/2025 06:53]  Razor: [6 Steps...]
[10/14/2025 06:53]  [Razor]: Steps: 6
[10/14/2025 06:53]  System: You have 5 stealth steps remaining.
[10/14/2025 06:53]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:53]  [Razor]: Steps: 5
[10/14/2025 06:53]  System: You have 4 stealth steps remaining.
[10/14/2025 06:53]  GawdHammer: +2
[10/14/2025 06:53]  System: some damage has been healed : 2
[10/14/2025 06:53]  Razor: [4 Steps...]
[10/14/2025 06:53]  [Razor]: Steps: 4
[10/14/2025 06:53]  System: You have 3 stealth steps remaining.
[10/14/2025 06:53]  Razor: [3 Steps...]
[10/14/2025 06:53]  System: You strike your target from the shadows!
[10/14/2025 06:53]  GawdHammer: You steal 364 gold coin.
[10/14/2025 06:53]  System: You steal 364 gold coin.
[10/14/2025 06:53]  GawdHammer: (Shadow Aspect 65,195.8/250000 xp)
[10/14/2025 06:53]  System: You have lost a little karma.
[10/14/2025 06:53]  GawdHammer: -13
[10/14/2025 06:53]  System: You have gained a very large amount of karma.
[10/14/2025 06:53]  GawdHammer: (Shadow Aspect 65,223.5/250000 xp)
[10/14/2025 06:53]  System: -6000
[10/14/2025 06:53]  You see: a bonfire wisp corpse
[10/14/2025 06:53]  GawdHammer: -3
[10/14/2025 06:53]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:53]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:53]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 06:53]  [Razor]: Queuing dragdrop request 2... 1.3 seconds left.
[10/14/2025 06:53]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:53]  a wyvern: a wyvern
[10/14/2025 06:53]  a custodian: *searches*
[10/14/2025 06:53]  a custodian: a custodian
[10/14/2025 06:53]  You see: a bonfire wisp corpse
[10/14/2025 06:53]  You see: an ophidian shaman corpse
[10/14/2025 06:53]  You see: an ophidian dragonguard corpse
[10/14/2025 06:53]  You see: an ophidian dragonguard corpse
[10/14/2025 06:53]  GawdHammer: +12
[10/14/2025 06:53]  System: some damage has been healed : 12
[10/14/2025 06:53]  System: You drink a healing potion.
[10/14/2025 06:53]  a stone adder: a stone adder
[10/14/2025 06:53]  a harvestman: a harvestman
[10/14/2025 06:53]  a cave dracolisk: a cave dracolisk
[10/14/2025 06:53]  You see: the remains of Rabbit-GamerYT
[10/14/2025 06:53]  You see: an earth dragon corpse
[10/14/2025 06:53]  You see: a dragon corpse
[10/14/2025 06:53]  GrannyFlaps: Aegis Keep Guardian
[10/14/2025 06:53]  GrannyFlaps: [Au]
[10/14/2025 06:53]  GrannyFlaps: Lady GrannyFlaps
[10/14/2025 06:53]  a dragon: [Lethal Poison]
[10/14/2025 06:53]  a dragon: a dragon
[10/14/2025 06:53]  Rabbit-GamerYT: [Siblings of the Dust, EMBR]
[10/14/2025 06:53]  Rabbit-GamerYT: Rabbit-GamerYT
[10/14/2025 06:53]  Boy'z: Domesticator
[10/14/2025 06:53]  Boy'z: Lady Boy'z
[10/14/2025 06:53]  Greater Explosion potion: 3
[10/14/2025 06:53]  You see: a dragon corpse
[10/14/2025 06:53]  Macer Windu: Legendary Boss Slayer
[10/14/2025 06:53]  Macer Windu: [Officer, Path]
[10/14/2025 06:53]  Macer Windu: Macer Windu
[10/14/2025 06:53]  Greater Explosion potion: 2
[10/14/2025 06:53]  You see: a dragon corpse
[10/14/2025 06:53]  You see: a dragon corpse
[10/14/2025 06:53]  You see: a dragon corpse
[10/14/2025 06:53]  Greater Explosion potion: 1
[10/14/2025 06:53]  You see: an ophidian shaman corpse
[10/14/2025 06:53]  You see: an ophidian shaman corpse
[10/14/2025 06:53]  You see: an earth dragon corpse
[10/14/2025 06:53]  You see: an earth dragon corpse
[10/14/2025 06:53]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  You see: an ophidian dragonguard corpse
[10/14/2025 06:54]  You see: an ophidian dragonguard corpse
[10/14/2025 06:54]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:54]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:54]  [Razor]: Steps: 0
[10/14/2025 06:54]  [Razor]: Steps: 0
[10/14/2025 06:54]  System: You are no longer hindered.
[10/14/2025 06:54]  System: You have 29 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 29
[10/14/2025 06:54]  System: You have 28 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 28
[10/14/2025 06:54]  System: You have 27 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 27
[10/14/2025 06:54]  System: You have 26 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 26
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  System: You have 25 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 25
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  System: You have 24 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 24
[10/14/2025 06:54]  System: You have 23 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: New target set.
[10/14/2025 06:54]  Razor: Target: an ophidian dragonguard
[10/14/2025 06:54]  [Razor]: Steps: 23
[10/14/2025 06:54]  System: You have 22 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 22
[10/14/2025 06:54]  System: You have 21 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 21
[10/14/2025 06:54]  Razor: Attack: an ophidian dragonguard
[10/14/2025 06:54]  System: You have 20 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 20
[10/14/2025 06:54]  System: You have 19 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 19
[10/14/2025 06:54]  System: You have 18 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 18
[10/14/2025 06:54]  System: You have 17 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 17
[10/14/2025 06:54]  System: You have 16 stealth steps remaining.
[10/14/2025 06:54]  System: You strike your target from the shadows!
[10/14/2025 06:54]  System: You fail to steal from the creature.
[10/14/2025 06:54]  System: You have lost a little karma.
[10/14/2025 06:54]  an ophidian dragonguard: -7203
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:54]  GawdHammer: You can't seem to hide right now.
[10/14/2025 06:54]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:54]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:54]  Rabbit-GamerYT: [Siblings of the Dust, EMBR]
[10/14/2025 06:54]  Rabbit-GamerYT: Rabbit-GamerYT
[10/14/2025 06:54]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:54]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:54]  System: You have 23 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 23
[10/14/2025 06:54]  [Razor]: Steps: 23
[10/14/2025 06:54]  Razor: [Last Target]
[10/14/2025 06:54]  You see: an ophidian dragonguard corpse
[10/14/2025 06:54]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:54]  System: You have 22 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 22
[10/14/2025 06:54]  [Razor]: New target set.
[10/14/2025 06:54]  System: You have 21 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 21
[10/14/2025 06:54]  System: You have 20 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 20
[10/14/2025 06:54]  System: You have 19 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 19
[10/14/2025 06:54]  [Razor]: New target set.
[10/14/2025 06:54]  System: You have 18 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 18
[10/14/2025 06:54]  System: You have 17 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 17
[10/14/2025 06:54]  You see: an ophidian dragonguard corpse
[10/14/2025 06:54]  System: You have 16 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 16
[10/14/2025 06:54]  System: You have 15 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 15
[10/14/2025 06:54]  System: You have 14 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 14
[10/14/2025 06:54]  System: You have 13 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 13
[10/14/2025 06:54]  an earth dragon: an earth dragon
[10/14/2025 06:54]  System: You have 12 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 12
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  System: You have 11 stealth steps remaining.
[10/14/2025 06:54]  [Razor]: Steps: 11
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  System: You have 10 stealth steps remaining.
[10/14/2025 06:54]  Razor: [10 Steps...]
[10/14/2025 06:54]  System: You strike your target from the shadows!
[10/14/2025 06:54]  GawdHammer: You steal 253 gold coin.
[10/14/2025 06:54]  System: You steal 253 gold coin.
[10/14/2025 06:54]  GawdHammer: (Shadow Aspect 65,229.0/250000 xp)
[10/14/2025 06:54]  System: You have lost a little karma.
[10/14/2025 06:54]  System: You have gained a very large amount of karma.
[10/14/2025 06:54]  GawdHammer: (Shadow Aspect 65,251.4/250000 xp)
[10/14/2025 06:54]  System: -797
[10/14/2025 06:54]  You see: an ophidian dragonguard corpse
[10/14/2025 06:54]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:54]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:54]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:54]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:54]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 06:54]  You see: an ophidian shaman corpse
[10/14/2025 06:54]  You see: an ophidian shaman corpse
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  You see: an earth dragon corpse
[10/14/2025 06:54]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:55]  You see: an earth dragon corpse
[10/14/2025 06:55]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:55]  System: [SR] A Stygian Rifts event will begin in 5 minutes. Type [BattleHotbar to view the Battle Hotbar for this event.
[10/14/2025 06:55]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:55]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:55]  System: You may now use another smoke bomb.
[10/14/2025 06:55]  System: You will automatically stealth when out of steps.
[10/14/2025 06:55]  Razor: [Auto-stealth ready.]
[10/14/2025 06:55]  System: You have 23 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 23
[10/14/2025 06:55]  You see: a dragon corpse
[10/14/2025 06:55]  You see: a dragon corpse
[10/14/2025 06:55]  System: You have 22 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 22
[10/14/2025 06:55]  System: You have 21 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 21
[10/14/2025 06:55]  System: You have 20 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 20
[10/14/2025 06:55]  System: You have 19 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 19
[10/14/2025 06:55]  System: You have 18 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 18
[10/14/2025 06:55]  System: You have 17 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 17
[10/14/2025 06:55]  You see: an earth dragon corpse
[10/14/2025 06:55]  You see: an earth dragon corpse
[10/14/2025 06:55]  System: You have 16 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 16
[10/14/2025 06:55]  You see: an earth dragon corpse
[10/14/2025 06:55]  System: You have 15 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 15
[10/14/2025 06:55]  System: You have 14 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 14
[10/14/2025 06:55]  an earth dragon: an earth dragon
[10/14/2025 06:55]  System: You have 13 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 13
[10/14/2025 06:55]  System: You have 12 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 12
[10/14/2025 06:55]  System: You have 11 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 11
[10/14/2025 06:55]  System: You have 10 stealth steps remaining.
[10/14/2025 06:55]  Razor: [10 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 10
[10/14/2025 06:55]  System: You have 9 stealth steps remaining.
[10/14/2025 06:55]  Razor: [9 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 9
[10/14/2025 06:55]  System: You have 8 stealth steps remaining.
[10/14/2025 06:55]  Razor: [8 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 8
[10/14/2025 06:55]  System: You have 7 stealth steps remaining.
[10/14/2025 06:55]  Razor: [7 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 7
[10/14/2025 06:55]  System: You have 6 stealth steps remaining.
[10/14/2025 06:55]  Razor: [6 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 6
[10/14/2025 06:55]  You see: a dragon corpse
[10/14/2025 06:55]  System: You have 5 stealth steps remaining.
[10/14/2025 06:55]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:55]  [Razor]: Steps: 5
[10/14/2025 06:55]  System: You have 4 stealth steps remaining.
[10/14/2025 06:55]  Razor: [4 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 4
[10/14/2025 06:55]  System: You have 3 stealth steps remaining.
[10/14/2025 06:55]  Razor: [3 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 3
[10/14/2025 06:55]  You see: the remains of Rabbit-GamerYT
[10/14/2025 06:55]  You see: a dragon corpse
[10/14/2025 06:55]  System: You have 2 stealth steps remaining.
[10/14/2025 06:55]  Razor: [2 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 2
[10/14/2025 06:55]  System: You have 1 stealth steps remaining.
[10/14/2025 06:55]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:55]  [Razor]: Steps: 1
[10/14/2025 06:55]  System: You have 0 stealth steps remaining.
[10/14/2025 06:55]  System: You feel ready to continue stealthing.
[10/14/2025 06:55]  Razor: [Stop!]
[10/14/2025 06:55]  [Razor]: Steps: 0
[10/14/2025 06:55]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:55]  System: You have 23 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 23
[10/14/2025 06:55]  System: You have 22 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 22
[10/14/2025 06:55]  System: You have 21 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 21
[10/14/2025 06:55]  System: You have 20 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 20
[10/14/2025 06:55]  System: You have 19 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 19
[10/14/2025 06:55]  System: You have 18 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 18
[10/14/2025 06:55]  System: You have 17 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 17
[10/14/2025 06:55]  System: You have 16 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 16
[10/14/2025 06:55]  System: You have 15 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 15
[10/14/2025 06:55]  System: You have 14 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 14
[10/14/2025 06:55]  System: You have 13 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 13
[10/14/2025 06:55]  System: You have 12 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 12
[10/14/2025 06:55]  System: You have 11 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 11
[10/14/2025 06:55]  System: You have 10 stealth steps remaining.
[10/14/2025 06:55]  Razor: [10 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 10
[10/14/2025 06:55]  System: You have 9 stealth steps remaining.
[10/14/2025 06:55]  Razor: [9 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 9
[10/14/2025 06:55]  System: You have 8 stealth steps remaining.
[10/14/2025 06:55]  Razor: [8 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 8
[10/14/2025 06:55]  System: You have 7 stealth steps remaining.
[10/14/2025 06:55]  Razor: [7 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 7
[10/14/2025 06:55]  System: You have 6 stealth steps remaining.
[10/14/2025 06:55]  Razor: [6 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 6
[10/14/2025 06:55]  System: You have 5 stealth steps remaining.
[10/14/2025 06:55]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:55]  [Razor]: Steps: 5
[10/14/2025 06:55]  System: You have 4 stealth steps remaining.
[10/14/2025 06:55]  Razor: [4 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 4
[10/14/2025 06:55]  System: You have 3 stealth steps remaining.
[10/14/2025 06:55]  Razor: [3 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 3
[10/14/2025 06:55]  System: You have 2 stealth steps remaining.
[10/14/2025 06:55]  Razor: [2 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 2
[10/14/2025 06:55]  System: You have 1 stealth steps remaining.
[10/14/2025 06:55]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:55]  [Razor]: Steps: 1
[10/14/2025 06:55]  System: You have 0 stealth steps remaining.
[10/14/2025 06:55]  Razor: [Stop!]
[10/14/2025 06:55]  [Razor]: Steps: 0
[10/14/2025 06:55]  System: You feel ready to continue stealthing.
[10/14/2025 06:55]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:55]  System: You have 23 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 23
[10/14/2025 06:55]  System: You have 22 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 22
[10/14/2025 06:55]  System: You have 21 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 21
[10/14/2025 06:55]  System: You have 20 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 20
[10/14/2025 06:55]  System: You have 19 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 19
[10/14/2025 06:55]  System: You have 18 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 18
[10/14/2025 06:55]  System: You have 17 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 17
[10/14/2025 06:55]  System: You have 16 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 16
[10/14/2025 06:55]  System: You have 15 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 15
[10/14/2025 06:55]  System: You have 14 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 14
[10/14/2025 06:55]  System: You have 13 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 13
[10/14/2025 06:55]  System: You have 12 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 12
[10/14/2025 06:55]  System: You have 11 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 11
[10/14/2025 06:55]  System: You have 10 stealth steps remaining.
[10/14/2025 06:55]  Razor: [10 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 10
[10/14/2025 06:55]  System: You have 9 stealth steps remaining.
[10/14/2025 06:55]  Razor: [9 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 9
[10/14/2025 06:55]  System: You have 8 stealth steps remaining.
[10/14/2025 06:55]  Razor: [8 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 8
[10/14/2025 06:55]  System: You have 7 stealth steps remaining.
[10/14/2025 06:55]  Razor: [7 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 7
[10/14/2025 06:55]  System: You have 6 stealth steps remaining.
[10/14/2025 06:55]  Razor: [6 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 6
[10/14/2025 06:55]  System: You have 5 stealth steps remaining.
[10/14/2025 06:55]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:55]  [Razor]: Steps: 5
[10/14/2025 06:55]  System: You have 4 stealth steps remaining.
[10/14/2025 06:55]  Razor: [4 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 4
[10/14/2025 06:55]  System: You have 3 stealth steps remaining.
[10/14/2025 06:55]  Razor: [3 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 3
[10/14/2025 06:55]  System: You have 2 stealth steps remaining.
[10/14/2025 06:55]  Razor: [2 Steps...]
[10/14/2025 06:55]  [Razor]: Steps: 2
[10/14/2025 06:55]  System: You have 1 stealth steps remaining.
[10/14/2025 06:55]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:55]  [Razor]: Steps: 1
[10/14/2025 06:55]  System: You will automatically stealth when out of steps.
[10/14/2025 06:55]  Razor: [Auto-stealth ready.]
[10/14/2025 06:55]  System: You have 0 stealth steps remaining.
[10/14/2025 06:55]  System: You feel ready to continue stealthing.
[10/14/2025 06:55]  Razor: [Stop!]
[10/14/2025 06:55]  [Razor]: Steps: 0
[10/14/2025 06:55]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:55]  System: You have 23 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 23
[10/14/2025 06:55]  System: You have 22 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 22
[10/14/2025 06:55]  System: You have 21 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 21
[10/14/2025 06:55]  System: You have 20 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 20
[10/14/2025 06:55]  System: You have 19 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 19
[10/14/2025 06:55]  an ophidian shaman: an ophidian shaman
[10/14/2025 06:55]  System: You have 18 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 18
[10/14/2025 06:55]  System: You have 17 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 17
[10/14/2025 06:55]  System: You have 16 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 16
[10/14/2025 06:55]  System: You have 15 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 15
[10/14/2025 06:55]  System: You have 14 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 14
[10/14/2025 06:55]  a wyvern: a wyvern
[10/14/2025 06:55]  System: You have 13 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 13
[10/14/2025 06:55]  System: You have 12 stealth steps remaining.
[10/14/2025 06:55]  [Razor]: Steps: 12
[10/14/2025 06:56]  System: You have 11 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 11
[10/14/2025 06:56]  System: You have 10 stealth steps remaining.
[10/14/2025 06:56]  Razor: [10 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 10
[10/14/2025 06:56]  You see: an ophidian dragonguard corpse
[10/14/2025 06:56]  System: You have 9 stealth steps remaining.
[10/14/2025 06:56]  Razor: [9 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 9
[10/14/2025 06:56]  a wyvern: a wyvern
[10/14/2025 06:56]  System: You have 8 stealth steps remaining.
[10/14/2025 06:56]  Razor: [8 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 8
[10/14/2025 06:56]  You see: an ophidian shaman corpse
[10/14/2025 06:56]  System: You have 7 stealth steps remaining.
[10/14/2025 06:56]  Razor: [7 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 7
[10/14/2025 06:56]  System: You have 6 stealth steps remaining.
[10/14/2025 06:56]  Razor: [6 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 6
[10/14/2025 06:56]  System: You have 5 stealth steps remaining.
[10/14/2025 06:56]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 06:56]  [Razor]: Steps: 5
[10/14/2025 06:56]  System: You have 4 stealth steps remaining.
[10/14/2025 06:56]  Razor: [4 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 4
[10/14/2025 06:56]  a wyvern: a wyvern
[10/14/2025 06:56]  System: You have 3 stealth steps remaining.
[10/14/2025 06:56]  Razor: [3 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 3
[10/14/2025 06:56]  [Razor]: New target set.
[10/14/2025 06:56]  Razor: Target: a wyvern
[10/14/2025 06:56]  System: You will automatically stealth when out of steps.
[10/14/2025 06:56]  Razor: [Auto-stealth ready.]
[10/14/2025 06:56]  System: You have 2 stealth steps remaining.
[10/14/2025 06:56]  Razor: [2 Steps...]
[10/14/2025 06:56]  [Razor]: Steps: 2
[10/14/2025 06:56]  System: You have 1 stealth steps remaining.
[10/14/2025 06:56]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 06:56]  [Razor]: Steps: 1
[10/14/2025 06:56]  System: You have 0 stealth steps remaining.
[10/14/2025 06:56]  System: You feel ready to continue stealthing.
[10/14/2025 06:56]  Razor: [Stop!]
[10/14/2025 06:56]  [Razor]: Steps: 0
[10/14/2025 06:56]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:56]  System: You have 23 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 23
[10/14/2025 06:56]  System: You have 22 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 22
[10/14/2025 06:56]  System: You have 21 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 21
[10/14/2025 06:56]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:56]  Razor: Attack: a wyvern
[10/14/2025 06:56]  System: You will automatically stealth when out of steps.
[10/14/2025 06:56]  Razor: [Auto-stealth ready.]
[10/14/2025 06:56]  System: You strike your target from the shadows!
[10/14/2025 06:56]  GawdHammer: You steal 254 gold coin.
[10/14/2025 06:56]  System: You steal 254 gold coin.
[10/14/2025 06:56]  GawdHammer: (Shadow Aspect 65,256.2/250000 xp)
[10/14/2025 06:56]  System: You have lost a little karma.
[10/14/2025 06:56]  System: You have gained a lot of karma.
[10/14/2025 06:56]  GawdHammer: (Shadow Aspect 65,275.5/250000 xp)
[10/14/2025 06:56]  System: -8000
[10/14/2025 06:56]  You see: a wyvern corpse
[10/14/2025 06:56]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:56]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:56]  [Razor]: New target set.
[10/14/2025 06:56]  Razor: Target: a wyvern
[10/14/2025 06:56]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:56]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:56]  System: You have 23 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 23
[10/14/2025 06:56]  Razor: Attack: a wyvern
[10/14/2025 06:56]  System: You have 22 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 22
[10/14/2025 06:56]  System: You strike your target from the shadows!
[10/14/2025 06:56]  System: You fail to steal from the creature.
[10/14/2025 06:56]  System: You have lost a little karma.
[10/14/2025 06:56]  System: You have gained a lot of karma.
[10/14/2025 06:56]  GawdHammer: (Shadow Aspect 65,294.8/250000 xp)
[10/14/2025 06:56]  System: -8000
[10/14/2025 06:56]  You see: a wyvern corpse
[10/14/2025 06:56]  System: That is too far away.
[10/14/2025 06:56]  You see: a wyvern corpse
[10/14/2025 06:56]  You see: an ophidian shaman corpse
[10/14/2025 06:56]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:56]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:56]  [Razor]: New target set.
[10/14/2025 06:56]  Razor: Target: a wyvern
[10/14/2025 06:56]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:56]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:56]  System: You have 23 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 23
[10/14/2025 06:56]  System: You have 22 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 22
[10/14/2025 06:56]  System: You have 21 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 21
[10/14/2025 06:56]  System: You have 20 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 20
[10/14/2025 06:56]  Razor: Attack: a wyvern
[10/14/2025 06:56]  System: You have 19 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 19
[10/14/2025 06:56]  System: You have 18 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 18
[10/14/2025 06:56]  System: You have 17 stealth steps remaining.
[10/14/2025 06:56]  [Razor]: Steps: 17
[10/14/2025 06:56]  System: You have 16 stealth steps remaining.
[10/14/2025 06:56]  System: You strike your target from the shadows!
[10/14/2025 06:56]  GawdHammer: You steal 218 gold coin.
[10/14/2025 06:56]  System: You steal 218 gold coin.
[10/14/2025 06:56]  GawdHammer: (Shadow Aspect 65,299.6/250000 xp)
[10/14/2025 06:56]  System: You have lost a little karma.
[10/14/2025 06:56]  System: You have gained a lot of karma.
[10/14/2025 06:56]  GawdHammer: (Shadow Aspect 65,318.9/250000 xp)
[10/14/2025 06:56]  System: -8000
[10/14/2025 06:56]  You see: a wyvern corpse
[10/14/2025 06:56]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:56]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:56]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:56]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:57]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 06:57]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:57]  You see: an ophidian dragonguard corpse
[10/14/2025 06:57]  You see: an ophidian shaman corpse
[10/14/2025 06:57]  You see: an ophidian shaman corpse
[10/14/2025 06:57]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 06:57]  a cave dracolisk: a cave dracolisk
[10/14/2025 06:57]  a cave dracolisk: a cave dracolisk
[10/14/2025 06:57]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:57]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:57]  [Razor]: New target set.
[10/14/2025 06:57]  Razor: Target: a cave dracolisk
[10/14/2025 06:57]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:57]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:57]  System: You have 23 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 23
[10/14/2025 06:57]  System: You have 22 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 22
[10/14/2025 06:57]  System: You have 21 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 21
[10/14/2025 06:57]  System: You have 20 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 20
[10/14/2025 06:57]  System: You have 19 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 19
[10/14/2025 06:57]  System: You have 18 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 18
[10/14/2025 06:57]  System: You have 17 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 17
[10/14/2025 06:57]  System: You have 16 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 16
[10/14/2025 06:57]  a harvestman: a harvestman
[10/14/2025 06:57]  [Razor]: New target set.
[10/14/2025 06:57]  Razor: Attack: a cave dracolisk
[10/14/2025 06:57]  System: You strike your target from the shadows!
[10/14/2025 06:57]  System: You fail to steal from the creature.
[10/14/2025 06:57]  System: You have lost a little karma.
[10/14/2025 06:57]  System: You have gained a very large amount of karma.
[10/14/2025 06:57]  GawdHammer: (Shadow Aspect 65,341.8/250000 xp)
[10/14/2025 06:57]  System: -8000
[10/14/2025 06:57]  You see: a cave dracolisk corpse
[10/14/2025 06:57]  a harvestman: a harvestman
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:57]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 06:57]  a harvestman: a harvestman
[10/14/2025 06:57]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:57]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:57]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 06:57]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:57]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:57]  System: You have 23 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 23
[10/14/2025 06:57]  System: You have 22 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 22
[10/14/2025 06:57]  System: You have 21 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 21
[10/14/2025 06:57]  a cave dracolisk: a cave dracolisk
[10/14/2025 06:57]  System: You have 20 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 20
[10/14/2025 06:57]  System: You have 19 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 19
[10/14/2025 06:57]  System: You have 18 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 18
[10/14/2025 06:57]  System: You have 17 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 17
[10/14/2025 06:57]  System: You have 16 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 16
[10/14/2025 06:57]  You see: a forktongue shocktrooper corpse
[10/14/2025 06:57]  You see: a forktongue battlekhan corpse
[10/14/2025 06:57]  System: You have 15 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 15
[10/14/2025 06:57]  System: You have 14 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 14
[10/14/2025 06:57]  System: You have 13 stealth steps remaining.
[10/14/2025 06:57]  [Razor]: Steps: 13
[10/14/2025 06:57]  System: You have 12 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 12
[10/14/2025 06:58]  System: You have 11 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 11
[10/14/2025 06:58]  System: You have 10 stealth steps remaining.
[10/14/2025 06:58]  Razor: [10 Steps...]
[10/14/2025 06:58]  [Razor]: Steps: 10
[10/14/2025 06:58]  System: You have 9 stealth steps remaining.
[10/14/2025 06:58]  Razor: [9 Steps...]
[10/14/2025 06:58]  [Razor]: Steps: 9
[10/14/2025 06:58]  System: You have 8 stealth steps remaining.
[10/14/2025 06:58]  Razor: [8 Steps...]
[10/14/2025 06:58]  [Razor]: Steps: 8
[10/14/2025 06:58]  [Razor]: New target set.
[10/14/2025 06:58]  Razor: Target: a harvestman
[10/14/2025 06:58]  System: You will automatically stealth when out of steps.
[10/14/2025 06:58]  Razor: [Auto-stealth ready.]
[10/14/2025 06:58]  Razor: Attack: a harvestman
[10/14/2025 06:58]  System: You strike your target from the shadows!
[10/14/2025 06:58]  GawdHammer: You steal 554 gold coin.
[10/14/2025 06:58]  System: You steal 554 gold coin.
[10/14/2025 06:58]  GawdHammer: (Shadow Aspect 65,346.9/250000 xp)
[10/14/2025 06:58]  System: You have lost a little karma.
[10/14/2025 06:58]  System: You have gained a very large amount of karma.
[10/14/2025 06:58]  GawdHammer: (Shadow Aspect 65,367.4/250000 xp)
[10/14/2025 06:58]  System: -6000
[10/14/2025 06:58]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:58]  You see: a harvestman corpse
[10/14/2025 06:58]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:58]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:58]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:58]  a harvestman: a harvestman
[10/14/2025 06:58]  System: You have 23 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 23
[10/14/2025 06:58]  [Razor]: New target set.
[10/14/2025 06:58]  Razor: Target: a cave dracolisk
[10/14/2025 06:58]  System: You have 22 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 22
[10/14/2025 06:58]  System: You have 21 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 21
[10/14/2025 06:58]  System: You have 20 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 20
[10/14/2025 06:58]  System: You have 19 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 19
[10/14/2025 06:58]  You see: a ghostly dragonknight corpse
[10/14/2025 06:58]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 06:58]  System: You have 18 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 18
[10/14/2025 06:58]  Razor: Attack: a cave dracolisk
[10/14/2025 06:58]  System: You have 17 stealth steps remaining.
[10/14/2025 06:58]  System: You will automatically stealth when out of steps.
[10/14/2025 06:58]  Razor: [Auto-stealth ready.]
[10/14/2025 06:58]  [Razor]: Steps: 17
[10/14/2025 06:58]  System: You strike your target from the shadows!
[10/14/2025 06:58]  GawdHammer: You steal 303 gold coin.
[10/14/2025 06:58]  System: You steal 303 gold coin.
[10/14/2025 06:58]  GawdHammer: (Shadow Aspect 65,373.1/250000 xp)
[10/14/2025 06:58]  System: You have lost a little karma.
[10/14/2025 06:58]  a cave dracolisk: -7716
[10/14/2025 06:58]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:58]  System: You begin to move quietly. (30 steps)
[10/14/2025 06:58]  System: You are no longer hindered.
[10/14/2025 06:58]  System: You strike your target from the shadows!
[10/14/2025 06:58]  System: You have already stolen from this creature.
[10/14/2025 06:58]  System: You have gained a very large amount of karma.
[10/14/2025 06:58]  GawdHammer: (Shadow Aspect 65,396.0/250000 xp)
[10/14/2025 06:58]  System: -284
[10/14/2025 06:58]  You see: a cave dracolisk corpse
[10/14/2025 06:58]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:58]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:58]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:58]  a cave dracolisk: a cave dracolisk
[10/14/2025 06:58]  You see: a cave dracolisk corpse
[10/14/2025 06:58]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:58]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:58]  a harvestman: a harvestman
[10/14/2025 06:58]  You see: a ghostly dragonknight corpse
[10/14/2025 06:58]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 06:58]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:58]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:58]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:58]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:58]  System: You have 23 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 23
[10/14/2025 06:58]  System: You have 22 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 22
[10/14/2025 06:58]  System: You have 21 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 21
[10/14/2025 06:58]  System: You have 20 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 20
[10/14/2025 06:58]  System: You have 19 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 19
[10/14/2025 06:58]  System: You have 18 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 18
[10/14/2025 06:58]  System: You have 17 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 17
[10/14/2025 06:58]  a dusk dragon: a dusk dragon
[10/14/2025 06:58]  System: You have 16 stealth steps remaining.
[10/14/2025 06:58]  [Razor]: Steps: 16
[10/14/2025 06:58]  [Razor]: New target set.
[10/14/2025 06:58]  Razor: Target: a harvestman
[10/14/2025 06:58]  Razor: Attack: a harvestman
[10/14/2025 06:58]  System: You strike your target from the shadows!
[10/14/2025 06:58]  GawdHammer: You steal 270 gold coin.
[10/14/2025 06:58]  System: You steal 270 gold coin.
[10/14/2025 06:58]  GawdHammer: (Shadow Aspect 65,401.1/250000 xp)
[10/14/2025 06:58]  System: You have lost a little karma.
[10/14/2025 06:58]  System: You have gained a very large amount of karma.
[10/14/2025 06:58]  GawdHammer: (Shadow Aspect 65,421.6/250000 xp)
[10/14/2025 06:58]  System: -6000
[10/14/2025 06:58]  You see: a harvestman corpse
[10/14/2025 06:58]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:58]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:59]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:59]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:59]  System: You have 23 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 23
[10/14/2025 06:59]  System: You have 22 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 22
[10/14/2025 06:59]  System: You have 21 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 21
[10/14/2025 06:59]  System: You have 20 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 20
[10/14/2025 06:59]  System: You have 19 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 19
[10/14/2025 06:59]  System: You have 18 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 18
[10/14/2025 06:59]  [Razor]: New target set.
[10/14/2025 06:59]  Razor: Target: a harvestman
[10/14/2025 06:59]  System: You will automatically stealth when out of steps.
[10/14/2025 06:59]  Razor: [Auto-stealth ready.]
[10/14/2025 06:59]  Razor: Attack: a harvestman
[10/14/2025 06:59]  System: You strike your target from the shadows!
[10/14/2025 06:59]  GawdHammer: You steal 269 gold coin.
[10/14/2025 06:59]  System: You steal 269 gold coin.
[10/14/2025 06:59]  GawdHammer: (Shadow Aspect 65,426.8/250000 xp)
[10/14/2025 06:59]  System: You have lost a little karma.
[10/14/2025 06:59]  System: You have gained a very large amount of karma.
[10/14/2025 06:59]  GawdHammer: (Shadow Aspect 65,447.3/250000 xp)
[10/14/2025 06:59]  System: -6000
[10/14/2025 06:59]  Razor: Hiding and waiting to sneak...
[10/14/2025 06:59]  You see: a harvestman corpse
[10/14/2025 06:59]  GawdHammer: You have hidden yourself well.
[10/14/2025 06:59]  System: You may now use another smoke bomb.
[10/14/2025 06:59]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 06:59]  System: You begin to move quietly. (24 steps)
[10/14/2025 06:59]  System: You have 23 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 23
[10/14/2025 06:59]  You see: a cave dracolisk corpse
[10/14/2025 06:59]  System: You have 22 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 22
[10/14/2025 06:59]  System: You have 21 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 21
[10/14/2025 06:59]  System: You have 20 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 20
[10/14/2025 06:59]  System: You have 19 stealth steps remaining.
[10/14/2025 06:59]  [Razor]: Steps: 19
[10/14/2025 06:59]  You see: a cave dracolisk corpse
[10/14/2025 06:59]  [Razor]: New target set.
[10/14/2025 06:59]  Razor: Target: a cave dracolisk
[10/14/2025 06:59]  Razor: Attack: a cave dracolisk
[10/14/2025 06:59]  System: You strike your target from the shadows!
[10/14/2025 06:59]  GawdHammer: You steal 308 gold coin.
[10/14/2025 06:59]  System: You steal 308 gold coin.
[10/14/2025 06:59]  GawdHammer: (Shadow Aspect 65,453.0/250000 xp)
[10/14/2025 06:59]  System: You have lost a little karma.
[10/14/2025 06:59]  System: You have gained a very large amount of karma.
[10/14/2025 06:59]  GawdHammer: (Shadow Aspect 65,475.9/250000 xp)
[10/14/2025 06:59]  System: -8000
[10/14/2025 06:59]  You see: a cave dracolisk corpse
[10/14/2025 06:59]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:59]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:59]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 06:59]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 06:59]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 06:59]  a dusk dragon: a dusk dragon
[10/14/2025 06:59]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 06:59]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 06:59]  You see: a cave dracolisk corpse
[10/14/2025 06:59]  You see: a cave dracolisk corpse
[10/14/2025 06:59]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 06:59]  You see: a ghostly dragonknight corpse
[10/14/2025 06:59]  You see: a harvestman corpse
[10/14/2025 07:00]  a bonfire wisp: a bonfire wisp
[10/14/2025 07:00]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:00]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:00]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:00]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:00]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:00]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:00]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:00]  System: You have 23 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 23
[10/14/2025 07:00]  System: You have 22 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 22
[10/14/2025 07:00]  System: You have 21 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 21
[10/14/2025 07:00]  System: You have 20 stealth steps remaining.
[10/14/2025 07:00]  GrannyFlaps: Aegis Keep Guardian
[10/14/2025 07:00]  GrannyFlaps: [Au]
[10/14/2025 07:00]  GrannyFlaps: Lady GrannyFlaps
[10/14/2025 07:00]  [Razor]: Steps: 20
[10/14/2025 07:00]  a wyvern: a wyvern
[10/14/2025 07:00]  GrannyFlaps: Aegis Keep Guardian
[10/14/2025 07:00]  GrannyFlaps: [Au]
[10/14/2025 07:00]  GrannyFlaps: Lady GrannyFlaps
[10/14/2025 07:00]  System: You have 19 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 19
[10/14/2025 07:00]  You see: a wyvern corpse
[10/14/2025 07:00]  System: You have 18 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 18
[10/14/2025 07:00]  [Razor]: New target set.
[10/14/2025 07:00]  Razor: Target: an ophidian shaman
[10/14/2025 07:00]  You see: a wyvern corpse
[10/14/2025 07:00]  System: You have 17 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 17
[10/14/2025 07:00]  System: You have 16 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 16
[10/14/2025 07:00]  System: You have 15 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 15
[10/14/2025 07:00]  a wyvern: a wyvern
[10/14/2025 07:00]  Boy'z: Domesticator
[10/14/2025 07:00]  Boy'z: Lady Boy'z
[10/14/2025 07:00]  System: You have 14 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 14
[10/14/2025 07:00]  You see: a wyvern corpse
[10/14/2025 07:00]  a wyvern: a wyvern
[10/14/2025 07:00]  System: You will automatically stealth when out of steps.
[10/14/2025 07:00]  Razor: [Auto-stealth ready.]
[10/14/2025 07:00]  Razor: Attack: an ophidian shaman
[10/14/2025 07:00]  System: You have 13 stealth steps remaining.
[10/14/2025 07:00]  System: You strike your target from the shadows!
[10/14/2025 07:00]  GawdHammer: You steal 592 gold coin.
[10/14/2025 07:00]  System: You steal 592 gold coin.
[10/14/2025 07:00]  GawdHammer: (Shadow Aspect 65,481.8/250000 xp)
[10/14/2025 07:00]  System: You have lost a little karma.
[10/14/2025 07:00]  System: You have gained a very large amount of karma.
[10/14/2025 07:00]  GawdHammer: (Shadow Aspect 65,505.5/250000 xp)
[10/14/2025 07:00]  System: -7000
[10/14/2025 07:00]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:00]  You see: an ophidian shaman corpse
[10/14/2025 07:00]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:00]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:00]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:00]  [Razor]: New target set.
[10/14/2025 07:00]  Razor: Target: an ophidian dragonguard
[10/14/2025 07:00]  System: You have 23 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 23
[10/14/2025 07:00]  System: You have 22 stealth steps remaining.
[10/14/2025 07:00]  [Razor]: Steps: 22
[10/14/2025 07:00]  Razor: Attack: an ophidian dragonguard
[10/14/2025 07:00]  System: You will automatically stealth when out of steps.
[10/14/2025 07:00]  Razor: [Auto-stealth ready.]
[10/14/2025 07:00]  System: You have 21 stealth steps remaining.
[10/14/2025 07:00]  System: You strike your target from the shadows!
[10/14/2025 07:00]  GawdHammer: You steal trap rune.
[10/14/2025 07:00]  System: You steal trap rune.
[10/14/2025 07:00]  GawdHammer: You steal 263 gold coin.
[10/14/2025 07:00]  System: You steal 263 gold coin.
[10/14/2025 07:00]  GawdHammer: (Shadow Aspect 65,511.1/250000 xp)
[10/14/2025 07:00]  System: You have lost a little karma.
[10/14/2025 07:00]  an ophidian dragonguard: -7957
[10/14/2025 07:00]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:00]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:00]  System: You are no longer hindered.
[10/14/2025 07:01]  [Razor]: New target set.
[10/14/2025 07:01]  Razor: Target: an ophidian dragonguard
[10/14/2025 07:01]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:01]  System: You will automatically stealth when out of steps.
[10/14/2025 07:01]  Razor: [Auto-stealth ready.]
[10/14/2025 07:01]  Razor: Attack: an ophidian dragonguard
[10/14/2025 07:01]  System: You strike your target from the shadows!
[10/14/2025 07:01]  GawdHammer: You steal 267 gold coin.
[10/14/2025 07:01]  System: You steal 267 gold coin.
[10/14/2025 07:01]  GawdHammer: (Shadow Aspect 65,516.7/250000 xp)
[10/14/2025 07:01]  System: You have lost a little karma.
[10/14/2025 07:01]  System: You have gained a very large amount of karma.
[10/14/2025 07:01]  GawdHammer: (Shadow Aspect 65,539.1/250000 xp)
[10/14/2025 07:01]  System: -8000
[10/14/2025 07:01]  You see: an ophidian dragonguard corpse
[10/14/2025 07:01]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:01]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:01]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:01]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:01]  System: You have 23 stealth steps remaining.
[10/14/2025 07:01]  [Razor]: Steps: 23
[10/14/2025 07:01]  System: You have 22 stealth steps remaining.
[10/14/2025 07:01]  [Razor]: New target set.
[10/14/2025 07:01]  Razor: Target: an ophidian dragonguard
[10/14/2025 07:01]  [Razor]: Steps: 22
[10/14/2025 07:01]  Razor: Attack: an ophidian dragonguard
[10/14/2025 07:01]  System: You strike your target from the shadows!
[10/14/2025 07:01]  System: You have already stolen from this creature.
[10/14/2025 07:01]  GawdHammer: (Shadow Aspect 65,561.5/250000 xp)
[10/14/2025 07:01]  System: -43
[10/14/2025 07:01]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:01]  You see: an ophidian dragonguard corpse
[10/14/2025 07:01]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:01]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:01]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:01]  System: You have 23 stealth steps remaining.
[10/14/2025 07:01]  [Razor]: Steps: 23
[10/14/2025 07:01]  [Razor]: New target set.
[10/14/2025 07:01]  Razor: Target: a bonfire wisp
[10/14/2025 07:01]  System: You have 22 stealth steps remaining.
[10/14/2025 07:01]  [Razor]: Steps: 22
[10/14/2025 07:01]  Razor: Attack: a bonfire wisp
[10/14/2025 07:01]  System: Ynorev has completed the achievement: Connoisseur (Basic).
[10/14/2025 07:01]  System: You have 21 stealth steps remaining.
[10/14/2025 07:01]  System: You strike your target from the shadows!
[10/14/2025 07:01]  GawdHammer: You steal 343 gold coin.
[10/14/2025 07:01]  System: You steal 343 gold coin.
[10/14/2025 07:01]  GawdHammer: (Shadow Aspect 65,568.4/250000 xp)
[10/14/2025 07:01]  System: You have lost a little karma.
[10/14/2025 07:01]  GawdHammer: -11
[10/14/2025 07:01]  System: You have gained a very large amount of karma.
[10/14/2025 07:01]  GawdHammer: (Shadow Aspect 65,596.0/250000 xp)
[10/14/2025 07:01]  System: -6000
[10/14/2025 07:01]  You see: a bonfire wisp corpse
[10/14/2025 07:01]  System: VonDarkmane has registered an antiquity: Jaguar Trophy
[10/14/2025 07:01]  You see: a bonfire wisp corpse
[10/14/2025 07:01]  System: VonDarkmane has registered an antiquity: Lumberjack Saw
[10/14/2025 07:01]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:01]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:01]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:01]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:01]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 07:01]  You see: a wyvern corpse
[10/14/2025 07:01]  a wyvern: a wyvern
[10/14/2025 07:01]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:01]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:01]  You see: a wyvern corpse
[10/14/2025 07:01]  System: The world will save in 15 seconds.
[10/14/2025 07:01]  System: You may now use another smoke bomb.
[10/14/2025 07:01]  a wyvern: a wyvern
[10/14/2025 07:01]  Glow: Lady Glow
[10/14/2025 07:01]  a fire minion: (summoned)
[10/14/2025 07:01]  a fire minion: a fire minion
[10/14/2025 07:01]  a water elemental: (summoned)
[10/14/2025 07:01]  a water elemental: a water elemental
[10/14/2025 07:01]  a fire elemental: (summoned)
[10/14/2025 07:01]  a fire elemental: a fire elemental
[10/14/2025 07:02]  You see: a harvestman corpse
[10/14/2025 07:02]  You see: a cave dracolisk corpse
[10/14/2025 07:02]  System: The world is saving, please wait.
[10/14/2025 07:02]  System: World save complete. The entire process took 3.4 seconds.
[10/14/2025 07:02]  You see: an ophidian dragonguard corpse
[10/14/2025 07:02]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:02]  You see: an ophidian shaman corpse
[10/14/2025 07:02]  System: Sas has completed the achievement: Order of Armorers Initiate.
[10/14/2025 07:02]  You see: an ophidian shaman corpse
[10/14/2025 07:02]  an earth dragon: an earth dragon
[10/14/2025 07:02]  Nekky the Nekro: [Councilor, Yew]
[10/14/2025 07:02]  Nekky the Nekro: Lord Nekky the Nekro
[10/14/2025 07:02]  Nekky the Nekro: Kal Vas Flam
[10/14/2025 07:02]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:02]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:02]  [Razor]: Steps: 0
[10/14/2025 07:02]  System: You are no longer hindered.
[10/14/2025 07:02]  System: You have 29 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 29
[10/14/2025 07:02]  System: You have 28 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 28
[10/14/2025 07:02]  System: You have 27 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 27
[10/14/2025 07:02]  System: You have 26 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 26
[10/14/2025 07:02]  System: You have 25 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 25
[10/14/2025 07:02]  You see: an ophidian dragonguard corpse
[10/14/2025 07:02]  System: You have 24 stealth steps remaining.
[10/14/2025 07:02]  GawdHammer: +1
[10/14/2025 07:02]  System: some damage has been healed : 1
[10/14/2025 07:02]  [Razor]: Steps: 24
[10/14/2025 07:02]  You see: an ophidian dragonguard corpse
[10/14/2025 07:02]  System: You have 23 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 23
[10/14/2025 07:02]  System: You have 22 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 22
[10/14/2025 07:02]  System: You have 21 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 21
[10/14/2025 07:02]  System: You have 20 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 20
[10/14/2025 07:02]  You see: an earth dragon corpse
[10/14/2025 07:02]  System: You have 19 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 19
[10/14/2025 07:02]  System: You have 18 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 18
[10/14/2025 07:02]  System: You have 17 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 17
[10/14/2025 07:02]  System: You have 16 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 16
[10/14/2025 07:02]  System: You have 15 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 15
[10/14/2025 07:02]  System: You have 14 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 14
[10/14/2025 07:02]  System: You have 13 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 13
[10/14/2025 07:02]  System: You have 12 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 12
[10/14/2025 07:02]  System: You have 11 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 11
[10/14/2025 07:02]  System: You have 10 stealth steps remaining.
[10/14/2025 07:02]  Razor: [10 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 10
[10/14/2025 07:02]  System: You have 9 stealth steps remaining.
[10/14/2025 07:02]  Razor: [9 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 9
[10/14/2025 07:02]  System: You have 8 stealth steps remaining.
[10/14/2025 07:02]  Razor: [8 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 8
[10/14/2025 07:02]  System: You have 7 stealth steps remaining.
[10/14/2025 07:02]  Razor: [7 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 7
[10/14/2025 07:02]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:02]  System: You will automatically stealth when out of steps.
[10/14/2025 07:02]  Razor: [Auto-stealth ready.]
[10/14/2025 07:02]  System: You have 6 stealth steps remaining.
[10/14/2025 07:02]  Razor: [6 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 6
[10/14/2025 07:02]  System: You have 5 stealth steps remaining.
[10/14/2025 07:02]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:02]  [Razor]: Steps: 5
[10/14/2025 07:02]  System: You have 4 stealth steps remaining.
[10/14/2025 07:02]  Razor: [4 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 4
[10/14/2025 07:02]  System: You have 3 stealth steps remaining.
[10/14/2025 07:02]  Razor: [3 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 3
[10/14/2025 07:02]  System: You have 2 stealth steps remaining.
[10/14/2025 07:02]  Razor: [2 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 2
[10/14/2025 07:02]  System: You have 1 stealth steps remaining.
[10/14/2025 07:02]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:02]  [Razor]: Steps: 1
[10/14/2025 07:02]  System: You have 0 stealth steps remaining.
[10/14/2025 07:02]  System: You feel ready to continue stealthing.
[10/14/2025 07:02]  Razor: [Stop!]
[10/14/2025 07:02]  [Razor]: Steps: 0
[10/14/2025 07:02]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:02]  System: You have 23 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 23
[10/14/2025 07:02]  System: You have 22 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 22
[10/14/2025 07:02]  System: You have 21 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 21
[10/14/2025 07:02]  System: You have 20 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 20
[10/14/2025 07:02]  System: You have 19 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 19
[10/14/2025 07:02]  System: You have 18 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 18
[10/14/2025 07:02]  System: You have 17 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 17
[10/14/2025 07:02]  System: You have 16 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 16
[10/14/2025 07:02]  System: You have 15 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 15
[10/14/2025 07:02]  System: You have 14 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 14
[10/14/2025 07:02]  System: You have 13 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 13
[10/14/2025 07:02]  System: You have 12 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 12
[10/14/2025 07:02]  System: You have 11 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 11
[10/14/2025 07:02]  System: You have 10 stealth steps remaining.
[10/14/2025 07:02]  Razor: [10 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 10
[10/14/2025 07:02]  a wyvern: a wyvern
[10/14/2025 07:02]  System: You have 9 stealth steps remaining.
[10/14/2025 07:02]  Razor: [9 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 9
[10/14/2025 07:02]  System: You have 8 stealth steps remaining.
[10/14/2025 07:02]  Razor: [8 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 8
[10/14/2025 07:02]  System: You have 7 stealth steps remaining.
[10/14/2025 07:02]  Razor: [7 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 7
[10/14/2025 07:02]  System: You have 6 stealth steps remaining.
[10/14/2025 07:02]  Razor: [6 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 6
[10/14/2025 07:02]  System: You have 5 stealth steps remaining.
[10/14/2025 07:02]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:02]  [Razor]: Steps: 5
[10/14/2025 07:02]  System: You have 4 stealth steps remaining.
[10/14/2025 07:02]  Razor: [4 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 4
[10/14/2025 07:02]  System: You will automatically stealth when out of steps.
[10/14/2025 07:02]  Razor: [Auto-stealth ready.]
[10/14/2025 07:02]  System: You have 3 stealth steps remaining.
[10/14/2025 07:02]  Razor: [3 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 3
[10/14/2025 07:02]  System: You have 2 stealth steps remaining.
[10/14/2025 07:02]  Razor: [2 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 2
[10/14/2025 07:02]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:02]  a wyvern: a wyvern
[10/14/2025 07:02]  System: You have 1 stealth steps remaining.
[10/14/2025 07:02]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:02]  [Razor]: Steps: 1
[10/14/2025 07:02]  System: You have 0 stealth steps remaining.
[10/14/2025 07:02]  System: You feel ready to continue stealthing.
[10/14/2025 07:02]  Razor: [Stop!]
[10/14/2025 07:02]  [Razor]: Steps: 0
[10/14/2025 07:02]  You see: an ophidian shaman corpse
[10/14/2025 07:02]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:02]  System: You have 23 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 23
[10/14/2025 07:02]  System: You have 22 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 22
[10/14/2025 07:02]  System: You have 21 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 21
[10/14/2025 07:02]  System: You have 20 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 20
[10/14/2025 07:02]  System: You have 19 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 19
[10/14/2025 07:02]  System: You have 18 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 18
[10/14/2025 07:02]  System: You have 17 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 17
[10/14/2025 07:02]  System: You have 16 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 16
[10/14/2025 07:02]  [Razor]: New target set.
[10/14/2025 07:02]  Razor: Target: an ophidian shaman
[10/14/2025 07:02]  System: You have 15 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 15
[10/14/2025 07:02]  System: You have 14 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 14
[10/14/2025 07:02]  System: You have 13 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 13
[10/14/2025 07:02]  You see: an ophidian dragonguard corpse
[10/14/2025 07:02]  System: You have 12 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 12
[10/14/2025 07:02]  You see: an earth dragon corpse
[10/14/2025 07:02]  System: You have 11 stealth steps remaining.
[10/14/2025 07:02]  [Razor]: Steps: 11
[10/14/2025 07:02]  Razor: Attack: an ophidian shaman
[10/14/2025 07:02]  System: You have 10 stealth steps remaining.
[10/14/2025 07:02]  Razor: [10 Steps...]
[10/14/2025 07:02]  [Razor]: Steps: 10
[10/14/2025 07:02]  System: You have 9 stealth steps remaining.
[10/14/2025 07:02]  Razor: [9 Steps...]
[10/14/2025 07:02]  System: You strike your target from the shadows!
[10/14/2025 07:02]  GawdHammer: You steal 292 gold coin.
[10/14/2025 07:02]  System: You steal 292 gold coin.
[10/14/2025 07:02]  GawdHammer: (Shadow Aspect 65,601.9/250000 xp)
[10/14/2025 07:02]  System: You have lost a little karma.
[10/14/2025 07:02]  System: You have gained a very large amount of karma.
[10/14/2025 07:02]  GawdHammer: (Shadow Aspect 65,625.7/250000 xp)
[10/14/2025 07:02]  System: -7000
[10/14/2025 07:02]  You see: an ophidian shaman corpse
[10/14/2025 07:02]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:02]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:02]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 07:02]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 07:02]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 07:02]  [Razor]: Finished 6 queued actions in 3.0 seconds.
[10/14/2025 07:03]  Macer Windu: Legendary Boss Slayer
[10/14/2025 07:03]  Macer Windu: [Officer, Path]
[10/14/2025 07:03]  Macer Windu: Macer Windu
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  an ember drake: an ember drake
[10/14/2025 07:03]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:03]  GawdHammer: You can't seem to hide right now.
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  GawdHammer: -40
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  a smoke drake: a smoke drake
[10/14/2025 07:03]  GawdHammer: You can't seem to hide right now.
[10/14/2025 07:03]  BelnaBleed: [Officer, MEMO]
[10/14/2025 07:03]  BelnaBleed: Lord BelnaBleed
[10/14/2025 07:03]  GawdHammer: +38
[10/14/2025 07:03]  System: some damage has been healed : 38
[10/14/2025 07:03]  System: You drink a healing potion.
[10/14/2025 07:03]  System: You may now use another smoke bomb.
[10/14/2025 07:03]  You see: an ophidian dragonguard corpse
[10/14/2025 07:03]  You see: an ophidian dragonguard corpse
[10/14/2025 07:03]  BelnaBleed: [Officer, MEMO]
[10/14/2025 07:03]  BelnaBleed: Lord BelnaBleed
[10/14/2025 07:03]  Razor: You fail to hide.
[10/14/2025 07:03]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:03]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:03]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:03]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:03]  System: You have 23 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 23
[10/14/2025 07:03]  [Razor]: Steps: 23
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  System: You have 22 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 22
[10/14/2025 07:03]  System: You have 21 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 21
[10/14/2025 07:03]  System: You have 20 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 20
[10/14/2025 07:03]  System: You have 19 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 19
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  System: You have 18 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 18
[10/14/2025 07:03]  System: You have 17 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 17
[10/14/2025 07:03]  System: You have 16 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 16
[10/14/2025 07:03]  System: You have 15 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 15
[10/14/2025 07:03]  System: You have 14 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 14
[10/14/2025 07:03]  System: You have 13 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 13
[10/14/2025 07:03]  System: You have 12 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 12
[10/14/2025 07:03]  System: You have 11 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 11
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  System: You have 10 stealth steps remaining.
[10/14/2025 07:03]  Razor: [10 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 10
[10/14/2025 07:03]  System: You have 9 stealth steps remaining.
[10/14/2025 07:03]  Razor: [9 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 9
[10/14/2025 07:03]  You see: an eldritch drake corpse
[10/14/2025 07:03]  an ember drake: an ember drake
[10/14/2025 07:03]  System: You have 8 stealth steps remaining.
[10/14/2025 07:03]  Razor: [8 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 8
[10/14/2025 07:03]  System: You have 7 stealth steps remaining.
[10/14/2025 07:03]  Razor: [7 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 7
[10/14/2025 07:03]  System: You have 6 stealth steps remaining.
[10/14/2025 07:03]  Razor: [6 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 6
[10/14/2025 07:03]  System: You have 5 stealth steps remaining.
[10/14/2025 07:03]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:03]  [Razor]: Steps: 5
[10/14/2025 07:03]  System: You will automatically stealth when out of steps.
[10/14/2025 07:03]  Razor: [Auto-stealth ready.]
[10/14/2025 07:03]  System: You have 4 stealth steps remaining.
[10/14/2025 07:03]  Razor: [4 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 4
[10/14/2025 07:03]  System: You have 3 stealth steps remaining.
[10/14/2025 07:03]  Razor: [3 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 3
[10/14/2025 07:03]  System: You have 2 stealth steps remaining.
[10/14/2025 07:03]  Razor: [2 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 2
[10/14/2025 07:03]  System: You have 1 stealth steps remaining.
[10/14/2025 07:03]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:03]  [Razor]: Steps: 1
[10/14/2025 07:03]  System: You have 0 stealth steps remaining.
[10/14/2025 07:03]  System: You feel ready to continue stealthing.
[10/14/2025 07:03]  Razor: [Stop!]
[10/14/2025 07:03]  [Razor]: Steps: 0
[10/14/2025 07:03]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:03]  System: You have 23 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 23
[10/14/2025 07:03]  System: You have 22 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 22
[10/14/2025 07:03]  an eldritch drake: an eldritch drake
[10/14/2025 07:03]  System: You have 21 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 21
[10/14/2025 07:03]  System: You have 20 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 20
[10/14/2025 07:03]  System: You have 19 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 19
[10/14/2025 07:03]  System: You have 18 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 18
[10/14/2025 07:03]  System: You have 17 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 17
[10/14/2025 07:03]  System: You have 16 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 16
[10/14/2025 07:03]  System: You have 15 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 15
[10/14/2025 07:03]  System: You have 14 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 14
[10/14/2025 07:03]  System: You have 13 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 13
[10/14/2025 07:03]  System: You have 12 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 12
[10/14/2025 07:03]  System: You have 11 stealth steps remaining.
[10/14/2025 07:03]  [Razor]: Steps: 11
[10/14/2025 07:03]  System: You have 10 stealth steps remaining.
[10/14/2025 07:03]  Razor: [10 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 10
[10/14/2025 07:03]  System: You have 9 stealth steps remaining.
[10/14/2025 07:03]  Razor: [9 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 9
[10/14/2025 07:03]  [Razor]: New target set.
[10/14/2025 07:03]  Razor: Target: an ember drake
[10/14/2025 07:03]  System: You have 8 stealth steps remaining.
[10/14/2025 07:03]  Razor: [8 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 8
[10/14/2025 07:03]  System: You have 7 stealth steps remaining.
[10/14/2025 07:03]  Razor: [7 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 7
[10/14/2025 07:03]  System: You have 6 stealth steps remaining.
[10/14/2025 07:03]  Razor: [6 Steps...]
[10/14/2025 07:03]  [Razor]: Steps: 6
[10/14/2025 07:03]  System: You have 5 stealth steps remaining.
[10/14/2025 07:03]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:03]  Razor: Attack: an ember drake
[10/14/2025 07:03]  [Razor]: Steps: 5
[10/14/2025 07:03]  System: You have 4 stealth steps remaining.
[10/14/2025 07:03]  Razor: [4 Steps...]
[10/14/2025 07:03]  System: You strike your target from the shadows!
[10/14/2025 07:03]  System: Society Job Progress: 3/10 [Steal 10 Black Goods].
[10/14/2025 07:03]  GawdHammer: You steal extremely common black goods.
[10/14/2025 07:03]  System: You steal extremely common black goods.
[10/14/2025 07:03]  GawdHammer: You steal 220 gold coin.
[10/14/2025 07:03]  System: You steal 220 gold coin.
[10/14/2025 07:03]  GawdHammer: (Shadow Aspect 65,630.1/250000 xp)
[10/14/2025 07:03]  System: You have lost a little karma.
[10/14/2025 07:03]  System: You have gained a lot of karma.
[10/14/2025 07:03]  GawdHammer: (Shadow Aspect 65,648.0/250000 xp)
[10/14/2025 07:03]  System: -6000
[10/14/2025 07:03]  You see: an ember drake corpse
[10/14/2025 07:03]  : stalagmites
[10/14/2025 07:03]  : stalagmites
[10/14/2025 07:04]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:04]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:04]  an ember drake: an ember drake
[10/14/2025 07:04]  You see: an eldritch drake corpse
[10/14/2025 07:04]  an ember drake: an ember drake
[10/14/2025 07:04]  You see: an eldritch drake corpse
[10/14/2025 07:04]  a wyvern hatchling: a wyvern hatchling
[10/14/2025 07:04]  a wyvern hatchling: a wyvern hatchling
[10/14/2025 07:04]  an ophidian warrior: an ophidian warrior
[10/14/2025 07:04]  an ophidian warrior: an ophidian warrior
[10/14/2025 07:04]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:04]  You see: an adder corpse
[10/14/2025 07:04]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:04]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:04]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:04]  System: You have 23 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 23
[10/14/2025 07:04]  You see: an adder corpse
[10/14/2025 07:04]  System: You have 22 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 22
[10/14/2025 07:04]  System: You have 21 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 21
[10/14/2025 07:04]  System: You have 20 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 20
[10/14/2025 07:04]  System: You have 19 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 19
[10/14/2025 07:04]  You see: an ophidian mage corpse
[10/14/2025 07:04]  System: You have 18 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 18
[10/14/2025 07:04]  an ophidian mage: an ophidian mage
[10/14/2025 07:04]  System: You have 17 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 17
[10/14/2025 07:04]  System: You have 16 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 16
[10/14/2025 07:04]  System: You have 15 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 15
[10/14/2025 07:04]  System: You have 14 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 14
[10/14/2025 07:04]  System: You have 13 stealth steps remaining.
[10/14/2025 07:04]  an ophidian warrior: Ththsathissitisth sas thylmissals sithtystsythys thesasishy tsy.
[10/14/2025 07:04]  [Razor]: Steps: 13
[10/14/2025 07:04]  System: You have 12 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 12
[10/14/2025 07:04]  System: You have 11 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 11
[10/14/2025 07:04]  System: You have 10 stealth steps remaining.
[10/14/2025 07:04]  Razor: [10 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 10
[10/14/2025 07:04]  System: You have 9 stealth steps remaining.
[10/14/2025 07:04]  Razor: [9 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 9
[10/14/2025 07:04]  System: You have 8 stealth steps remaining.
[10/14/2025 07:04]  Razor: [8 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 8
[10/14/2025 07:04]  System: You have 7 stealth steps remaining.
[10/14/2025 07:04]  Razor: [7 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 7
[10/14/2025 07:04]  System: You will automatically stealth when out of steps.
[10/14/2025 07:04]  Razor: [Auto-stealth ready.]
[10/14/2025 07:04]  System: You have 6 stealth steps remaining.
[10/14/2025 07:04]  Razor: [6 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 6
[10/14/2025 07:04]  System: You have 5 stealth steps remaining.
[10/14/2025 07:04]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:04]  [Razor]: Steps: 5
[10/14/2025 07:04]  System: You have 4 stealth steps remaining.
[10/14/2025 07:04]  Razor: [4 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 4
[10/14/2025 07:04]  an asp: an asp
[10/14/2025 07:04]  System: You have 3 stealth steps remaining.
[10/14/2025 07:04]  Razor: [3 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 3
[10/14/2025 07:04]  System: You have 2 stealth steps remaining.
[10/14/2025 07:04]  Razor: [2 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 2
[10/14/2025 07:04]  System: You have 1 stealth steps remaining.
[10/14/2025 07:04]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:04]  [Razor]: Steps: 1
[10/14/2025 07:04]  System: You have 0 stealth steps remaining.
[10/14/2025 07:04]  System: You feel ready to continue stealthing.
[10/14/2025 07:04]  Razor: [Stop!]
[10/14/2025 07:04]  [Razor]: Steps: 0
[10/14/2025 07:04]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:04]  System: You have 23 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 23
[10/14/2025 07:04]  System: You have 22 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 22
[10/14/2025 07:04]  System: You have 21 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 21
[10/14/2025 07:04]  System: You have 20 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 20
[10/14/2025 07:04]  System: You have 19 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 19
[10/14/2025 07:04]  System: You have 18 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 18
[10/14/2025 07:04]  System: You have 17 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 17
[10/14/2025 07:04]  System: You have 16 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 16
[10/14/2025 07:04]  System: You have 15 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 15
[10/14/2025 07:04]  System: You have 14 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 14
[10/14/2025 07:04]  System: You have 13 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 13
[10/14/2025 07:04]  You see: a dusk drake corpse
[10/14/2025 07:04]  You see: a dusk drake corpse
[10/14/2025 07:04]  Dame Alinor: Dame Alinor
[10/14/2025 07:04]  a dusk drake: a dusk drake
[10/14/2025 07:04]  System: You have 12 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 12
[10/14/2025 07:04]  You see: a dusk drake corpse
[10/14/2025 07:04]  You see: an ophidian warrior corpse
[10/14/2025 07:04]  System: You have 11 stealth steps remaining.
[10/14/2025 07:04]  [Razor]: Steps: 11
[10/14/2025 07:04]  System: You have 10 stealth steps remaining.
[10/14/2025 07:04]  Razor: [10 Steps...]
[10/14/2025 07:04]  [Razor]: Steps: 10
[10/14/2025 07:04]  You see: a drake corpse
[10/14/2025 07:04]  a drake: a drake
[10/14/2025 07:04]  System: You are now under the protection of the town guards.
[10/14/2025 07:04]  [Razor]: Steps: 10
[10/14/2025 07:04]  GawdHammer: You summon your mount.
[10/14/2025 07:04]  Caron: Caron the armorer
[10/14/2025 07:04]  Candida: Candida the miner
[10/14/2025 07:04]  Lev: Lev the fisherman
[10/14/2025 07:04]  Kaili: Kaili the blacksmith
[10/14/2025 07:04]  Troglodyte Rind: Troglodyte Rind
[10/14/2025 07:04]  Craftylady: Legendary Blacksmith
[10/14/2025 07:04]  Craftylady: Craftylady
[10/14/2025 07:04]  Trind: Trind the banker
[10/14/2025 07:04]  Chapman: Chapman the real estate broker
[10/14/2025 07:04]  Cam: Cam the Town Crier
[10/14/2025 07:04]  Jacinda: Jacinda the healer
[10/14/2025 07:04]  Claire: Claire the baker
[10/14/2025 07:04]  Gerik: Gerik the cook
[10/14/2025 07:04]  System: 68° 22'N, 177° 53'W
[10/14/2025 07:04]  System: Your skill in Tactics has decreased by 78.0.  It is now 0.0.
[10/14/2025 07:04]  GawdHammer: Kal Ort Por
[10/14/2025 07:04]  System: Now tracking: Cornwallis X (48 spaces to target)
[10/14/2025 07:04]  System: You have left the protection of the town guards.
[10/14/2025 07:04]  a horse: a horse
[10/14/2025 07:04]  an orc: an orc
[10/14/2025 07:04]  a great hart: a great hart
[10/14/2025 07:04]  a crow: a crow
[10/14/2025 07:04]  a wolf: a wolf
[10/14/2025 07:04]  a grizzly bear: a grizzly bear
[10/14/2025 07:04]  a pack horse: (tame)
[10/14/2025 07:04]  a pack horse: [344/2000 stones]
[10/14/2025 07:04]  a pack horse: [92/125 items]
[10/14/2025 07:04]  a pack horse: a pack horse
[10/14/2025 07:04]  a cougar: a cougar
[10/14/2025 07:04]  a headless: a headless
[10/14/2025 07:04]  a giant rat: a giant rat
[10/14/2025 07:04]  a hind: a hind
[10/14/2025 07:04]  a wisp: a wisp
[10/14/2025 07:04]  an eagle: an eagle
[10/14/2025 07:04]  a pack horse: (tame)
[10/14/2025 07:04]  a pack horse: [453/2000 stones]
[10/14/2025 07:04]  a pack horse: [125/125 items]
[10/14/2025 07:04]  a pack horse: a pack horse
[10/14/2025 07:04]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 07:04]  a giant spider: a giant spider
[10/14/2025 07:05]  You see: a storage shelf
[10/14/2025 07:05]  You see: [secure]
[10/14/2025 07:05]  System: Which container do you wish to restock this container from? (you may target yourself or a nearby friendly pack animal)
[10/14/2025 07:05]  System: 23 items were added.
[10/14/2025 07:05]  [Razor]: Warning: Garlic amount is now 0!
[10/14/2025 07:05]  [Razor]: Warning: Spider's Silk amount is now 0!
[10/14/2025 07:05]  [Razor]: Warning: Ginseng amount is now 0!
[10/14/2025 07:05]  [Razor]: Warning: Blood Moss amount is now 0!
[10/14/2025 07:05]  System: You deposit 8,767 gold into your bank box.
[10/14/2025 07:05]  a giant spider: a giant spider
[10/14/2025 07:05]  a horse: a horse
[10/14/2025 07:05]  GawdHammer: You dismiss your mount.
[10/14/2025 07:05]  You see: pouch
[10/14/2025 07:05]  You see: (40 items, 458 stones)
[10/14/2025 07:05]  [Razor]: Ignore List cleared
[10/14/2025 07:05]  [Razor]: drop - Not holding anything
[10/14/2025 07:05]  Razor: Select sort container
[10/14/2025 07:05]  [Razor]: Select target for variable 'sort_container'
[10/14/2025 07:05]  [Razor]: 'sort_container' script variable updated to '0x4C93D8A8'
[10/14/2025 07:05]  a wisp: a wisp
[10/14/2025 07:05]  Razor: unidentified leather gorget
[10/14/2025 07:05]  [Razor]: Added 1285053975 to ignore list
[10/14/2025 07:05]  Razor: unidentified leather cap
[10/14/2025 07:05]  [Razor]: Added 1285340327 to ignore list
[10/14/2025 07:05]  Razor: unidentified studded gorget
[10/14/2025 07:05]  [Razor]: Added 1285498253 to ignore list
[10/14/2025 07:05]  Razor: unidentified bone legs
[10/14/2025 07:05]  [Razor]: Added 1284943145 to ignore list
[10/14/2025 07:05]  Razor: unidentified bone skirt
[10/14/2025 07:05]  [Razor]: Added 1285558157 to ignore list
[10/14/2025 07:05]  Razor: unidentified bone skirt
[10/14/2025 07:05]  [Razor]: Added 1285085834 to ignore list
[10/14/2025 07:05]  Razor: unidentified female studded chest
[10/14/2025 07:05]  [Razor]: Added 1284965022 to ignore list
[10/14/2025 07:05]  Razor: unidentified studded arms
[10/14/2025 07:05]  [Razor]: Added 1285255388 to ignore list
[10/14/2025 07:05]  Razor: unidentified studded arms
[10/14/2025 07:05]  [Razor]: Added 1284890457 to ignore list
[10/14/2025 07:05]  Razor: unidentified ringmail gloves
[10/14/2025 07:05]  [Razor]: Added 1285518552 to ignore list
[10/14/2025 07:05]  Razor: unidentified ringmail helm
[10/14/2025 07:05]  [Razor]: Added 1285165632 to ignore list
[10/14/2025 07:05]  Razor: unidentified chainmail gloves
[10/14/2025 07:05]  [Razor]: Added 1284915395 to ignore list
[10/14/2025 07:05]  Razor: unidentified chainmail gorget
[10/14/2025 07:05]  [Razor]: Added 1285102489 to ignore list
[10/14/2025 07:05]  Razor: unidentified chainmail coif
[10/14/2025 07:05]  [Razor]: Added 1285510164 to ignore list
[10/14/2025 07:05]  Razor: unidentified bascinet
[10/14/2025 07:05]  [Razor]: Added 1285370432 to ignore list
[10/14/2025 07:05]  Razor: unidentified female platemail chest
[10/14/2025 07:05]  [Razor]: Added 1284999695 to ignore list
[10/14/2025 07:05]  Razor: unidentified platemail helm
[10/14/2025 07:05]  [Razor]: Added 1285447423 to ignore list
[10/14/2025 07:05]  Razor: unidentified assassin's knife
[10/14/2025 07:05]  [Razor]: Added 1284881791 to ignore list
[10/14/2025 07:05]  Razor: unidentified kryss
[10/14/2025 07:05]  [Razor]: Added 1285401246 to ignore list
[10/14/2025 07:05]  Razor: unidentified war fork
[10/14/2025 07:05]  [Razor]: Added 1284840249 to ignore list
[10/14/2025 07:05]  Razor: unidentified shawm (500/500 uses remaining)
[10/14/2025 07:05]  [Razor]: Added 1285195368 to ignore list
[10/14/2025 07:05]  Razor: unidentified tambourine (500/500 uses remaining)
[10/14/2025 07:05]  [Razor]: Added 1285135072 to ignore list
[10/14/2025 07:05]  Razor: unidentified bone shield
[10/14/2025 07:05]  [Razor]: Added 1285386706 to ignore list
[10/14/2025 07:05]  Razor: unidentified bone shield
[10/14/2025 07:05]  [Razor]: Added 1285314124 to ignore list
[10/14/2025 07:05]  Razor: unidentified bone shield
[10/14/2025 07:05]  [Razor]: Added 1285323401 to ignore list
[10/14/2025 07:05]  Razor: unidentified heater shield
[10/14/2025 07:05]  [Razor]: Added 1285420889 to ignore list
[10/14/2025 07:05]  Razor: unidentified parry dagger
[10/14/2025 07:05]  [Razor]: Added 1285597298 to ignore list
[10/14/2025 07:05]  Razor: amethyst : 4
[10/14/2025 07:05]  [Razor]: Added 1284965021 to ignore list
[10/14/2025 07:05]  Razor: tourmaline : 2
[10/14/2025 07:06]  [Razor]: Added 1285340326 to ignore list
[10/14/2025 07:06]  Razor: sapphire : 2
[10/14/2025 07:06]  [Razor]: Added 1284999694 to ignore list
[10/14/2025 07:06]  Razor: citrine : 16
[10/14/2025 07:06]  [Razor]: Added 1284890456 to ignore list
[10/14/2025 07:06]  Razor: amber : 6
[10/14/2025 07:06]  [Razor]: Added 1285152161 to ignore list
[10/14/2025 07:06]  Razor: ruby : 8
[10/14/2025 07:06]  [Razor]: Added 1284943144 to ignore list
[10/14/2025 07:06]  Razor: extremely common seed
[10/14/2025 07:06]  [Razor]: Added 1285323402 to ignore list
[10/14/2025 07:06]  Razor: research materials (double-click to research)
[10/14/2025 07:06]  [Razor]: Added 1285370433 to ignore list
[10/14/2025 07:06]  Razor: mastercrafting diagram
[10/14/2025 07:06]  [Razor]: Added 1285558158 to ignore list
[10/14/2025 07:06]  Razor: lumberjacking skill mastery scroll (used to increase a player's skill cap for a skill by 1)
[10/14/2025 07:06]  [Razor]: Added 1285255390 to ignore list
[10/14/2025 07:06]  Razor: an arcane scroll : 3
[10/14/2025 07:06]  [Razor]: Added 1284943146 to ignore list
[10/14/2025 07:06]  Razor: dullhide skinning map (undeciphered)
[10/14/2025 07:06]  [Razor]: Added 1285255391 to ignore list
[10/14/2025 07:06]  : [locked down]
[10/14/2025 07:06]  System: Lockdowns Used: 831 / 875
[10/14/2025 07:06]  System: You deposit 35,232 gold into your bank box.
[10/14/2025 07:06]  Razor: Done.
[10/14/2025 07:06]  an orc: an orc
[10/14/2025 07:06]  an eagle: an eagle
[10/14/2025 07:07]  System: Architectus has completed the achievement: Scientist (Basic).
[10/14/2025 07:08]  a black bear: a black bear
[10/14/2025 07:08]  a forest ostard: a forest ostard
[10/14/2025 07:09]  GawdHammer: -1
[10/14/2025 07:09]  System: You now have 0 trapped pouches remaining.
[10/14/2025 07:09]  You see: a storage shelf
[10/14/2025 07:09]  You see: [secure]
[10/14/2025 07:09]  System: Resupply complete.
[10/14/2025 07:09]  a cougar: a cougar
[10/14/2025 07:09]  System: Opening door...
[10/14/2025 07:09]  System: As the owner, you refresh the house and its contents.
[10/14/2025 07:09]  a crow: a crow
[10/14/2025 07:09]  GawdHammer: Kal Ort Por
[10/14/2025 07:10]  System: One or more of your pack animals is not allowed in this location.
[10/14/2025 07:10]  You see: a stonescale tribal corpse
[10/14/2025 07:10]  You see: a stonescale tribal corpse
[10/14/2025 07:10]  You see: a monitor corpse
[10/14/2025 07:10]  a drake whelp: a drake whelp
[10/14/2025 07:10]  System: Your skill in Tactics has increased by 78.0.  It is now 78.0.
[10/14/2025 07:10]  You see: a dragon whelp corpse
[10/14/2025 07:10]  a monitor: a monitor
[10/14/2025 07:10]  You see: a drake whelp corpse
[10/14/2025 07:10]  You see: a drake whelp corpse
[10/14/2025 07:10]  a monitor: a monitor
[10/14/2025 07:10]  a monitor: a monitor
[10/14/2025 07:10]  You see: a primordial whelp corpse
[10/14/2025 07:10]  Atlanta: [Initiate, who]
[10/14/2025 07:10]  Atlanta: Atlanta
[10/14/2025 07:10]  a dragon whelp: a dragon whelp
[10/14/2025 07:10]  You see: a primordial whelp corpse
[10/14/2025 07:10]  an army ant: an army ant
[10/14/2025 07:10]  You see: an earth drake corpse
[10/14/2025 07:10]  You see: a drake corpse
[10/14/2025 07:10]  You see: an army ant corpse
[10/14/2025 07:10]  an earth drake: an earth drake
[10/14/2025 07:10]  Labyrinthh: [Wraith, Hex]
[10/14/2025 07:10]  Labyrinthh: Labyrinthh
[10/14/2025 07:10]  a lich: (summoned)
[10/14/2025 07:10]  a lich: a lich
[10/14/2025 07:10]  a lich: (summoned)
[10/14/2025 07:10]  a lich: a lich
[10/14/2025 07:10]  a skeletal knight: (summoned)
[10/14/2025 07:10]  a skeletal knight: a skeletal knight
[10/14/2025 07:10]  a drake: [Lesser Poison]
[10/14/2025 07:10]  a drake: a drake
[10/14/2025 07:10]  a drake: a drake
[10/14/2025 07:10]  You see: an earth drake corpse
[10/14/2025 07:10]  You see: a drake corpse
[10/14/2025 07:10]  a drake: *looks violently ill*
[10/14/2025 07:10]  You see: a drake corpse
[10/14/2025 07:10]  You see: an earth drake corpse
[10/14/2025 07:10]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:10]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:10]  [Razor]: Steps: 0
[10/14/2025 07:10]  [Razor]: Steps: 0
[10/14/2025 07:10]  System: You are no longer hindered.
[10/14/2025 07:10]  a drake: *looks violently ill*
[10/14/2025 07:10]  System: You have 29 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 29
[10/14/2025 07:10]  System: You have 28 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 28
[10/14/2025 07:10]  System: You have 27 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 27
[10/14/2025 07:10]  an earth drake: an earth drake
[10/14/2025 07:10]  System: You have 26 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 26
[10/14/2025 07:10]  System: You have 25 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 25
[10/14/2025 07:10]  System: You have 24 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 24
[10/14/2025 07:10]  System: You have 23 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 23
[10/14/2025 07:10]  System: You have 22 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 22
[10/14/2025 07:10]  [Razor]: New target set.
[10/14/2025 07:10]  Razor: Target: an earth drake
[10/14/2025 07:10]  System: You have 21 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 21
[10/14/2025 07:10]  System: You have 20 stealth steps remaining.
[10/14/2025 07:10]  Razor: Attack: an earth drake
[10/14/2025 07:10]  [Razor]: Steps: 20
[10/14/2025 07:10]  System: You have 19 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 19
[10/14/2025 07:10]  System: You have 18 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 18
[10/14/2025 07:10]  System: You have 17 stealth steps remaining.
[10/14/2025 07:10]  System: You strike your target from the shadows!
[10/14/2025 07:10]  GawdHammer: You steal 216 gold coin.
[10/14/2025 07:10]  System: You steal 216 gold coin.
[10/14/2025 07:10]  GawdHammer: (Shadow Aspect 65,652.6/250000 xp)
[10/14/2025 07:10]  System: You have lost a little karma.
[10/14/2025 07:10]  System: You have gained a lot of karma.
[10/14/2025 07:10]  GawdHammer: (Shadow Aspect 65,671.2/250000 xp)
[10/14/2025 07:10]  System: -6000
[10/14/2025 07:10]  You see: an earth drake corpse
[10/14/2025 07:10]  System: Grab Bag not found, setting to Backpack.
[10/14/2025 07:10]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:10]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 07:10]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:10]  Labyrinthh: [Wraith, Hex]
[10/14/2025 07:10]  Labyrinthh: Labyrinthh
[10/14/2025 07:10]  a ghoul: (summoned)
[10/14/2025 07:10]  a ghoul: a ghoul
[10/14/2025 07:10]  a lich: (summoned)
[10/14/2025 07:10]  a lich: a lich
[10/14/2025 07:10]  GawdHammer: -17
[10/14/2025 07:10]  a drake: [Lesser Poison]
[10/14/2025 07:10]  a drake: a drake
[10/14/2025 07:10]  a drake: a drake
[10/14/2025 07:10]  GawdHammer: +17
[10/14/2025 07:10]  System: some damage has been healed : 17
[10/14/2025 07:10]  System: You drink a healing potion.
[10/14/2025 07:10]  You see: a drake corpse
[10/14/2025 07:10]  You see: a drake corpse
[10/14/2025 07:10]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:10]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:10]  [Razor]: New target set.
[10/14/2025 07:10]  Razor: Target: an earth drake
[10/14/2025 07:10]  a lich: (summoned)
[10/14/2025 07:10]  a lich: a lich
[10/14/2025 07:10]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:10]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:10]  Razor: Attack: an earth drake
[10/14/2025 07:10]  System: You have 23 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 23
[10/14/2025 07:10]  System: You have 22 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 22
[10/14/2025 07:10]  System: You have 21 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 21
[10/14/2025 07:10]  System: You have 20 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 20
[10/14/2025 07:10]  System: You have 19 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 19
[10/14/2025 07:10]  System: You have 18 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 18
[10/14/2025 07:10]  System: You have 17 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 17
[10/14/2025 07:10]  System: You have 16 stealth steps remaining.
[10/14/2025 07:10]  [Razor]: Steps: 16
[10/14/2025 07:10]  System: You have 15 stealth steps remaining.
[10/14/2025 07:10]  System: You strike your target from the shadows!
[10/14/2025 07:10]  GawdHammer: You steal 221 gold coin.
[10/14/2025 07:10]  System: You steal 221 gold coin.
[10/14/2025 07:10]  GawdHammer: (Shadow Aspect 65,675.8/250000 xp)
[10/14/2025 07:10]  System: You have lost a little karma.
[10/14/2025 07:10]  System: You have gained a lot of karma.
[10/14/2025 07:10]  GawdHammer: (Shadow Aspect 65,694.4/250000 xp)
[10/14/2025 07:10]  System: -6000
[10/14/2025 07:10]  You see: an earth drake corpse
[10/14/2025 07:10]  a lich: (summoned)
[10/14/2025 07:10]  a lich: a lich
[10/14/2025 07:10]  a drake: a drake
[10/14/2025 07:10]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:10]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:10]  You see: a drake corpse
[10/14/2025 07:10]  a drake: a drake
[10/14/2025 07:10]  a drake: a drake
[10/14/2025 07:10]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:10]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:11]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:11]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:11]  System: You have 23 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 23
[10/14/2025 07:11]  System: You have 22 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 22
[10/14/2025 07:11]  System: You have 21 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: New target set.
[10/14/2025 07:11]  Razor: Target: a drake
[10/14/2025 07:11]  [Razor]: Steps: 21
[10/14/2025 07:11]  System: You have 20 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 20
[10/14/2025 07:11]  System: You have 19 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 19
[10/14/2025 07:11]  System: You have 18 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 18
[10/14/2025 07:11]  System: You have 17 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 17
[10/14/2025 07:11]  You see: a drake corpse
[10/14/2025 07:11]  System: You have 16 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 16
[10/14/2025 07:11]  System: You have 15 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 15
[10/14/2025 07:11]  System: You have 14 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 14
[10/14/2025 07:11]  Razor: Attack: a drake
[10/14/2025 07:11]  System: You will automatically stealth when out of steps.
[10/14/2025 07:11]  Razor: [Auto-stealth ready.]
[10/14/2025 07:11]  System: You strike your target from the shadows!
[10/14/2025 07:11]  GawdHammer: You steal 198 gold coin.
[10/14/2025 07:11]  System: You steal 198 gold coin.
[10/14/2025 07:11]  GawdHammer: (Shadow Aspect 65,698.5/250000 xp)
[10/14/2025 07:11]  System: You have lost a little karma.
[10/14/2025 07:11]  System: You have gained a lot of karma.
[10/14/2025 07:11]  GawdHammer: (Shadow Aspect 65,715.0/250000 xp)
[10/14/2025 07:11]  System: -6000
[10/14/2025 07:11]  You see: a drake corpse
[10/14/2025 07:11]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:11]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:11]  System: You may now use another smoke bomb.
[10/14/2025 07:11]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:11]  [Razor]: New target set.
[10/14/2025 07:11]  Razor: Target: a drake
[10/14/2025 07:11]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:11]  System: You have 23 stealth steps remaining.
[10/14/2025 07:11]  [Razor]: Steps: 23
[10/14/2025 07:11]  Razor: Attack: a drake
[10/14/2025 07:11]  System: You have 22 stealth steps remaining.
[10/14/2025 07:11]  System: You strike your target from the shadows!
[10/14/2025 07:11]  GawdHammer: You steal 209 gold coin.
[10/14/2025 07:11]  System: You steal 209 gold coin.
[10/14/2025 07:11]  GawdHammer: (Shadow Aspect 65,719.1/250000 xp)
[10/14/2025 07:11]  System: You have lost a little karma.
[10/14/2025 07:11]  System: You have gained a lot of karma.
[10/14/2025 07:11]  GawdHammer: (Shadow Aspect 65,735.5/250000 xp)
[10/14/2025 07:11]  System: -6000
[10/14/2025 07:11]  You see: a drake corpse
[10/14/2025 07:11]  System: That is too far away.
[10/14/2025 07:11]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:11]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:11]  You see: an earth drake corpse
[10/14/2025 07:11]  You see: a drake corpse
[10/14/2025 07:11]  KaaPow: [Initiate, who]
[10/14/2025 07:11]  KaaPow: KaaPow
[10/14/2025 07:11]  a drake: a drake
[10/14/2025 07:11]  You see: an adder corpse
[10/14/2025 07:11]  You see: an adder corpse
[10/14/2025 07:11]  You see: a smoke drake corpse
[10/14/2025 07:11]  You see: a smoke drake corpse
[10/14/2025 07:11]  Glow: Lady Glow
[10/14/2025 07:11]  a water elemental: (summoned)
[10/14/2025 07:11]  a water elemental: a water elemental
[10/14/2025 07:11]  a fire elemental: (summoned)
[10/14/2025 07:11]  a fire elemental: a fire elemental
[10/14/2025 07:11]  a smoke drake: [Greater Poison]
[10/14/2025 07:11]  a smoke drake: a smoke drake
[10/14/2025 07:11]  a rock guar: (summoned)
[10/14/2025 07:11]  a rock guar: a rock guar
[10/14/2025 07:11]  You see: a smoke drake corpse
[10/14/2025 07:11]  an eldritch drake: an eldritch drake
[10/14/2025 07:12]  an eldritch drake: an eldritch drake
[10/14/2025 07:12]  an eldritch drake: an eldritch drake
[10/14/2025 07:12]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:12]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:12]  a smoke drake: [Greater Poison]
[10/14/2025 07:12]  a smoke drake: a smoke drake
[10/14/2025 07:12]  Glow: Por Corp Wis
[10/14/2025 07:12]  You see: a smoke drake corpse
[10/14/2025 07:12]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:12]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:12]  System: You have 23 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 23
[10/14/2025 07:12]  System: You have 22 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 22
[10/14/2025 07:12]  You see: an eldritch drake corpse
[10/14/2025 07:12]  System: You have 21 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 21
[10/14/2025 07:12]  System: You have 20 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 20
[10/14/2025 07:12]  [Razor]: New target set.
[10/14/2025 07:12]  Razor: Target: an eldritch drake
[10/14/2025 07:12]  System: You have 19 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 19
[10/14/2025 07:12]  System: You have 18 stealth steps remaining.
[10/14/2025 07:12]  a fire elemental: (summoned)
[10/14/2025 07:12]  a fire elemental: a fire elemental
[10/14/2025 07:12]  [Razor]: Steps: 18
[10/14/2025 07:12]  You see: an eldritch drake corpse
[10/14/2025 07:12]  System: You have 17 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 17
[10/14/2025 07:12]  System: You have 16 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 16
[10/14/2025 07:12]  System: You have 15 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 15
[10/14/2025 07:12]  System: You have 14 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 14
[10/14/2025 07:12]  System: You have 13 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 13
[10/14/2025 07:12]  You see: an eldritch drake corpse
[10/14/2025 07:12]  Razor: Attack: an eldritch drake
[10/14/2025 07:12]  System: You strike your target from the shadows!
[10/14/2025 07:12]  GawdHammer: You steal 327 gold coin.
[10/14/2025 07:12]  System: You steal 327 gold coin.
[10/14/2025 07:12]  GawdHammer: (Shadow Aspect 65,741.8/250000 xp)
[10/14/2025 07:12]  System: You have lost a little karma.
[10/14/2025 07:12]  System: You have gained a very large amount of karma.
[10/14/2025 07:12]  GawdHammer: (Shadow Aspect 65,767.1/250000 xp)
[10/14/2025 07:12]  System: -6000
[10/14/2025 07:12]  You see: an eldritch drake corpse
[10/14/2025 07:12]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:12]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:12]  System: Rokugan has completed the achievement: Connoisseur (Advanced).
[10/14/2025 07:12]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:12]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:12]  System: You have 23 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 23
[10/14/2025 07:12]  System: You have 22 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 22
[10/14/2025 07:12]  System: You have 21 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 21
[10/14/2025 07:12]  System: You have 20 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 20
[10/14/2025 07:12]  System: You have 19 stealth steps remaining.
[10/14/2025 07:12]  [Razor]: Steps: 19
[10/14/2025 07:12]  [Razor]: New target set.
[10/14/2025 07:12]  Razor: Target: an eldritch drake
[10/14/2025 07:12]  System: You have 18 stealth steps remaining.
[10/14/2025 07:12]  Razor: Attack: an eldritch drake
[10/14/2025 07:12]  [Razor]: Steps: 18
[10/14/2025 07:12]  System: You strike your target from the shadows!
[10/14/2025 07:12]  GawdHammer: You steal 327 gold coin.
[10/14/2025 07:12]  System: You steal 327 gold coin.
[10/14/2025 07:12]  GawdHammer: (Shadow Aspect 65,773.4/250000 xp)
[10/14/2025 07:12]  System: You have lost a little karma.
[10/14/2025 07:12]  System: You have gained a very large amount of karma.
[10/14/2025 07:12]  GawdHammer: (Shadow Aspect 65,797.9/250000 xp)
[10/14/2025 07:12]  System: -5819
[10/14/2025 07:12]  You see: an eldritch drake corpse
[10/14/2025 07:12]  Glow: All Kill
[10/14/2025 07:12]  Glow: In Por Ylem
[10/14/2025 07:12]  an eldritch drake: *warded*
[10/14/2025 07:12]  Glow: In Por Ylem
[10/14/2025 07:12]  Glow: An Mani
[10/14/2025 07:12]  Glow: Por Ort Grav
[10/14/2025 07:12]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:12]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:12]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 07:12]  Glow: Des Sanct
[10/14/2025 07:12]  [Razor]: Queuing dragdrop request 1... 1.2 seconds left.
[10/14/2025 07:12]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 07:12]  Glow: In Nox
[10/14/2025 07:12]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:12]  Glow: Kal Vas Flam
[10/14/2025 07:12]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:12]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:12]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:12]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:12]  Glow: In Mani
[10/14/2025 07:12]  Glow: Kal Vas Flam
[10/14/2025 07:12]  Glow: Kal Vas Flam
[10/14/2025 07:12]  an eldritch drake: *warded*
[10/14/2025 07:12]  Glow: Kal Vas Flam
[10/14/2025 07:12]  an ember drake: an ember drake
[10/14/2025 07:13]  Glow: Por Corp Wis
[10/14/2025 07:13]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:13]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:13]  Glow: Por Corp Wis
[10/14/2025 07:13]  System: Target the container to Grab items into.
[10/14/2025 07:13]  Glow: Por Corp Wis
[10/14/2025 07:13]  System: Grab Bag set: 1285803930
[10/14/2025 07:13]  Glow: Por Corp Wis
[10/14/2025 07:13]  Glow: Por Corp Wis
[10/14/2025 07:13]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:13]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:13]  System: You will automatically stealth when out of steps.
[10/14/2025 07:13]  Razor: [Auto-stealth ready.]
[10/14/2025 07:13]  System: You have 23 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 23
[10/14/2025 07:13]  System: You have 22 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: New target set.
[10/14/2025 07:13]  Razor: Target: an ember drake
[10/14/2025 07:13]  [Razor]: Steps: 22
[10/14/2025 07:13]  Razor: Attack: an ember drake
[10/14/2025 07:13]  System: You have 21 stealth steps remaining.
[10/14/2025 07:13]  System: You strike your target from the shadows!
[10/14/2025 07:13]  GawdHammer: You steal 216 gold coin.
[10/14/2025 07:13]  System: You steal 216 gold coin.
[10/14/2025 07:13]  GawdHammer: (Shadow Aspect 65,802.3/250000 xp)
[10/14/2025 07:13]  System: You have lost a little karma.
[10/14/2025 07:13]  System: You have gained a lot of karma.
[10/14/2025 07:13]  GawdHammer: (Shadow Aspect 65,820.2/250000 xp)
[10/14/2025 07:13]  System: -6000
[10/14/2025 07:13]  You see: an ember drake corpse
[10/14/2025 07:13]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:13]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:13]  You see: an eldritch drake corpse
[10/14/2025 07:13]  You see: an eldritch drake corpse
[10/14/2025 07:13]  You see: an eldritch drake corpse
[10/14/2025 07:13]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:13]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:13]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:13]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:13]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:13]  [Razor]: Steps: 0
[10/14/2025 07:13]  System: You are no longer hindered.
[10/14/2025 07:13]  System: You have 29 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 29
[10/14/2025 07:13]  System: You have 28 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 28
[10/14/2025 07:13]  System: You have 27 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 27
[10/14/2025 07:13]  an earth dragon: an earth dragon
[10/14/2025 07:13]  System: You have 26 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 26
[10/14/2025 07:13]  System: You have 25 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 25
[10/14/2025 07:13]  System: You have 24 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 24
[10/14/2025 07:13]  System: You have 23 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 23
[10/14/2025 07:13]  [Razor]: New target set.
[10/14/2025 07:13]  Razor: Target: an ophidian dragonguard
[10/14/2025 07:13]  System: You have 22 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 22
[10/14/2025 07:13]  System: You have 21 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 21
[10/14/2025 07:13]  System: You have 20 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 20
[10/14/2025 07:13]  System: You have 19 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 19
[10/14/2025 07:13]  System: You have 18 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 18
[10/14/2025 07:13]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:13]  System: You have 17 stealth steps remaining.
[10/14/2025 07:13]  [Razor]: Steps: 17
[10/14/2025 07:13]  Razor: Attack: an ophidian dragonguard
[10/14/2025 07:13]  System: You strike your target from the shadows!
[10/14/2025 07:13]  GawdHammer: You steal 286 gold coin.
[10/14/2025 07:13]  System: You steal 286 gold coin.
[10/14/2025 07:13]  GawdHammer: (Shadow Aspect 65,825.8/250000 xp)
[10/14/2025 07:13]  System: You have lost a little karma.
[10/14/2025 07:13]  System: You have gained a very large amount of karma.
[10/14/2025 07:13]  GawdHammer: (Shadow Aspect 65,848.1/250000 xp)
[10/14/2025 07:13]  System: -8000
[10/14/2025 07:13]  You see: an ophidian dragonguard corpse
[10/14/2025 07:13]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:13]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:13]  [Razor]: New target set.
[10/14/2025 07:13]  Razor: Target: an ophidian dragonguard
[10/14/2025 07:14]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:14]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:14]  System: You have 23 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 23
[10/14/2025 07:14]  System: You have 22 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 22
[10/14/2025 07:14]  System: You have 21 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 21
[10/14/2025 07:14]  System: You have 20 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 20
[10/14/2025 07:14]  an earth dragon: an earth dragon
[10/14/2025 07:14]  System: You have 19 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 19
[10/14/2025 07:14]  System: You have 18 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 18
[10/14/2025 07:14]  System: You have 17 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 17
[10/14/2025 07:14]  System: You have 16 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 16
[10/14/2025 07:14]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:14]  System: You have 15 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 15
[10/14/2025 07:14]  Razor: Attack: an ophidian dragonguard
[10/14/2025 07:14]  System: You strike your target from the shadows!
[10/14/2025 07:14]  GawdHammer: You steal 280 gold coin.
[10/14/2025 07:14]  System: You steal 280 gold coin.
[10/14/2025 07:14]  GawdHammer: (Shadow Aspect 65,853.7/250000 xp)
[10/14/2025 07:14]  System: You have lost a little karma.
[10/14/2025 07:14]  System: You have gained a very large amount of karma.
[10/14/2025 07:14]  GawdHammer: (Shadow Aspect 65,876.1/250000 xp)
[10/14/2025 07:14]  System: -8000
[10/14/2025 07:14]  You see: an ophidian dragonguard corpse
[10/14/2025 07:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:14]  : stalagmites
[10/14/2025 07:14]  : stalagmites
[10/14/2025 07:14]  System: Ynorev has completed the achievement: Beastial Slayer (Basic).
[10/14/2025 07:14]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:14]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:14]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:14]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:14]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:14]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:14]  You see: an ophidian shaman corpse
[10/14/2025 07:14]  an earth dragon: an earth dragon
[10/14/2025 07:14]  You see: an ophidian dragonguard corpse
[10/14/2025 07:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:14]  You see: an ophidian shaman corpse
[10/14/2025 07:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:14]  a wyvern: a wyvern
[10/14/2025 07:14]  You see: a wyvern corpse
[10/14/2025 07:14]  You see: a wyvern corpse
[10/14/2025 07:14]  a wyvern: a wyvern
[10/14/2025 07:14]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:14]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:14]  [Razor]: New target set.
[10/14/2025 07:14]  Razor: Target: a wyvern
[10/14/2025 07:14]  System: You may now use another smoke bomb.
[10/14/2025 07:14]  a wyvern: a wyvern
[10/14/2025 07:14]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:14]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:14]  System: You have 23 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 23
[10/14/2025 07:14]  You see: a wyvern corpse
[10/14/2025 07:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:14]  System: You have 22 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 22
[10/14/2025 07:14]  System: You have 21 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 21
[10/14/2025 07:14]  System: You have 20 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 20
[10/14/2025 07:14]  System: You have 19 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 19
[10/14/2025 07:14]  System: You have 18 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 18
[10/14/2025 07:14]  Razor: Attack: a wyvern
[10/14/2025 07:14]  System: You have 17 stealth steps remaining.
[10/14/2025 07:14]  System: You strike your target from the shadows!
[10/14/2025 07:14]  GawdHammer: You steal 256 gold coin.
[10/14/2025 07:14]  System: You steal 256 gold coin.
[10/14/2025 07:14]  GawdHammer: (Shadow Aspect 65,880.9/250000 xp)
[10/14/2025 07:14]  System: You have lost a little karma.
[10/14/2025 07:14]  a wyvern: -7963
[10/14/2025 07:14]  You see: an ophidian dragonguard corpse
[10/14/2025 07:14]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:14]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:14]  [Razor]: Steps: 0
[10/14/2025 07:14]  System: You are no longer hindered.
[10/14/2025 07:14]  System: You have 29 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 29
[10/14/2025 07:14]  [Razor]: New target set.
[10/14/2025 07:14]  System: You have 28 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 28
[10/14/2025 07:14]  a wyvern: a wyvern
[10/14/2025 07:14]  System: You have 27 stealth steps remaining.
[10/14/2025 07:14]  [Razor]: Steps: 27
[10/14/2025 07:14]  You see: a wyvern corpse
[10/14/2025 07:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:15]  System: You have 26 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 26
[10/14/2025 07:15]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:15]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:15]  System: You have 25 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 25
[10/14/2025 07:15]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:15]  System: You will automatically stealth when out of steps.
[10/14/2025 07:15]  Razor: [Auto-stealth ready.]
[10/14/2025 07:15]  System: You have 24 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 24
[10/14/2025 07:15]  System: You have 23 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 23
[10/14/2025 07:15]  System: You have 22 stealth steps remaining.
[10/14/2025 07:15]  System: You strike your target from the shadows!
[10/14/2025 07:15]  System: You have already stolen from this creature.
[10/14/2025 07:15]  System: You have gained a lot of karma.
[10/14/2025 07:15]  GawdHammer: (Shadow Aspect 65,900.2/250000 xp)
[10/14/2025 07:15]  System: -37
[10/14/2025 07:15]  You see: a wyvern corpse
[10/14/2025 07:15]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:15]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:15]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:15]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:15]  System: You have 23 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 23
[10/14/2025 07:15]  System: You have 22 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 22
[10/14/2025 07:15]  a wyvern: a wyvern
[10/14/2025 07:15]  System: You have 21 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 21
[10/14/2025 07:15]  System: You have 20 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 20
[10/14/2025 07:15]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:15]  System: You have 19 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 19
[10/14/2025 07:15]  You see: a wyvern corpse
[10/14/2025 07:15]  System: You have 18 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 18
[10/14/2025 07:15]  System: You have 17 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 17
[10/14/2025 07:15]  System: You have 16 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 16
[10/14/2025 07:15]  System: You have 15 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 15
[10/14/2025 07:15]  System: You have 14 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 14
[10/14/2025 07:15]  System: You have 13 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 13
[10/14/2025 07:15]  System: You have 12 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: New target set.
[10/14/2025 07:15]  Razor: Target: a wyvern
[10/14/2025 07:15]  [Razor]: Steps: 12
[10/14/2025 07:15]  System: You have 11 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 11
[10/14/2025 07:15]  System: You have 10 stealth steps remaining.
[10/14/2025 07:15]  Razor: [10 Steps...]
[10/14/2025 07:15]  [Razor]: Steps: 10
[10/14/2025 07:15]  System: You have 9 stealth steps remaining.
[10/14/2025 07:15]  Razor: [9 Steps...]
[10/14/2025 07:15]  [Razor]: Steps: 9
[10/14/2025 07:15]  Razor: Attack: a wyvern
[10/14/2025 07:15]  System: You strike your target from the shadows!
[10/14/2025 07:15]  System: Society Job Progress: 4/10 [Steal 10 Black Goods].
[10/14/2025 07:15]  GawdHammer: You steal 265 gold coin.
[10/14/2025 07:15]  System: You steal 265 gold coin.
[10/14/2025 07:15]  GawdHammer: You steal common black goods.
[10/14/2025 07:15]  System: You steal common black goods.
[10/14/2025 07:15]  GawdHammer: (Shadow Aspect 65,905.0/250000 xp)
[10/14/2025 07:15]  System: You have lost a little karma.
[10/14/2025 07:15]  a wyvern: -6963
[10/14/2025 07:15]  You see: an ophidian dragonguard corpse
[10/14/2025 07:15]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:15]  System: DeathShade has completed the achievement: Relic Hunter (Epic).
[10/14/2025 07:15]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:15]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:15]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:15]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:15]  System: You have 23 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 23
[10/14/2025 07:15]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:15]  System: You have 22 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 22
[10/14/2025 07:15]  System: You have 21 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 21
[10/14/2025 07:15]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:15]  System: You have 20 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 20
[10/14/2025 07:15]  System: You have 19 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 19
[10/14/2025 07:15]  System: You have 18 stealth steps remaining.
[10/14/2025 07:15]  [Razor]: Steps: 18
[10/14/2025 07:15]  System: You have 17 stealth steps remaining.
[10/14/2025 07:15]  System: You strike your target from the shadows!
[10/14/2025 07:15]  System: You have already stolen from this creature.
[10/14/2025 07:15]  System: You have gained a lot of karma.
[10/14/2025 07:15]  GawdHammer: (Shadow Aspect 65,924.3/250000 xp)
[10/14/2025 07:15]  System: -1037
[10/14/2025 07:15]  You see: a wyvern corpse
[10/14/2025 07:15]  You see: a wyvern corpse
[10/14/2025 07:15]  You see: a wyvern corpse
[10/14/2025 07:15]  a wyvern: a wyvern
[10/14/2025 07:15]  System: You may now use another smoke bomb.
[10/14/2025 07:15]  a wyvern: a wyvern
[10/14/2025 07:15]  You see: a wyvern corpse
[10/14/2025 07:15]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:15]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:15]  [Razor]: New target set.
[10/14/2025 07:15]  Razor: Target: a wyvern
[10/14/2025 07:16]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:16]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:16]  System: You have 23 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 23
[10/14/2025 07:16]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:16]  System: You have 22 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 22
[10/14/2025 07:16]  System: You have 21 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 21
[10/14/2025 07:16]  Razor: Attack: a wyvern
[10/14/2025 07:16]  System: You have 20 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 20
[10/14/2025 07:16]  System: You have 19 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 19
[10/14/2025 07:16]  System: You have 18 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 18
[10/14/2025 07:16]  System: You have 17 stealth steps remaining.
[10/14/2025 07:16]  System: You strike your target from the shadows!
[10/14/2025 07:16]  System: You fail to steal from the creature.
[10/14/2025 07:16]  System: You have lost a little karma.
[10/14/2025 07:16]  a wyvern: -7523
[10/14/2025 07:16]  You see: a wyvern corpse
[10/14/2025 07:16]  You see: a wyvern corpse
[10/14/2025 07:16]  You see: an ophidian dragonguard corpse
[10/14/2025 07:16]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:16]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:16]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:16]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:16]  System: You are no longer hindered.
[10/14/2025 07:16]  System: You have 29 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 29
[10/14/2025 07:16]  You see: a wyvern corpse
[10/14/2025 07:16]  You see: a wyvern corpse
[10/14/2025 07:16]  System: You have 28 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 28
[10/14/2025 07:16]  System: You have 27 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 27
[10/14/2025 07:16]  System: You have 26 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 26
[10/14/2025 07:16]  System: You have 25 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 25
[10/14/2025 07:16]  System: You have 24 stealth steps remaining.
[10/14/2025 07:16]  System: You strike your target from the shadows!
[10/14/2025 07:16]  GawdHammer: You steal 244 gold coin.
[10/14/2025 07:16]  System: You steal 244 gold coin.
[10/14/2025 07:16]  GawdHammer: (Shadow Aspect 65,929.1/250000 xp)
[10/14/2025 07:16]  System: You have lost a little karma.
[10/14/2025 07:16]  System: You have gained a lot of karma.
[10/14/2025 07:16]  GawdHammer: (Shadow Aspect 65,948.4/250000 xp)
[10/14/2025 07:16]  System: -477
[10/14/2025 07:16]  You see: a wyvern corpse
[10/14/2025 07:16]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:16]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:16]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:16]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:16]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:16]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:16]  [Razor]: New target set.
[10/14/2025 07:16]  Razor: Target: a cave dracolisk
[10/14/2025 07:16]  System: The world will save in 15 seconds.
[10/14/2025 07:16]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:16]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:16]  System: You have 23 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 23
[10/14/2025 07:16]  System: You have 22 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 22
[10/14/2025 07:16]  System: You have 21 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 21
[10/14/2025 07:16]  System: You have 20 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 20
[10/14/2025 07:16]  System: You have 19 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 19
[10/14/2025 07:16]  System: You have 18 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 18
[10/14/2025 07:16]  System: You have 17 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 17
[10/14/2025 07:16]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:16]  a harvestman: a harvestman
[10/14/2025 07:16]  System: You have 16 stealth steps remaining.
[10/14/2025 07:16]  [Razor]: Steps: 16
[10/14/2025 07:16]  a harvestman: a harvestman
[10/14/2025 07:16]  System: The world is saving, please wait.
[10/14/2025 07:16]  System: World save complete. The entire process took 3.4 seconds.
[10/14/2025 07:16]  Razor: Attack: a cave dracolisk
[10/14/2025 07:16]  System: You will automatically stealth when out of steps.
[10/14/2025 07:16]  Razor: [Auto-stealth ready.]
[10/14/2025 07:16]  System: You strike your target from the shadows!
[10/14/2025 07:16]  GawdHammer: You steal 278 gold coin.
[10/14/2025 07:16]  System: You steal 278 gold coin.
[10/14/2025 07:16]  GawdHammer: (Shadow Aspect 65,954.1/250000 xp)
[10/14/2025 07:16]  System: You have lost a little karma.
[10/14/2025 07:16]  System: You have gained a very large amount of karma.
[10/14/2025 07:16]  GawdHammer: (Shadow Aspect 65,977.0/250000 xp)
[10/14/2025 07:16]  System: -8000
[10/14/2025 07:17]  You see: a cave dracolisk corpse
[10/14/2025 07:17]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:17]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:17]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:17]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:17]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:17]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:17]  System: You have 23 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 23
[10/14/2025 07:17]  System: You have 22 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 22
[10/14/2025 07:17]  System: You have 21 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 21
[10/14/2025 07:17]  System: You have 20 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 20
[10/14/2025 07:17]  a harvestman: a harvestman
[10/14/2025 07:17]  System: You have 19 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 19
[10/14/2025 07:17]  System: You have 18 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 18
[10/14/2025 07:17]  System: You have 17 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: New target set.
[10/14/2025 07:17]  Razor: Target: a harvestman
[10/14/2025 07:17]  [Razor]: Steps: 17
[10/14/2025 07:17]  System: You have 16 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 16
[10/14/2025 07:17]  Keittovelho: [Hostile Juggernaut, HOST]
[10/14/2025 07:17]  Keittovelho: Lord Keittovelho
[10/14/2025 07:17]  System: You have 15 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 15
[10/14/2025 07:17]  a forktongue shocktrooper: a forktongue shocktrooper
[10/14/2025 07:17]  System: You have 14 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 14
[10/14/2025 07:17]  a harvestman: a harvestman
[10/14/2025 07:17]  System: You have 13 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 13
[10/14/2025 07:17]  System: You may now use another smoke bomb.
[10/14/2025 07:17]  You see: a forktongue shocktrooper corpse
[10/14/2025 07:17]  Razor: Attack: a harvestman
[10/14/2025 07:17]  System: You strike your target from the shadows!
[10/14/2025 07:17]  GawdHammer: You steal 256 gold coin.
[10/14/2025 07:17]  System: You steal 256 gold coin.
[10/14/2025 07:17]  GawdHammer: (Shadow Aspect 65,982.1/250000 xp)
[10/14/2025 07:17]  System: You have lost a little karma.
[10/14/2025 07:17]  System: You have gained a very large amount of karma.
[10/14/2025 07:17]  GawdHammer: (Shadow Aspect 66,002.6/250000 xp)
[10/14/2025 07:17]  System: -6000
[10/14/2025 07:17]  You see: a harvestman corpse
[10/14/2025 07:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:17]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:17]  [Razor]: New target set.
[10/14/2025 07:17]  Razor: Target: a cave dracolisk
[10/14/2025 07:17]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:17]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:17]  System: You have 23 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 23
[10/14/2025 07:17]  System: You have 22 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 22
[10/14/2025 07:17]  a harvestman: a harvestman
[10/14/2025 07:17]  System: You have 21 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 21
[10/14/2025 07:17]  System: LootEater has completed the achievement: Wilderness Hunter (Intermediate).
[10/14/2025 07:17]  System: You have 20 stealth steps remaining.
[10/14/2025 07:17]  [Razor]: Steps: 20
[10/14/2025 07:17]  Razor: Attack: a cave dracolisk
[10/14/2025 07:17]  System: You strike your target from the shadows!
[10/14/2025 07:17]  System: You fail to steal from the creature.
[10/14/2025 07:17]  System: You have lost a little karma.
[10/14/2025 07:17]  System: You have gained a very large amount of karma.
[10/14/2025 07:17]  GawdHammer: (Shadow Aspect 66,025.5/250000 xp)
[10/14/2025 07:17]  System: -8000
[10/14/2025 07:17]  You see: a cave dracolisk corpse
[10/14/2025 07:17]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:17]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:17]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:17]  You see: a harvestman corpse
[10/14/2025 07:17]  a harvestman: a harvestman
[10/14/2025 07:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:17]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:17]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 07:17]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:17]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:17]  System: You have 23 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 23
[10/14/2025 07:18]  System: You have 22 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 22
[10/14/2025 07:18]  System: You have 21 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 21
[10/14/2025 07:18]  System: You have 20 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 20
[10/14/2025 07:18]  System: You have 19 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 19
[10/14/2025 07:18]  System: You have 18 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 18
[10/14/2025 07:18]  System: You have 17 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 17
[10/14/2025 07:18]  System: You have 16 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 16
[10/14/2025 07:18]  System: You have 15 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 15
[10/14/2025 07:18]  [Razor]: New target set.
[10/14/2025 07:18]  Razor: Target: a cave dracolisk
[10/14/2025 07:18]  System: You will automatically stealth when out of steps.
[10/14/2025 07:18]  Razor: [Auto-stealth ready.]
[10/14/2025 07:18]  Razor: Attack: a cave dracolisk
[10/14/2025 07:18]  System: You strike your target from the shadows!
[10/14/2025 07:18]  GawdHammer: You steal 273 gold coin.
[10/14/2025 07:18]  System: You steal 273 gold coin.
[10/14/2025 07:18]  GawdHammer: (Shadow Aspect 66,031.2/250000 xp)
[10/14/2025 07:18]  System: You have lost a little karma.
[10/14/2025 07:18]  System: You have gained a very large amount of karma.
[10/14/2025 07:18]  GawdHammer: (Shadow Aspect 66,054.0/250000 xp)
[10/14/2025 07:18]  System: -8000
[10/14/2025 07:18]  You see: a cave dracolisk corpse
[10/14/2025 07:18]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:18]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:18]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:18]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:18]  System: You have 23 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 23
[10/14/2025 07:18]  System: You have 22 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 22
[10/14/2025 07:18]  System: You have 21 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 21
[10/14/2025 07:18]  System: You have 20 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: New target set.
[10/14/2025 07:18]  Razor: Target: a harvestman
[10/14/2025 07:18]  [Razor]: Steps: 20
[10/14/2025 07:18]  a dusk dragon: a dusk dragon
[10/14/2025 07:18]  System: You will automatically stealth when out of steps.
[10/14/2025 07:18]  Razor: [Auto-stealth ready.]
[10/14/2025 07:18]  Razor: Attack: a harvestman
[10/14/2025 07:18]  System: You strike your target from the shadows!
[10/14/2025 07:18]  GawdHammer: You steal 276 gold coin.
[10/14/2025 07:18]  System: You steal 276 gold coin.
[10/14/2025 07:18]  GawdHammer: (Shadow Aspect 66,059.2/250000 xp)
[10/14/2025 07:18]  System: You have lost a little karma.
[10/14/2025 07:18]  System: You have gained a very large amount of karma.
[10/14/2025 07:18]  GawdHammer: (Shadow Aspect 66,079.7/250000 xp)
[10/14/2025 07:18]  System: -6000
[10/14/2025 07:18]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:18]  You see: a harvestman corpse
[10/14/2025 07:18]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:18]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:18]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:18]  System: You have 23 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 23
[10/14/2025 07:18]  System: You have 22 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 22
[10/14/2025 07:18]  [Razor]: New target set.
[10/14/2025 07:18]  Razor: Target: a harvestman
[10/14/2025 07:18]  You see: a cave dracolisk corpse
[10/14/2025 07:18]  a dusk dragon: a dusk dragon
[10/14/2025 07:18]  System: You have 21 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 21
[10/14/2025 07:18]  System: You have 20 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 20
[10/14/2025 07:18]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:18]  Razor: Attack: a harvestman
[10/14/2025 07:18]  System: You have 19 stealth steps remaining.
[10/14/2025 07:18]  [Razor]: Steps: 19
[10/14/2025 07:18]  System: You have 18 stealth steps remaining.
[10/14/2025 07:18]  System: You strike your target from the shadows!
[10/14/2025 07:18]  GawdHammer: You steal 281 gold coin.
[10/14/2025 07:18]  System: You steal 281 gold coin.
[10/14/2025 07:18]  GawdHammer: (Shadow Aspect 66,084.8/250000 xp)
[10/14/2025 07:18]  System: You have lost a little karma.
[10/14/2025 07:18]  System: You have gained a very large amount of karma.
[10/14/2025 07:18]  GawdHammer: (Shadow Aspect 66,105.4/250000 xp)
[10/14/2025 07:18]  System: -6000
[10/14/2025 07:18]  You see: a harvestman corpse
[10/14/2025 07:18]  a dusk dragon: a dusk dragon
[10/14/2025 07:18]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:18]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:18]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:18]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:18]  You see: a cave dracolisk corpse
[10/14/2025 07:18]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:18]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:18]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:18]  a forktongue spinebreaker: a forktongue spinebreaker
[10/14/2025 07:19]  You see: a harvestman corpse
[10/14/2025 07:19]  a dusk dragon: a dusk dragon
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  a bonfire wisp: a bonfire wisp
[10/14/2025 07:19]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:19]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:19]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:19]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:19]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:19]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:19]  System: You have 23 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 23
[10/14/2025 07:19]  System: You have 22 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 22
[10/14/2025 07:19]  System: You have 21 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 21
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  System: You have 20 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 20
[10/14/2025 07:19]  System: You have 19 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 19
[10/14/2025 07:19]  System: You have 18 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 18
[10/14/2025 07:19]  System: You have 17 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 17
[10/14/2025 07:19]  [Razor]: New target set.
[10/14/2025 07:19]  Razor: Target: an ophidian shaman
[10/14/2025 07:19]  System: You have 16 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 16
[10/14/2025 07:19]  System: You have 15 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 15
[10/14/2025 07:19]  System: You have 14 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 14
[10/14/2025 07:19]  Razor: Attack: an ophidian shaman
[10/14/2025 07:19]  System: You will automatically stealth when out of steps.
[10/14/2025 07:19]  Razor: [Auto-stealth ready.]
[10/14/2025 07:19]  System: You have 13 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 13
[10/14/2025 07:19]  System: You have 12 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 12
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  You see: a wyvern corpse
[10/14/2025 07:19]  a wyvern: a wyvern
[10/14/2025 07:19]  System: You have 11 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 11
[10/14/2025 07:19]  System: You have 10 stealth steps remaining.
[10/14/2025 07:19]  Razor: [10 Steps...]
[10/14/2025 07:19]  [Razor]: Steps: 10
[10/14/2025 07:19]  System: You have 9 stealth steps remaining.
[10/14/2025 07:19]  Razor: [9 Steps...]
[10/14/2025 07:19]  [Razor]: Steps: 9
[10/14/2025 07:19]  System: You have 8 stealth steps remaining.
[10/14/2025 07:19]  Razor: [8 Steps...]
[10/14/2025 07:19]  [Razor]: Steps: 8
[10/14/2025 07:19]  System: You have 7 stealth steps remaining.
[10/14/2025 07:19]  Razor: [7 Steps...]
[10/14/2025 07:19]  System: You strike your target from the shadows!
[10/14/2025 07:19]  GawdHammer: You steal 289 gold coin.
[10/14/2025 07:19]  System: You steal 289 gold coin.
[10/14/2025 07:19]  GawdHammer: (Shadow Aspect 66,111.3/250000 xp)
[10/14/2025 07:19]  System: You have lost a little karma.
[10/14/2025 07:19]  System: You have gained a very large amount of karma.
[10/14/2025 07:19]  GawdHammer: (Shadow Aspect 66,135.0/250000 xp)
[10/14/2025 07:19]  System: -7000
[10/14/2025 07:19]  a wyvern: a wyvern
[10/14/2025 07:19]  You see: an ophidian shaman corpse
[10/14/2025 07:19]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:19]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:19]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:19]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:19]  System: You have 23 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 23
[10/14/2025 07:19]  System: You have 22 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 22
[10/14/2025 07:19]  System: You have 21 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 21
[10/14/2025 07:19]  a wyvern: a wyvern
[10/14/2025 07:19]  System: You have 20 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 20
[10/14/2025 07:19]  System: You have 19 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 19
[10/14/2025 07:19]  System: You have 18 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 18
[10/14/2025 07:19]  System: You have 17 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 17
[10/14/2025 07:19]  [Razor]: New target set.
[10/14/2025 07:19]  Razor: Target: a bonfire wisp
[10/14/2025 07:19]  System: You have 16 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 16
[10/14/2025 07:19]  System: You have 15 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 15
[10/14/2025 07:19]  System: You have 14 stealth steps remaining.
[10/14/2025 07:19]  [Razor]: Steps: 14
[10/14/2025 07:19]  Razor: Attack: a bonfire wisp
[10/14/2025 07:19]  System: You strike your target from the shadows!
[10/14/2025 07:19]  System: You fail to steal from the creature.
[10/14/2025 07:19]  System: You have lost a little karma.
[10/14/2025 07:19]  System: You have gained a very large amount of karma.
[10/14/2025 07:19]  GawdHammer: (Shadow Aspect 66,162.7/250000 xp)
[10/14/2025 07:19]  System: -6000
[10/14/2025 07:19]  You see: a bonfire wisp corpse
[10/14/2025 07:19]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:19]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:20]  [Razor]: New target set.
[10/14/2025 07:20]  Razor: Target: an ophidian dragonguard
[10/14/2025 07:20]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:20]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:20]  System: You have 23 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 23
[10/14/2025 07:20]  a wyvern: a wyvern
[10/14/2025 07:20]  System: You have 22 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 22
[10/14/2025 07:20]  System: You have 21 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 21
[10/14/2025 07:20]  You see: a wyvern corpse
[10/14/2025 07:20]  System: You have 20 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 20
[10/14/2025 07:20]  System: You have 19 stealth steps remaining.
[10/14/2025 07:20]  an ophidian dragonguard: Lishsthy lassesthyl asa asisialil thilytstsi is.
[10/14/2025 07:20]  [Razor]: Steps: 19
[10/14/2025 07:20]  System: You have 18 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 18
[10/14/2025 07:20]  You see: a wyvern corpse
[10/14/2025 07:20]  You see: a wyvern corpse
[10/14/2025 07:20]  System: You have 17 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 17
[10/14/2025 07:20]  System: You have 16 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 16
[10/14/2025 07:20]  a wyvern: a wyvern
[10/14/2025 07:20]  Razor: Attack: an ophidian dragonguard
[10/14/2025 07:20]  System: You strike your target from the shadows!
[10/14/2025 07:20]  GawdHammer: You steal 263 gold coin.
[10/14/2025 07:20]  System: You steal 263 gold coin.
[10/14/2025 07:20]  GawdHammer: (Shadow Aspect 66,168.2/250000 xp)
[10/14/2025 07:20]  System: You have lost a little karma.
[10/14/2025 07:20]  System: You have gained a very large amount of karma.
[10/14/2025 07:20]  GawdHammer: (Shadow Aspect 66,190.6/250000 xp)
[10/14/2025 07:20]  System: -8000
[10/14/2025 07:20]  You see: an ophidian dragonguard corpse
[10/14/2025 07:20]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:20]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:20]  [Razor]: New target set.
[10/14/2025 07:20]  Razor: Target: an ophidian dragonguard
[10/14/2025 07:20]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:20]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:20]  System: You have 23 stealth steps remaining.
[10/14/2025 07:20]  Razor: Attack: an ophidian dragonguard
[10/14/2025 07:20]  [Razor]: Steps: 23
[10/14/2025 07:20]  a wyvern: a wyvern
[10/14/2025 07:20]  System: You have 22 stealth steps remaining.
[10/14/2025 07:20]  System: You strike your target from the shadows!
[10/14/2025 07:20]  System: You fail to steal from the creature.
[10/14/2025 07:20]  System: You have lost a little karma.
[10/14/2025 07:20]  an ophidian dragonguard: -7238
[10/14/2025 07:20]  a wyvern: a wyvern
[10/14/2025 07:20]  You see: a wyvern corpse
[10/14/2025 07:20]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:20]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:20]  System: You are no longer hindered.
[10/14/2025 07:20]  System: You have 29 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 29
[10/14/2025 07:20]  System: You have 28 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: Steps: 28
[10/14/2025 07:20]  You see: a wyvern corpse
[10/14/2025 07:20]  System: You have 27 stealth steps remaining.
[10/14/2025 07:20]  [Razor]: New target set.
[10/14/2025 07:20]  [Razor]: Steps: 27
[10/14/2025 07:20]  System: You have 26 stealth steps remaining.
[10/14/2025 07:20]  System: You strike your target from the shadows!
[10/14/2025 07:20]  System: You fail to steal from the creature.
[10/14/2025 07:20]  System: You have lost a little karma.
[10/14/2025 07:20]  System: You have gained a very large amount of karma.
[10/14/2025 07:20]  GawdHammer: (Shadow Aspect 66,213.0/250000 xp)
[10/14/2025 07:20]  System: -762
[10/14/2025 07:20]  You see: an ophidian dragonguard corpse
[10/14/2025 07:20]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:20]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:20]  You see: a wyvern corpse
[10/14/2025 07:20]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:20]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:20]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:20]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 07:20]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 07:20]  System: Alduous Bundy has completed the achievement: Angler (Advanced).
[10/14/2025 07:20]  a wyvern: a wyvern
[10/14/2025 07:20]  System: Sandow Fastfoot has completed the achievement: Triggered (Epic).
[10/14/2025 07:20]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:20]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:21]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:21]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:21]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:21]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 07:21]  a stone adder: a stone adder
[10/14/2025 07:21]  a dusk dragon: a dusk dragon
[10/14/2025 07:21]  You see: a harvestman corpse
[10/14/2025 07:21]  You see: a cave dracolisk corpse
[10/14/2025 07:21]  You see: a cave dracolisk corpse
[10/14/2025 07:21]  You see: an ophidian shaman corpse
[10/14/2025 07:21]  You see: a wyvern corpse
[10/14/2025 07:21]  You see: an ophidian dragonguard corpse
[10/14/2025 07:21]  a wyvern: a wyvern
[10/14/2025 07:21]  a wyvern: a wyvern
[10/14/2025 07:21]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:21]  a wyvern: a wyvern
[10/14/2025 07:21]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:21]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:21]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:21]  System: You have 23 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 23
[10/14/2025 07:21]  System: You have 22 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 22
[10/14/2025 07:21]  System: You have 21 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 21
[10/14/2025 07:21]  You see: an ophidian dragonguard corpse
[10/14/2025 07:21]  System: You have 20 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 20
[10/14/2025 07:21]  System: You have 19 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 19
[10/14/2025 07:21]  [Razor]: New target set.
[10/14/2025 07:21]  Razor: Target: a wyvern
[10/14/2025 07:21]  System: You may now use another smoke bomb.
[10/14/2025 07:21]  Rabbit-GamerYT: [Siblings of the Dust, EMBR]
[10/14/2025 07:21]  Rabbit-GamerYT: Rabbit-GamerYT
[10/14/2025 07:21]  an earth elemental: (summoned)
[10/14/2025 07:21]  an earth elemental: an earth elemental
[10/14/2025 07:21]  Razor: Attack: a wyvern
[10/14/2025 07:21]  System: You strike your target from the shadows!
[10/14/2025 07:21]  GawdHammer: You steal 249 gold coin.
[10/14/2025 07:21]  System: You steal 249 gold coin.
[10/14/2025 07:21]  GawdHammer: (Shadow Aspect 66,217.8/250000 xp)
[10/14/2025 07:21]  System: You have lost a little karma.
[10/14/2025 07:21]  System: You have gained a lot of karma.
[10/14/2025 07:21]  GawdHammer: (Shadow Aspect 66,237.1/250000 xp)
[10/14/2025 07:21]  System: -8000
[10/14/2025 07:21]  You see: a wyvern corpse
[10/14/2025 07:21]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:21]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:21]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:21]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:21]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:21]  [Razor]: New target set.
[10/14/2025 07:21]  Razor: Target: a wyvern
[10/14/2025 07:21]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:21]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:21]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:21]  System: You have 23 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 23
[10/14/2025 07:21]  System: You have 22 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 22
[10/14/2025 07:21]  You see: an ophidian dragonguard corpse
[10/14/2025 07:21]  System: You have 21 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 21
[10/14/2025 07:21]  System: You have 20 stealth steps remaining.
[10/14/2025 07:21]  [Razor]: Steps: 20
[10/14/2025 07:22]  System: You have 19 stealth steps remaining.
[10/14/2025 07:22]  [Razor]: Steps: 19
[10/14/2025 07:22]  You see: an ophidian dragonguard corpse
[10/14/2025 07:22]  Razor: Attack: a wyvern
[10/14/2025 07:22]  System: You will automatically stealth when out of steps.
[10/14/2025 07:22]  Razor: [Auto-stealth ready.]
[10/14/2025 07:22]  System: You strike your target from the shadows!
[10/14/2025 07:22]  System: Society Job Progress: 5/10 [Steal 10 Black Goods].
[10/14/2025 07:22]  GawdHammer: You steal 259 gold coin.
[10/14/2025 07:22]  System: You steal 259 gold coin.
[10/14/2025 07:22]  GawdHammer: You steal extremely common black goods.
[10/14/2025 07:22]  System: You steal extremely common black goods.
[10/14/2025 07:22]  GawdHammer: (Shadow Aspect 66,241.9/250000 xp)
[10/14/2025 07:22]  System: You have lost a little karma.
[10/14/2025 07:22]  a wyvern: -7028
[10/14/2025 07:22]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:22]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:22]  System: You are no longer hindered.
[10/14/2025 07:22]  [Razor]: New target set.
[10/14/2025 07:22]  System: You strike your target from the shadows!
[10/14/2025 07:22]  System: You have already stolen from this creature.
[10/14/2025 07:22]  System: You have gained a lot of karma.
[10/14/2025 07:22]  GawdHammer: (Shadow Aspect 66,261.2/250000 xp)
[10/14/2025 07:22]  System: -972
[10/14/2025 07:22]  You see: a wyvern corpse
[10/14/2025 07:22]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:22]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:22]  You see: an ophidian dragonguard corpse
[10/14/2025 07:22]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:22]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:22]  [Razor]: New target set.
[10/14/2025 07:22]  Razor: Target: a wyvern
[10/14/2025 07:22]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:22]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:22]  System: You will automatically stealth when out of steps.
[10/14/2025 07:22]  Razor: [Auto-stealth ready.]
[10/14/2025 07:22]  System: You have 23 stealth steps remaining.
[10/14/2025 07:22]  [Razor]: Steps: 23
[10/14/2025 07:22]  System: You have 22 stealth steps remaining.
[10/14/2025 07:22]  [Razor]: New target set.
[10/14/2025 07:22]  [Razor]: Steps: 22
[10/14/2025 07:22]  You see: an ophidian dragonguard corpse
[10/14/2025 07:22]  System: You have 21 stealth steps remaining.
[10/14/2025 07:22]  [Razor]: Steps: 21
[10/14/2025 07:22]  System: You have 20 stealth steps remaining.
[10/14/2025 07:22]  Razor: Attack: a wyvern
[10/14/2025 07:22]  [Razor]: Steps: 20
[10/14/2025 07:22]  System: You have 19 stealth steps remaining.
[10/14/2025 07:22]  [Razor]: Steps: 19
[10/14/2025 07:22]  System: You have 18 stealth steps remaining.
[10/14/2025 07:22]  [Razor]: Steps: 18
[10/14/2025 07:22]  System: You have 17 stealth steps remaining.
[10/14/2025 07:22]  System: You strike your target from the shadows!
[10/14/2025 07:22]  System: You fail to steal from the creature.
[10/14/2025 07:22]  System: You have lost a little karma.
[10/14/2025 07:22]  a wyvern: -7669
[10/14/2025 07:22]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:22]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:22]  [Razor]: New target set.
[10/14/2025 07:23]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:23]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:23]  System: You have 23 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 23
[10/14/2025 07:23]  System: You have 22 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 22
[10/14/2025 07:23]  System: You have 21 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 21
[10/14/2025 07:23]  System: You have 20 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 20
[10/14/2025 07:23]  System: You have 19 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 19
[10/14/2025 07:23]  System: You have 18 stealth steps remaining.
[10/14/2025 07:23]  System: You strike your target from the shadows!
[10/14/2025 07:23]  System: You fail to steal from the creature.
[10/14/2025 07:23]  System: You have lost a little karma.
[10/14/2025 07:23]  System: You have gained a lot of karma.
[10/14/2025 07:23]  GawdHammer: (Shadow Aspect 66,280.5/250000 xp)
[10/14/2025 07:23]  System: -331
[10/14/2025 07:23]  You see: a wyvern corpse
[10/14/2025 07:23]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:23]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:23]  a bonfire wisp: a bonfire wisp
[10/14/2025 07:23]  You see: an ophidian dragonguard corpse
[10/14/2025 07:23]  System: You may now use another smoke bomb.
[10/14/2025 07:23]  You see: an ophidian dragonguard corpse
[10/14/2025 07:23]  a bonfire wisp: a bonfire wisp
[10/14/2025 07:23]  You see: an ophidian dragonguard corpse
[10/14/2025 07:23]  You see: a harvestman corpse
[10/14/2025 07:23]  You see: a cave dracolisk corpse
[10/14/2025 07:23]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:23]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:23]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:23]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:23]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:23]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:23]  System: You have 23 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 23
[10/14/2025 07:23]  [Razor]: New target set.
[10/14/2025 07:23]  Razor: Target: a cave dracolisk
[10/14/2025 07:23]  System: You have 22 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 22
[10/14/2025 07:23]  System: You have 21 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 21
[10/14/2025 07:23]  a harvestman: a harvestman
[10/14/2025 07:23]  System: You have 20 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 20
[10/14/2025 07:23]  System: You have 19 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 19
[10/14/2025 07:23]  System: You have 18 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 18
[10/14/2025 07:23]  Razor: Attack: a cave dracolisk
[10/14/2025 07:23]  System: You strike your target from the shadows!
[10/14/2025 07:23]  GawdHammer: You steal 289 gold coin.
[10/14/2025 07:23]  System: You steal 289 gold coin.
[10/14/2025 07:23]  GawdHammer: (Shadow Aspect 66,286.2/250000 xp)
[10/14/2025 07:23]  System: You have lost a little karma.
[10/14/2025 07:23]  System: You have gained a very large amount of karma.
[10/14/2025 07:23]  GawdHammer: (Shadow Aspect 66,309.0/250000 xp)
[10/14/2025 07:23]  System: -8000
[10/14/2025 07:23]  You see: a cave dracolisk corpse
[10/14/2025 07:23]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:23]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:23]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:23]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:23]  System: You have 23 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 23
[10/14/2025 07:23]  System: You have 22 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 22
[10/14/2025 07:23]  System: You have 21 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 21
[10/14/2025 07:23]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:23]  System: You have 20 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: New target set.
[10/14/2025 07:23]  Razor: Target: a harvestman
[10/14/2025 07:23]  [Razor]: Steps: 20
[10/14/2025 07:23]  System: You have 19 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 19
[10/14/2025 07:23]  System: You have 18 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 18
[10/14/2025 07:23]  System: You have 17 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 17
[10/14/2025 07:23]  System: You have 16 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 16
[10/14/2025 07:23]  System: You have 15 stealth steps remaining.
[10/14/2025 07:23]  [Razor]: Steps: 15
[10/14/2025 07:24]  System: You have 14 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 14
[10/14/2025 07:24]  System: You have 13 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 13
[10/14/2025 07:24]  System: You have 12 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 12
[10/14/2025 07:24]  You see: a forktongue spinebreaker corpse
[10/14/2025 07:24]  System: You have 11 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 11
[10/14/2025 07:24]  Razor: Attack: a harvestman
[10/14/2025 07:24]  System: You strike your target from the shadows!
[10/14/2025 07:24]  GawdHammer: You steal 246 gold coin.
[10/14/2025 07:24]  System: You steal 246 gold coin.
[10/14/2025 07:24]  GawdHammer: (Shadow Aspect 66,314.2/250000 xp)
[10/14/2025 07:24]  System: You have lost a little karma.
[10/14/2025 07:24]  System: You have gained a very large amount of karma.
[10/14/2025 07:24]  GawdHammer: (Shadow Aspect 66,334.7/250000 xp)
[10/14/2025 07:24]  System: -6000
[10/14/2025 07:24]  You see: a harvestman corpse
[10/14/2025 07:24]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:24]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:24]  [Razor]: New target set.
[10/14/2025 07:24]  Razor: Target: a cave dracolisk
[10/14/2025 07:24]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:24]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:24]  System: You have 23 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 23
[10/14/2025 07:24]  System: You have 22 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 22
[10/14/2025 07:24]  System: You have 21 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 21
[10/14/2025 07:24]  You see: a forktongue battlekhan corpse
[10/14/2025 07:24]  You see: a forktongue shocktrooper corpse
[10/14/2025 07:24]  System: You have 20 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 20
[10/14/2025 07:24]  You see: a forktongue spinebreaker corpse
[10/14/2025 07:24]  You see: a forktongue shocktrooper corpse
[10/14/2025 07:24]  System: You have 19 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 19
[10/14/2025 07:24]  a harvestman: a harvestman
[10/14/2025 07:24]  System: You have 18 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 18
[10/14/2025 07:24]  Razor: Attack: a cave dracolisk
[10/14/2025 07:24]  System: You strike your target from the shadows!
[10/14/2025 07:24]  GawdHammer: You steal 298 gold coin.
[10/14/2025 07:24]  System: You steal 298 gold coin.
[10/14/2025 07:24]  GawdHammer: (Shadow Aspect 66,340.4/250000 xp)
[10/14/2025 07:24]  System: You have lost a little karma.
[10/14/2025 07:24]  System: You have gained a very large amount of karma.
[10/14/2025 07:24]  GawdHammer: (Shadow Aspect 66,363.3/250000 xp)
[10/14/2025 07:24]  System: -8000
[10/14/2025 07:24]  You see: a cave dracolisk corpse
[10/14/2025 07:24]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:24]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 07:24]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:24]  You see: a forktongue spinebreaker corpse
[10/14/2025 07:24]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:24]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:24]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:24]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 07:24]  System: Chico Rei has completed the achievement: Guardian of Inferno (Basic).
[10/14/2025 07:24]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 07:24]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 07:24]  a harvestman: a harvestman
[10/14/2025 07:24]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:24]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:24]  [Razor]: New target set.
[10/14/2025 07:24]  Razor: Target: a cave dracolisk
[10/14/2025 07:24]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:24]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:24]  System: You have 23 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 23
[10/14/2025 07:24]  System: You have 22 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 22
[10/14/2025 07:24]  System: You have 21 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 21
[10/14/2025 07:24]  System: You have 20 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 20
[10/14/2025 07:24]  System: You have 19 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 19
[10/14/2025 07:24]  System: You have 18 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 18
[10/14/2025 07:24]  System: You have 17 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 17
[10/14/2025 07:24]  System: You have 16 stealth steps remaining.
[10/14/2025 07:24]  [Razor]: Steps: 16
[10/14/2025 07:24]  Razor: Attack: a cave dracolisk
[10/14/2025 07:24]  System: You strike your target from the shadows!
[10/14/2025 07:24]  System: You fail to steal from the creature.
[10/14/2025 07:24]  System: You have lost a little karma.
[10/14/2025 07:24]  System: You have gained a very large amount of karma.
[10/14/2025 07:24]  GawdHammer: (Shadow Aspect 66,386.1/250000 xp)
[10/14/2025 07:24]  System: -8000
[10/14/2025 07:24]  You see: a cave dracolisk corpse
[10/14/2025 07:24]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 07:24]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:24]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:24]  [Razor]: New target set.
[10/14/2025 07:24]  Razor: Target: a harvestman
[10/14/2025 07:25]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:25]  Waterpipe: [The Jammers Maker, -TJ-]
[10/14/2025 07:25]  Waterpipe: Lord Waterpipe
[10/14/2025 07:25]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:25]  a searing imp: (summoned)
[10/14/2025 07:25]  a searing imp: a searing imp
[10/14/2025 07:25]  a water elemental: (summoned)
[10/14/2025 07:25]  a water elemental: a water elemental
[10/14/2025 07:25]  a water elemental: (summoned)
[10/14/2025 07:25]  a water elemental: a water elemental
[10/14/2025 07:25]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 07:25]  System: You have 23 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 23
[10/14/2025 07:25]  a dusk dragon: a dusk dragon
[10/14/2025 07:25]  System: You have 22 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 22
[10/14/2025 07:25]  System: You have 21 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 21
[10/14/2025 07:25]  a cave dracolisk: a cave dracolisk
[10/14/2025 07:25]  Razor: Attack: a harvestman
[10/14/2025 07:25]  System: You have 20 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 20
[10/14/2025 07:25]  System: You have 19 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 19
[10/14/2025 07:25]  System: You have 18 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 18
[10/14/2025 07:25]  a harvestman: a harvestman
[10/14/2025 07:25]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 07:25]  System: You strike your target from the shadows!
[10/14/2025 07:25]  System: You fail to steal from the creature.
[10/14/2025 07:25]  System: You have lost a little karma.
[10/14/2025 07:25]  System: You have gained a very large amount of karma.
[10/14/2025 07:25]  GawdHammer: (Shadow Aspect 66,406.7/250000 xp)
[10/14/2025 07:25]  System: -6000
[10/14/2025 07:25]  You see: a harvestman corpse
[10/14/2025 07:25]  You see: a harvestman corpse
[10/14/2025 07:25]  Waterpipe: [The Jammers Maker, -TJ-]
[10/14/2025 07:25]  Waterpipe: Lord Waterpipe
[10/14/2025 07:25]  a searing imp: (summoned)
[10/14/2025 07:25]  a searing imp: a searing imp
[10/14/2025 07:25]  a water elemental: (summoned)
[10/14/2025 07:25]  a water elemental: a water elemental
[10/14/2025 07:25]  a water elemental: (summoned)
[10/14/2025 07:25]  a water elemental: a water elemental
[10/14/2025 07:25]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:25]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:25]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 07:25]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 07:25]  You see: a cave dracolisk corpse
[10/14/2025 07:25]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:25]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 07:25]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 07:25]  a harvestman: a harvestman
[10/14/2025 07:25]  You see: a cave dracolisk corpse
[10/14/2025 07:25]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:25]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:25]  [Razor]: Steps: 0
[10/14/2025 07:25]  System: You are no longer hindered.
[10/14/2025 07:25]  System: You have 29 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 29
[10/14/2025 07:25]  System: You have 28 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 28
[10/14/2025 07:25]  System: You have 27 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 27
[10/14/2025 07:25]  [Razor]: New target set.
[10/14/2025 07:25]  Razor: Target: a harvestman
[10/14/2025 07:25]  System: You have 26 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 26
[10/14/2025 07:25]  System: You have 25 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 25
[10/14/2025 07:25]  System: You have 24 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 24
[10/14/2025 07:25]  System: You have 23 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 23
[10/14/2025 07:25]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 07:25]  System: You have 22 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 22
[10/14/2025 07:25]  System: You have 21 stealth steps remaining.
[10/14/2025 07:25]  Razor: Attack: a harvestman
[10/14/2025 07:25]  [Razor]: Steps: 21
[10/14/2025 07:25]  System: You have 20 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 20
[10/14/2025 07:25]  System: You have 19 stealth steps remaining.
[10/14/2025 07:25]  [Razor]: Steps: 19
[10/14/2025 07:25]  System: You have 18 stealth steps remaining.
[10/14/2025 07:25]  System: You strike your target from the shadows!
[10/14/2025 07:25]  GawdHammer: You steal 237 gold coin.
[10/14/2025 07:25]  System: You steal 237 gold coin.
[10/14/2025 07:25]  GawdHammer: (Shadow Aspect 66,411.8/250000 xp)
[10/14/2025 07:25]  System: You have lost a little karma.
[10/14/2025 07:25]  System: You have gained a very large amount of karma.
[10/14/2025 07:25]  GawdHammer: (Shadow Aspect 66,432.3/250000 xp)
[10/14/2025 07:25]  System: -6000
[10/14/2025 07:25]  You see: a harvestman corpse
[10/14/2025 07:25]  You see: an ophidian shaman corpse
[10/14/2025 07:25]  You see: an ophidian dragonguard corpse
[10/14/2025 07:25]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:25]  an earth elemental: (summoned)
[10/14/2025 07:25]  an earth elemental: an earth elemental
[10/14/2025 07:25]  You see: a bonfire wisp corpse
[10/14/2025 07:25]  a blade spirit: (summoned)
[10/14/2025 07:25]  a blade spirit: a blade spirit
[10/14/2025 07:25]  You see: a bonfire wisp corpse
[10/14/2025 07:25]  You see: a wyvern corpse
[10/14/2025 07:25]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:25]  an energy vortex: (summoned)
[10/14/2025 07:25]  an energy vortex: an energy vortex
[10/14/2025 07:25]  You see: an ophidian dragonguard corpse
[10/14/2025 07:25]  You see: a wyvern corpse
[10/14/2025 07:25]  a stone adder: a stone adder
[10/14/2025 07:25]  a stone adder: a stone adder
[10/14/2025 07:25]  a stone adder: a stone adder
[10/14/2025 07:26]  You see: a harvestman corpse
[10/14/2025 07:26]  a dragon: a dragon
[10/14/2025 07:26]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:26]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  CreoleJoe: Beast Slayer
[10/14/2025 07:26]  CreoleJoe: [Knight of the Realm, COOL]
[10/14/2025 07:26]  CreoleJoe: Lord CreoleJoe
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:26]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:26]  System: You have 23 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 23
[10/14/2025 07:26]  System: You have 22 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 22
[10/14/2025 07:26]  System: You have 21 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 21
[10/14/2025 07:26]  System: You have 20 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 20
[10/14/2025 07:26]  System: You have 19 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 19
[10/14/2025 07:26]  System: You have 18 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 18
[10/14/2025 07:26]  System: You have 17 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 17
[10/14/2025 07:26]  System: You have 16 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 16
[10/14/2025 07:26]  System: You have 15 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 15
[10/14/2025 07:26]  a dragon: a dragon
[10/14/2025 07:26]  System: You have 14 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 14
[10/14/2025 07:26]  System: You have 13 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 13
[10/14/2025 07:26]  System: You have 12 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 12
[10/14/2025 07:26]  System: You have 11 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 11
[10/14/2025 07:26]  System: You have 10 stealth steps remaining.
[10/14/2025 07:26]  Razor: [10 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 10
[10/14/2025 07:26]  an earth dragon: an earth dragon
[10/14/2025 07:26]  System: You have 9 stealth steps remaining.
[10/14/2025 07:26]  Razor: [9 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 9
[10/14/2025 07:26]  System: You may now use another smoke bomb.
[10/14/2025 07:26]  System: You have 8 stealth steps remaining.
[10/14/2025 07:26]  Razor: [8 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 8
[10/14/2025 07:26]  an ophidian shaman: an ophidian shaman
[10/14/2025 07:26]  System: You have 7 stealth steps remaining.
[10/14/2025 07:26]  Razor: [7 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 7
[10/14/2025 07:26]  System: You have 6 stealth steps remaining.
[10/14/2025 07:26]  Razor: [6 Steps...]
[10/14/2025 07:26]  System: You will automatically stealth when out of steps.
[10/14/2025 07:26]  Razor: [Auto-stealth ready.]
[10/14/2025 07:26]  [Razor]: Steps: 6
[10/14/2025 07:26]  an earth dragon: an earth dragon
[10/14/2025 07:26]  System: You have 5 stealth steps remaining.
[10/14/2025 07:26]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:26]  a dragon: a dragon
[10/14/2025 07:26]  [Razor]: Steps: 5
[10/14/2025 07:26]  an earth dragon: an earth dragon
[10/14/2025 07:26]  System: You have 4 stealth steps remaining.
[10/14/2025 07:26]  Razor: [4 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 4
[10/14/2025 07:26]  System: You have 3 stealth steps remaining.
[10/14/2025 07:26]  Razor: [3 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 3
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  System: You have 2 stealth steps remaining.
[10/14/2025 07:26]  Razor: [2 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 2
[10/14/2025 07:26]  You see: an ophidian shaman corpse
[10/14/2025 07:26]  System: You have 1 stealth steps remaining.
[10/14/2025 07:26]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:26]  [Razor]: Steps: 1
[10/14/2025 07:26]  System: You have 0 stealth steps remaining.
[10/14/2025 07:26]  System: You feel ready to continue stealthing.
[10/14/2025 07:26]  Razor: [Stop!]
[10/14/2025 07:26]  [Razor]: Steps: 0
[10/14/2025 07:26]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:26]  System: You have 23 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 23
[10/14/2025 07:26]  System: You have 22 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 22
[10/14/2025 07:26]  System: You have 21 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 21
[10/14/2025 07:26]  System: You have 20 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 20
[10/14/2025 07:26]  an earth dragon: an earth dragon
[10/14/2025 07:26]  System: You have 19 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 19
[10/14/2025 07:26]  System: You have 18 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 18
[10/14/2025 07:26]  System: You have 17 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 17
[10/14/2025 07:26]  System: You have 16 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 16
[10/14/2025 07:26]  System: You have 15 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 15
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  System: You have 14 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 14
[10/14/2025 07:26]  System: You have 13 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 13
[10/14/2025 07:26]  System: You have 12 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 12
[10/14/2025 07:26]  System: You have 11 stealth steps remaining.
[10/14/2025 07:26]  [Razor]: Steps: 11
[10/14/2025 07:26]  [Razor]: New target set.
[10/14/2025 07:26]  Razor: Target: an ophidian shaman
[10/14/2025 07:26]  Cloxz: Legendary Connoisseur
[10/14/2025 07:26]  Cloxz: [Defiler, WKD]
[10/14/2025 07:26]  Cloxz: Cloxz
[10/14/2025 07:26]  System: You have 10 stealth steps remaining.
[10/14/2025 07:26]  Razor: [10 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 10
[10/14/2025 07:26]  an earth elemental: (summoned)
[10/14/2025 07:26]  an earth elemental: an earth elemental
[10/14/2025 07:26]  an earth elemental: (summoned)
[10/14/2025 07:26]  an earth elemental: an earth elemental
[10/14/2025 07:26]  System: You have 9 stealth steps remaining.
[10/14/2025 07:26]  Razor: [9 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 9
[10/14/2025 07:26]  System: You have 8 stealth steps remaining.
[10/14/2025 07:26]  Razor: [8 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 8
[10/14/2025 07:26]  System: You have 7 stealth steps remaining.
[10/14/2025 07:26]  Razor: [7 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 7
[10/14/2025 07:26]  System: You have 6 stealth steps remaining.
[10/14/2025 07:26]  Razor: [6 Steps...]
[10/14/2025 07:26]  [Razor]: Steps: 6
[10/14/2025 07:26]  System: You have 5 stealth steps remaining.
[10/14/2025 07:26]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:26]  Razor: Attack: an ophidian shaman
[10/14/2025 07:26]  [Razor]: Steps: 5
[10/14/2025 07:26]  System: You have 4 stealth steps remaining.
[10/14/2025 07:26]  System: You strike your target from the shadows!
[10/14/2025 07:26]  GawdHammer: You steal 272 gold coin.
[10/14/2025 07:26]  System: You steal 272 gold coin.
[10/14/2025 07:26]  GawdHammer: (Shadow Aspect 66,438.2/250000 xp)
[10/14/2025 07:26]  System: You have lost a little karma.
[10/14/2025 07:26]  System: You have gained a very large amount of karma.
[10/14/2025 07:26]  GawdHammer: (Shadow Aspect 66,462.0/250000 xp)
[10/14/2025 07:26]  System: -7000
[10/14/2025 07:26]  Razor: [4 Steps...]
[10/14/2025 07:26]  You see: an ophidian shaman corpse
[10/14/2025 07:26]  : flowstone
[10/14/2025 07:26]  : flowstone
[10/14/2025 07:26]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 07:26]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 07:26]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 07:26]  a wyvern: a wyvern
[10/14/2025 07:26]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 07:26]  an earth elemental: (summoned)
[10/14/2025 07:26]  an earth elemental: an earth elemental
[10/14/2025 07:26]  an earth elemental: (summoned)
[10/14/2025 07:26]  an earth elemental: an earth elemental
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  Razor: Hiding and waiting to sneak...
[10/14/2025 07:26]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:26]  an earth elemental: (summoned)
[10/14/2025 07:26]  an earth elemental: an earth elemental
[10/14/2025 07:26]  an earth elemental: (summoned)
[10/14/2025 07:26]  an earth elemental: an earth elemental
[10/14/2025 07:26]  Cloxz: Legendary Connoisseur
[10/14/2025 07:26]  Cloxz: [Defiler, WKD]
[10/14/2025 07:26]  Cloxz: Cloxz
[10/14/2025 07:26]  an ophidian dragonguard: Sythais leskthytsthys thathathyl!
[10/14/2025 07:26]  an ophidian dragonguard: Away with thee!
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  Cloxz: 5
[10/14/2025 07:26]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 07:26]  an ophidian dragonguard: *potion stuck*
[10/14/2025 07:27]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:27]  Greater Explosion potion: 4
[10/14/2025 07:27]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:27]  System: You have 23 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 23
[10/14/2025 07:27]  Greater Explosion potion: 3
[10/14/2025 07:27]  System: You have 22 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 22
[10/14/2025 07:27]  System: You have 21 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 21
[10/14/2025 07:27]  Greater Explosion potion: 2
[10/14/2025 07:27]  System: You have 20 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 20
[10/14/2025 07:27]  System: You have 19 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 19
[10/14/2025 07:27]  System: You have 18 stealth steps remaining.
[10/14/2025 07:27]  Folcan dex: Legendarily Triggered
[10/14/2025 07:27]  Folcan dex: [Ripe, SQzD]
[10/14/2025 07:27]  Folcan dex: Lord Folcan dex
[10/14/2025 07:27]  [Razor]: Steps: 18
[10/14/2025 07:27]  Greater Explosion potion: 1
[10/14/2025 07:27]  System: You have 17 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 17
[10/14/2025 07:27]  System: You have 16 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 16
[10/14/2025 07:27]  Folcan dex: i ban thee bank guards room vendor buy
[10/14/2025 07:27]  System: You have 15 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 15
[10/14/2025 07:27]  System: You have 14 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 14
[10/14/2025 07:27]  System: You have 13 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 13
[10/14/2025 07:27]  You see: a wyvern corpse
[10/14/2025 07:27]  System: You have 12 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 12
[10/14/2025 07:27]  System: You have 11 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 11
[10/14/2025 07:27]  System: You have 10 stealth steps remaining.
[10/14/2025 07:27]  Razor: [10 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 10
[10/14/2025 07:27]  System: You have 9 stealth steps remaining.
[10/14/2025 07:27]  Razor: [9 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 9
[10/14/2025 07:27]  a wyvern: a wyvern
[10/14/2025 07:27]  a wyvern: a wyvern
[10/14/2025 07:27]  System: You have 8 stealth steps remaining.
[10/14/2025 07:27]  Razor: [8 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 8
[10/14/2025 07:27]  System: You have 7 stealth steps remaining.
[10/14/2025 07:27]  Razor: [7 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 7
[10/14/2025 07:27]  System: You have 6 stealth steps remaining.
[10/14/2025 07:27]  Razor: [6 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 6
[10/14/2025 07:27]  System: You have 5 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:27]  [Razor]: Steps: 5
[10/14/2025 07:27]  System: You have 4 stealth steps remaining.
[10/14/2025 07:27]  Razor: [4 Steps...]
[10/14/2025 07:27]  System: You will automatically stealth when out of steps.
[10/14/2025 07:27]  Razor: [Auto-stealth ready.]
[10/14/2025 07:27]  [Razor]: Steps: 4
[10/14/2025 07:27]  System: You have 3 stealth steps remaining.
[10/14/2025 07:27]  Razor: [3 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 3
[10/14/2025 07:27]  System: You have 2 stealth steps remaining.
[10/14/2025 07:27]  Razor: [2 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 2
[10/14/2025 07:27]  System: You have 1 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:27]  [Razor]: Steps: 1
[10/14/2025 07:27]  Cloxz: Kal Vas Xen Ylem
[10/14/2025 07:27]  System: You have 0 stealth steps remaining.
[10/14/2025 07:27]  System: You feel ready to continue stealthing.
[10/14/2025 07:27]  Razor: [Stop!]
[10/14/2025 07:27]  [Razor]: Steps: 0
[10/14/2025 07:27]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:27]  System: You have 23 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 23
[10/14/2025 07:27]  System: You have 22 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 22
[10/14/2025 07:27]  System: You have 21 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 21
[10/14/2025 07:27]  System: You have 20 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 20
[10/14/2025 07:27]  an earth elemental: (summoned)
[10/14/2025 07:27]  an earth elemental: an earth elemental
[10/14/2025 07:27]  System: You have 19 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 19
[10/14/2025 07:27]  System: You have 18 stealth steps remaining.
[10/14/2025 07:27]  Cloxz: All Guard
[10/14/2025 07:27]  [Razor]: Steps: 18
[10/14/2025 07:27]  System: You have 17 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 17
[10/14/2025 07:27]  System: You have 16 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 16
[10/14/2025 07:27]  System: You have 15 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 15
[10/14/2025 07:27]  System: You have 14 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 14
[10/14/2025 07:27]  [Razor]: Steps: 14
[10/14/2025 07:27]  System: You have 13 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 13
[10/14/2025 07:27]  System: You have 12 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 12
[10/14/2025 07:27]  System: You have 11 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 11
[10/14/2025 07:27]  System: You have 10 stealth steps remaining.
[10/14/2025 07:27]  Razor: [10 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 10
[10/14/2025 07:27]  System: You have 9 stealth steps remaining.
[10/14/2025 07:27]  Razor: [9 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 9
[10/14/2025 07:27]  System: You have 8 stealth steps remaining.
[10/14/2025 07:27]  Razor: [8 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 8
[10/14/2025 07:27]  System: You have 7 stealth steps remaining.
[10/14/2025 07:27]  Razor: [7 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 7
[10/14/2025 07:27]  System: You have 6 stealth steps remaining.
[10/14/2025 07:27]  Razor: [6 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 6
[10/14/2025 07:27]  System: You have 5 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:27]  [Razor]: Steps: 5
[10/14/2025 07:27]  System: You have 4 stealth steps remaining.
[10/14/2025 07:27]  Razor: [4 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 4
[10/14/2025 07:27]  System: You have 3 stealth steps remaining.
[10/14/2025 07:27]  Razor: [3 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 3
[10/14/2025 07:27]  You see: an eldritch drake corpse
[10/14/2025 07:27]  You see: an eldritch drake corpse
[10/14/2025 07:27]  System: You have 2 stealth steps remaining.
[10/14/2025 07:27]  Razor: [2 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 2
[10/14/2025 07:27]  System: You have 1 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:27]  System: You will automatically stealth when out of steps.
[10/14/2025 07:27]  Razor: [Auto-stealth ready.]
[10/14/2025 07:27]  [Razor]: Steps: 1
[10/14/2025 07:27]  System: You have 0 stealth steps remaining.
[10/14/2025 07:27]  System: You feel ready to continue stealthing.
[10/14/2025 07:27]  Razor: [Stop!]
[10/14/2025 07:27]  [Razor]: Steps: 0
[10/14/2025 07:27]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:27]  System: You have 23 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 23
[10/14/2025 07:27]  You see: an eldritch drake corpse
[10/14/2025 07:27]  System: You have 22 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 22
[10/14/2025 07:27]  System: You have 21 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 21
[10/14/2025 07:27]  You see: an ember drake corpse
[10/14/2025 07:27]  You see: an ember drake corpse
[10/14/2025 07:27]  System: You have 20 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 20
[10/14/2025 07:27]  System: You have 19 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 19
[10/14/2025 07:27]  System: You have 18 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 18
[10/14/2025 07:27]  System: You have 17 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 17
[10/14/2025 07:27]  System: You have 16 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 16
[10/14/2025 07:27]  System: You have 15 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 15
[10/14/2025 07:27]  System: You have 14 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 14
[10/14/2025 07:27]  System: You have 13 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 13
[10/14/2025 07:27]  You see: an eldritch drake corpse
[10/14/2025 07:27]  System: You have 12 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 12
[10/14/2025 07:27]  System: You have 11 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 11
[10/14/2025 07:27]  System: You have 10 stealth steps remaining.
[10/14/2025 07:27]  Razor: [10 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 10
[10/14/2025 07:27]  System: You have 9 stealth steps remaining.
[10/14/2025 07:27]  Razor: [9 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 9
[10/14/2025 07:27]  System: You have 8 stealth steps remaining.
[10/14/2025 07:27]  Razor: [8 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 8
[10/14/2025 07:27]  System: You have 7 stealth steps remaining.
[10/14/2025 07:27]  Razor: [7 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 7
[10/14/2025 07:27]  System: You have 6 stealth steps remaining.
[10/14/2025 07:27]  Razor: [6 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 6
[10/14/2025 07:27]  System: You have 5 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:27]  [Razor]: Steps: 5
[10/14/2025 07:27]  System: You have 4 stealth steps remaining.
[10/14/2025 07:27]  Razor: [4 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 4
[10/14/2025 07:27]  System: You have 3 stealth steps remaining.
[10/14/2025 07:27]  Razor: [3 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 3
[10/14/2025 07:27]  System: You have 2 stealth steps remaining.
[10/14/2025 07:27]  Razor: [2 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 2
[10/14/2025 07:27]  System: You have 1 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:27]  [Razor]: Steps: 1
[10/14/2025 07:27]  System: You have 0 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Stop!]
[10/14/2025 07:27]  [Razor]: Steps: 0
[10/14/2025 07:27]  System: You feel ready to continue stealthing.
[10/14/2025 07:27]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:27]  System: You have 23 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 23
[10/14/2025 07:27]  System: You have 22 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 22
[10/14/2025 07:27]  System: You have 21 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 21
[10/14/2025 07:27]  a smoke drake: a smoke drake
[10/14/2025 07:27]  System: You have 20 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 20
[10/14/2025 07:27]  an eldritch drake: an eldritch drake
[10/14/2025 07:27]  System: You have 19 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 19
[10/14/2025 07:27]  System: You have 18 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 18
[10/14/2025 07:27]  System: You have 17 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 17
[10/14/2025 07:27]  System: You have 16 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 16
[10/14/2025 07:27]  System: You have 15 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 15
[10/14/2025 07:27]  System: You have 14 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 14
[10/14/2025 07:27]  [Razor]: New target set.
[10/14/2025 07:27]  Razor: Target: an eldritch drake
[10/14/2025 07:27]  System: You have 13 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 13
[10/14/2025 07:27]  System: You have 12 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 12
[10/14/2025 07:27]  System: You have 11 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 11
[10/14/2025 07:27]  System: You have 10 stealth steps remaining.
[10/14/2025 07:27]  Razor: [10 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 10
[10/14/2025 07:27]  System: You have 9 stealth steps remaining.
[10/14/2025 07:27]  Razor: [9 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 9
[10/14/2025 07:27]  System: You have 8 stealth steps remaining.
[10/14/2025 07:27]  Razor: [8 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 8
[10/14/2025 07:27]  System: You have 7 stealth steps remaining.
[10/14/2025 07:27]  Razor: [7 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 7
[10/14/2025 07:27]  Razor: Attack: an eldritch drake
[10/14/2025 07:27]  System: You have 6 stealth steps remaining.
[10/14/2025 07:27]  Razor: [6 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 6
[10/14/2025 07:27]  System: You have 5 stealth steps remaining.
[10/14/2025 07:27]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:27]  [Razor]: Steps: 5
[10/14/2025 07:27]  System: You have 4 stealth steps remaining.
[10/14/2025 07:27]  Razor: [4 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 4
[10/14/2025 07:27]  System: You have 3 stealth steps remaining.
[10/14/2025 07:27]  Razor: [3 Steps...]
[10/14/2025 07:27]  System: You strike your target from the shadows!
[10/14/2025 07:27]  System: You fail to steal from the creature.
[10/14/2025 07:27]  System: You have lost a little karma.
[10/14/2025 07:27]  System: You have gained a very large amount of karma.
[10/14/2025 07:27]  GawdHammer: (Shadow Aspect 66,487.3/250000 xp)
[10/14/2025 07:27]  System: -6000
[10/14/2025 07:27]  You see: an eldritch drake corpse
[10/14/2025 07:27]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:27]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 07:27]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 07:27]  GawdHammer: You have hidden yourself well.
[10/14/2025 07:27]  System: You begin to move quietly. (30 steps)
[10/14/2025 07:27]  System: You are no longer hindered.
[10/14/2025 07:27]  System: You have 29 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 29
[10/14/2025 07:27]  System: You have 28 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 28
[10/14/2025 07:27]  System: You have 27 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 27
[10/14/2025 07:27]  System: You have 26 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 26
[10/14/2025 07:27]  System: You have 25 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 25
[10/14/2025 07:27]  System: You have 24 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 24
[10/14/2025 07:27]  System: You have 23 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 23
[10/14/2025 07:27]  a smoke drake: a smoke drake
[10/14/2025 07:27]  System: You have 22 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 22
[10/14/2025 07:27]  System: You have 21 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 21
[10/14/2025 07:27]  System: You have 20 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 20
[10/14/2025 07:27]  System: You have 19 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 19
[10/14/2025 07:27]  System: You have 18 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 18
[10/14/2025 07:27]  System: You have 17 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 17
[10/14/2025 07:27]  System: You have 16 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 16
[10/14/2025 07:27]  System: You have 15 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 15
[10/14/2025 07:27]  a smoke drake: a smoke drake
[10/14/2025 07:27]  System: You have 14 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 14
[10/14/2025 07:27]  System: You have 13 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 13
[10/14/2025 07:27]  System: You have 12 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 12
[10/14/2025 07:27]  System: You have 11 stealth steps remaining.
[10/14/2025 07:27]  [Razor]: Steps: 11
[10/14/2025 07:27]  System: You have 10 stealth steps remaining.
[10/14/2025 07:27]  Razor: [10 Steps...]
[10/14/2025 07:27]  [Razor]: Steps: 10
[10/14/2025 07:27]  You see: a smoke drake corpse
[10/14/2025 07:28]  System: You have 9 stealth steps remaining.
[10/14/2025 07:28]  Razor: [9 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 9
[10/14/2025 07:28]  System: You have 8 stealth steps remaining.
[10/14/2025 07:28]  Razor: [8 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 8
[10/14/2025 07:28]  You see: a smoke drake corpse
[10/14/2025 07:28]  System: You have 7 stealth steps remaining.
[10/14/2025 07:28]  Razor: [7 Steps...]
[10/14/2025 07:28]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 07:28]  System: You will automatically stealth when out of steps.
[10/14/2025 07:28]  Razor: [Auto-stealth ready.]
[10/14/2025 07:28]  [Razor]: Steps: 7
[10/14/2025 07:28]  System: You have 6 stealth steps remaining.
[10/14/2025 07:28]  Razor: [6 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 6
[10/14/2025 07:28]  System: You have 5 stealth steps remaining.
[10/14/2025 07:28]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:28]  [Razor]: Steps: 5
[10/14/2025 07:28]  System: You have 4 stealth steps remaining.
[10/14/2025 07:28]  Razor: [4 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 4
[10/14/2025 07:28]  System: You have 3 stealth steps remaining.
[10/14/2025 07:28]  Razor: [3 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 3
[10/14/2025 07:28]  System: You have 2 stealth steps remaining.
[10/14/2025 07:28]  Razor: [2 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 2
[10/14/2025 07:28]  System: You have 1 stealth steps remaining.
[10/14/2025 07:28]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:28]  [Razor]: Steps: 1
[10/14/2025 07:28]  System: You have 0 stealth steps remaining.
[10/14/2025 07:28]  System: You feel ready to continue stealthing.
[10/14/2025 07:28]  Razor: [Stop!]
[10/14/2025 07:28]  [Razor]: Steps: 0
[10/14/2025 07:28]  an adder: an adder
[10/14/2025 07:28]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:28]  System: You have 23 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 23
[10/14/2025 07:28]  System: You have 22 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 22
[10/14/2025 07:28]  System: You have 21 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 21
[10/14/2025 07:28]  System: You have 20 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 20
[10/14/2025 07:28]  System: You have 19 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 19
[10/14/2025 07:28]  System: You have 18 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 18
[10/14/2025 07:28]  System: You have 17 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 17
[10/14/2025 07:28]  System: You have 16 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 16
[10/14/2025 07:28]  System: You have 15 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 15
[10/14/2025 07:28]  System: You have 14 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 14
[10/14/2025 07:28]  System: You have 13 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 13
[10/14/2025 07:28]  System: You have 12 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 12
[10/14/2025 07:28]  System: You have 11 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 11
[10/14/2025 07:28]  System: You have 10 stealth steps remaining.
[10/14/2025 07:28]  Razor: [10 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 10
[10/14/2025 07:28]  System: You have 9 stealth steps remaining.
[10/14/2025 07:28]  Razor: [9 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 9
[10/14/2025 07:28]  System: You have 8 stealth steps remaining.
[10/14/2025 07:28]  Razor: [8 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 8
[10/14/2025 07:28]  System: You have 7 stealth steps remaining.
[10/14/2025 07:28]  Razor: [7 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 7
[10/14/2025 07:28]  System: You have 6 stealth steps remaining.
[10/14/2025 07:28]  Razor: [6 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 6
[10/14/2025 07:28]  System: You have 5 stealth steps remaining.
[10/14/2025 07:28]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:28]  [Razor]: Steps: 5
[10/14/2025 07:28]  System: You have 4 stealth steps remaining.
[10/14/2025 07:28]  Razor: [4 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 4
[10/14/2025 07:28]  System: You will automatically stealth when out of steps.
[10/14/2025 07:28]  Razor: [Auto-stealth ready.]
[10/14/2025 07:28]  System: You have 3 stealth steps remaining.
[10/14/2025 07:28]  Razor: [3 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 3
[10/14/2025 07:28]  System: You have 2 stealth steps remaining.
[10/14/2025 07:28]  Razor: [2 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 2
[10/14/2025 07:28]  System: You have 1 stealth steps remaining.
[10/14/2025 07:28]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:28]  [Razor]: Steps: 1
[10/14/2025 07:28]  an ophidian warrior: an ophidian warrior
[10/14/2025 07:28]  System: You have 0 stealth steps remaining.
[10/14/2025 07:28]  System: You feel ready to continue stealthing.
[10/14/2025 07:28]  Razor: [Stop!]
[10/14/2025 07:28]  [Razor]: Steps: 0
[10/14/2025 07:28]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:28]  System: You have 23 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 23
[10/14/2025 07:28]  You see: an ophidian warrior corpse
[10/14/2025 07:28]  System: You have 22 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 22
[10/14/2025 07:28]  You see: an adder corpse
[10/14/2025 07:28]  System: You have 21 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 21
[10/14/2025 07:28]  System: You have 20 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 20
[10/14/2025 07:28]  System: You have 19 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 19
[10/14/2025 07:28]  System: You have 18 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 18
[10/14/2025 07:28]  an ophidian warrior: an ophidian warrior
[10/14/2025 07:28]  System: You have 17 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 17
[10/14/2025 07:28]  System: You have 16 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 16
[10/14/2025 07:28]  System: You have 15 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 15
[10/14/2025 07:28]  System: You have 14 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 14
[10/14/2025 07:28]  an ophidian mage: an ophidian mage
[10/14/2025 07:28]  System: You have 13 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 13
[10/14/2025 07:28]  System: You have 12 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 12
[10/14/2025 07:28]  System: You have 11 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 11
[10/14/2025 07:28]  System: You have 10 stealth steps remaining.
[10/14/2025 07:28]  Razor: [10 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 10
[10/14/2025 07:28]  System: You have 9 stealth steps remaining.
[10/14/2025 07:28]  Razor: [9 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 9
[10/14/2025 07:28]  System: You have 8 stealth steps remaining.
[10/14/2025 07:28]  Razor: [8 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 8
[10/14/2025 07:28]  You see: an ophidian warrior corpse
[10/14/2025 07:28]  System: You have 7 stealth steps remaining.
[10/14/2025 07:28]  Razor: [7 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 7
[10/14/2025 07:28]  System: You have 6 stealth steps remaining.
[10/14/2025 07:28]  Razor: [6 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 6
[10/14/2025 07:28]  You see: an ophidian mage corpse
[10/14/2025 07:28]  You see: an ophidian warrior corpse
[10/14/2025 07:28]  System: You have 5 stealth steps remaining.
[10/14/2025 07:28]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 07:28]  [Razor]: Steps: 5
[10/14/2025 07:28]  System: You have 4 stealth steps remaining.
[10/14/2025 07:28]  Razor: [4 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 4
[10/14/2025 07:28]  You see: a dusk drake corpse
[10/14/2025 07:28]  System: You have 3 stealth steps remaining.
[10/14/2025 07:28]  Razor: [3 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 3
[10/14/2025 07:28]  You see: a dusk drake corpse
[10/14/2025 07:28]  System: You have 2 stealth steps remaining.
[10/14/2025 07:28]  Razor: [2 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 2
[10/14/2025 07:28]  System: You have 1 stealth steps remaining.
[10/14/2025 07:28]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 07:28]  [Razor]: Steps: 1
[10/14/2025 07:28]  System: You will automatically stealth when out of steps.
[10/14/2025 07:28]  Razor: [Auto-stealth ready.]
[10/14/2025 07:28]  System: You have 0 stealth steps remaining.
[10/14/2025 07:28]  System: You feel ready to continue stealthing.
[10/14/2025 07:28]  Razor: [Stop!]
[10/14/2025 07:28]  [Razor]: Steps: 0
[10/14/2025 07:28]  System: You begin to move quietly. (24 steps)
[10/14/2025 07:28]  System: You have 23 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 23
[10/14/2025 07:28]  System: You have 22 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 22
[10/14/2025 07:28]  System: You have 21 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 21
[10/14/2025 07:28]  System: You have 20 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 20
[10/14/2025 07:28]  System: You have 19 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 19
[10/14/2025 07:28]  You see: a drake corpse
[10/14/2025 07:28]  You see: a drake corpse
[10/14/2025 07:28]  System: You have 18 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 18
[10/14/2025 07:28]  System: You have 17 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 17
[10/14/2025 07:28]  System: You have 16 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 16
[10/14/2025 07:28]  System: You have 15 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 15
[10/14/2025 07:28]  System: You have 14 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 14
[10/14/2025 07:28]  System: You have 13 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 13
[10/14/2025 07:28]  System: You have 12 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 12
[10/14/2025 07:28]  You see: a dusk drake corpse
[10/14/2025 07:28]  System: You have 11 stealth steps remaining.
[10/14/2025 07:28]  [Razor]: Steps: 11
[10/14/2025 07:28]  System: You have 10 stealth steps remaining.
[10/14/2025 07:28]  Razor: [10 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 10
[10/14/2025 07:28]  a drake: a drake
[10/14/2025 07:28]  System: You have 9 stealth steps remaining.
[10/14/2025 07:28]  Razor: [9 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 9
[10/14/2025 07:28]  System: You are now under the protection of the town guards.
[10/14/2025 07:28]  [Razor]: Steps: 9
[10/14/2025 07:28]  Candida: Candida the miner
[10/14/2025 07:28]  Lev: Lev the fisherman
[10/14/2025 07:28]  Kaili: Kaili the blacksmith
[10/14/2025 07:28]  Troglodyte Rind: Troglodyte Rind
[10/14/2025 07:28]  Cayla: Cayla the mage
[10/14/2025 07:28]  Trind: Trind the banker
[10/14/2025 07:28]  Cam: Cam the Town Crier
[10/14/2025 07:28]  Jacinda: Jacinda the healer
[10/14/2025 07:28]  Migina: Migina the provisioner
[10/14/2025 07:28]  Bitalo: Bitalo the beggar
[10/14/2025 07:28]  Braden: Braden the tailor
[10/14/2025 07:28]  Jacelyn: Jacelyn the scribe
[10/14/2025 07:28]  Like: Like the tavern keeper
[10/14/2025 07:28]  Zaide: Zaide the tinker
[10/14/2025 07:28]  Camelia: Camelia the jeweler
[10/14/2025 07:28]  Matana: Matana the innkeeper
[10/14/2025 07:28]  Tertia: Tertia the bowyer
[10/14/2025 07:28]  Takiyah: Takiyah the carpenter
[10/14/2025 07:28]  Yesmina: Yesmina the bard
[10/14/2025 07:28]  Deva: Deva the waiter
[10/14/2025 07:28]  Maria: Maria the architect
[10/14/2025 07:28]  Rusty: Rusty the farmer
[10/14/2025 07:28]  System: Now tracking: Cornwallis X (61 spaces to target)
[10/14/2025 07:28]  System: You have 8 stealth steps remaining.
[10/14/2025 07:28]  Razor: [8 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 8
[10/14/2025 07:28]  Chapman: Chapman the real estate broker
[10/14/2025 07:28]  System: You have 7 stealth steps remaining.
[10/14/2025 07:28]  Razor: [7 Steps...]
[10/14/2025 07:28]  [Razor]: Steps: 7
[10/14/2025 07:28]  System: You will automatically stealth when out of steps.
[10/14/2025 07:28]  Razor: [Auto-stealth ready.]
[10/14/2025 07:28]  Val Killmore: Val Killmore
[10/14/2025 07:28]  GawdHammer: You summon your mount.
[10/14/2025 07:28]  You see: forest ostard mount token
[10/14/2025 07:28]  You see: (monster hunter hue 2817)
[10/14/2025 07:28]  You see: [blessed]
[10/14/2025 07:28]  BobJiuJitsuOssss: [Initiate, who]
[10/14/2025 07:28]  BobJiuJitsuOssss: BobJiuJitsuOssss
[10/14/2025 07:28]  System: 68° 22'N, 177° 53'W
[10/14/2025 07:28]  System: Your skill in Tactics has decreased by 78.0.  It is now 0.0.
[10/14/2025 07:28]  GawdHammer: Kal Ort Por
[10/14/2025 07:28]  System: Distance to destination: 59 steps.
[10/14/2025 07:28]  System: Now tracking: Cornwallis X (59 spaces to target)
[10/14/2025 07:28]  System: You have left the protection of the town guards.
[10/14/2025 07:28]  a wolf: a wolf
[10/14/2025 07:28]  an orc: an orc
[10/14/2025 07:28]  a great hart: a great hart
[10/14/2025 07:28]  a grizzly bear: a grizzly bear
[10/14/2025 07:28]  a horse: a horse
[10/14/2025 07:28]  an eagle: an eagle
[10/14/2025 07:28]  a pack horse: (tame)
[10/14/2025 07:28]  a pack horse: [435/2000 stones]
[10/14/2025 07:28]  a pack horse: [119/125 items]
[10/14/2025 07:28]  a pack horse: a pack horse
[10/14/2025 07:28]  a cougar: a cougar
[10/14/2025 07:28]  a giant rat: a giant rat
[10/14/2025 07:28]  a headless: a headless
[10/14/2025 07:28]  a pack horse: (tame)
[10/14/2025 07:28]  a pack horse: [453/2000 stones]
[10/14/2025 07:28]  a pack horse: [125/125 items]
[10/14/2025 07:28]  a pack horse: a pack horse
[10/14/2025 07:28]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 07:28]  You see: pouch
[10/14/2025 07:28]  You see: (0 items, 0 stones)
[10/14/2025 07:28]  You see: a storage shelf
[10/14/2025 07:28]  You see: [secure]
[10/14/2025 07:28]  System: Which container do you wish to restock this container from? (you may target yourself or a nearby friendly pack animal)
[10/14/2025 07:28]  System: 25 items were added.
[10/14/2025 07:28]  System: You may now use another smoke bomb.
[10/14/2025 07:28]  [Razor]: Warning: Black Pearl amount is now 0!
[10/14/2025 07:28]  [Razor]: Warning: Ginseng amount is now 0!
[10/14/2025 07:28]  [Razor]: Warning: Sulfurous Ash amount is now 0!
[10/14/2025 07:28]  You see: pouch
[10/14/2025 07:28]  You see: (41 items, 493 stones)
[10/14/2025 07:29]  [Razor]: Ignore List cleared
[10/14/2025 07:29]  You see: pouch
[10/14/2025 07:29]  You see: (41 items, 546 stones)
[10/14/2025 07:29]  [Razor]: drop - Not holding anything
[10/14/2025 07:29]  Razor: Select sort container
[10/14/2025 07:29]  [Razor]: Select target for variable 'sort_container'
[10/14/2025 07:29]  [Razor]: 'sort_container' script variable updated to '0x4CA3CF9A'
[10/14/2025 07:29]  Razor: unidentified leather arms
[10/14/2025 07:29]  [Razor]: Added 1286256458 to ignore list
[10/14/2025 07:29]  Razor: unidentified ringmail arms
[10/14/2025 07:29]  [Razor]: Added 1285956201 to ignore list
[10/14/2025 07:29]  Razor: unidentified platemail arms
[10/14/2025 07:29]  [Razor]: Added 1285872600 to ignore list
[10/14/2025 07:29]  Razor: unidentified female platemail chest
[10/14/2025 07:29]  [Razor]: Added 1285832867 to ignore list
[10/14/2025 07:29]  Razor: unidentified platemail gloves
[10/14/2025 07:29]  [Razor]: Added 1286048841 to ignore list
[10/14/2025 07:29]  Razor: unidentified platemail skirt
[10/14/2025 07:29]  [Razor]: Added 1286117446 to ignore list
[10/14/2025 07:29]  Razor: unidentified boneshard staff
[10/14/2025 07:29]  [Razor]: Added 1285942838 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified celestial staff
[10/14/2025 07:29]  [Razor]: Added 1286186082 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified chaos staff
[10/14/2025 07:29]  [Razor]: Added 1286132823 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified heavy crossbow
[10/14/2025 07:29]  [Razor]: Added 1286444874 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified pistol crossbow
[10/14/2025 07:29]  [Razor]: Added 1285913663 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified assassin's knife
[10/14/2025 07:29]  [Razor]: Added 1286389930 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified assassin's knife
[10/14/2025 07:29]  [Razor]: Added 1285902088 to ignore list
[10/14/2025 07:29]  a crow: a crow
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified hunting knife
[10/14/2025 07:29]  [Razor]: Added 1286201120 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified hunting knife
[10/14/2025 07:29]  [Razor]: Added 1286356004 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified kryss
[10/14/2025 07:29]  [Razor]: Added 1286140650 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified hurdy-gurdy (500/500 uses remaining)
[10/14/2025 07:29]  [Razor]: Added 1286215216 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified lute (500/500 uses remaining)
[10/14/2025 07:29]  [Razor]: Added 1285847321 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified flanged mace
[10/14/2025 07:29]  [Razor]: Added 1286172277 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified giant club
[10/14/2025 07:29]  a pack horse: (tame)
[10/14/2025 07:29]  a pack horse: [453/2000 stones]
[10/14/2025 07:29]  a pack horse: [125/125 items]
[10/14/2025 07:29]  a pack horse: a pack horse
[10/14/2025 07:29]  [Razor]: Added 1285865042 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified great sledge
[10/14/2025 07:29]  [Razor]: Added 1286345938 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified quarter staff
[10/14/2025 07:29]  [Razor]: Added 1286377046 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified war mace
[10/14/2025 07:29]  [Razor]: Added 1286403180 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified magic spellbook
[10/14/2025 07:29]  [Razor]: Added 1285966747 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified magic spellbook
[10/14/2025 07:29]  [Razor]: Added 1286070733 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified bronze shield
[10/14/2025 07:29]  [Razor]: Added 1286484054 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified broadsword
[10/14/2025 07:29]  [Razor]: Added 1286096963 to ignore list
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: unidentified viking sword
[10/14/2025 07:29]  [Razor]: Added 1286333560 to ignore list
[10/14/2025 07:29]  : brambles
[10/14/2025 07:29]  System: That container cannot hold more items.
[10/14/2025 07:29]  Razor: amethyst : 2
[10/14/2025 07:29]  [Razor]: Added 1285902087 to ignore list
[10/14/2025 07:29]  Razor: tourmaline : 2
[10/14/2025 07:29]  [Razor]: Added 1286117445 to ignore list
[10/14/2025 07:29]  Razor: sapphire : 6
[10/14/2025 07:29]  [Razor]: Added 1285832866 to ignore list
[10/14/2025 07:29]  Razor: citrine : 12
[10/14/2025 07:29]  [Razor]: Added 1285872598 to ignore list
[10/14/2025 07:29]  Razor: amber : 4
[10/14/2025 07:29]  [Razor]: Added 1286140649 to ignore list
[10/14/2025 07:29]  Razor: diamond : 2
[10/14/2025 07:29]  [Razor]: Added 1285956200 to ignore list
[10/14/2025 07:29]  Razor: ruby : 2
[10/14/2025 07:29]  [Razor]: Added 1286172276 to ignore list
[10/14/2025 07:29]  Razor: star sapphire : 4
[10/14/2025 07:29]  [Razor]: Added 1285942837 to ignore list
[10/14/2025 07:29]  Razor: trap rune (Trap damage increased by 15.0%) [double click to place]
[10/14/2025 07:29]  [Razor]: Added 1285490542 to ignore list
[10/14/2025 07:29]  Razor: rage rune (Melee damage increased by 10.0%) [double click to place]
[10/14/2025 07:29]  [Razor]: Added 1285966748 to ignore list
[10/14/2025 07:29]  Razor: an arcane scroll : 3
[10/14/2025 07:29]  [Razor]: Added 1286333561 to ignore list
[10/14/2025 07:29]  System: KGB has completed the achievement: Leatherworker (Advanced).
[10/14/2025 07:29]  System: You deposit 42,182 gold into your bank box.
[10/14/2025 07:29]  Razor: Done.
[10/14/2025 07:29]  a pack horse: (tame)
[10/14/2025 07:29]  a pack horse: [458/2000 stones]
[10/14/2025 07:29]  a pack horse: [125/125 items]
[10/14/2025 07:29]  a pack horse: a pack horse
[10/14/2025 07:29]  a hind: a hind
[10/14/2025 07:29]  GawdHammer: Legendary Backstabber
[10/14/2025 07:29]  GawdHammer: Lord GawdHammer
[10/14/2025 07:29]  a pack horse: (tame)
[10/14/2025 07:29]  a pack horse: [453/2000 stones]
[10/14/2025 07:29]  a pack horse: [125/125 items]
[10/14/2025 07:29]  a pack horse: a pack horse
[10/14/2025 07:29]  : marble floor
[10/14/2025 07:29]  PaigeMe: [Recruit, OL]
[10/14/2025 07:29]  PaigeMe: PaigeMe
[10/14/2025 07:29]  System: PaigeMe : rejoined the party.
[10/14/2025 07:30]  a wolf: a wolf
[10/14/2025 07:30]  System: Filbert has completed the achievement: Guardian of Shadowspire (Basic).
[10/14/2025 07:30]  an ettin: an ettin
[10/14/2025 07:30]  a great hart: a great hart
[10/14/2025 07:30]  an ettin: an ettin
[10/14/2025 07:30]  a wolf: a wolf
[10/14/2025 07:30]  System: Folcan dex has completed the achievement: Guardian of Nusero (Basic).
[10/14/2025 07:30]  Razor: Select equipment container
[10/14/2025 07:30]  [Razor]: Select target for variable 'equipment_container'
[10/14/2025 07:30]  [Razor]: 'equipment_container' script variable updated to '0x509AD3E1'
[10/14/2025 07:31]  You see: pouch
[10/14/2025 07:31]  You see: (23 items, 89 stones)
[10/14/2025 07:31]  [Razor]: Ignore List cleared
[10/14/2025 07:31]  [Razor]: drop - Not holding anything
[10/14/2025 07:31]  Razor: Select sort container
[10/14/2025 07:31]  [Razor]: Select target for variable 'sort_container'
[10/14/2025 07:31]  [Razor]: 'sort_container' script variable updated to '0x4CA3CF9A'
[10/14/2025 07:31]  Razor: unidentified boneshard staff
[10/14/2025 07:31]  [Razor]: Added 1285942838 to ignore list
[10/14/2025 07:31]  Razor: unidentified celestial staff
[10/14/2025 07:31]  [Razor]: Added 1286186082 to ignore list
[10/14/2025 07:31]  Razor: unidentified chaos staff
[10/14/2025 07:31]  [Razor]: Added 1286132823 to ignore list
[10/14/2025 07:31]  Razor: unidentified heavy crossbow
[10/14/2025 07:31]  [Razor]: Added 1286444874 to ignore list
[10/14/2025 07:31]  Razor: unidentified pistol crossbow
[10/14/2025 07:31]  [Razor]: Added 1285913663 to ignore list
[10/14/2025 07:31]  Razor: unidentified assassin's knife
[10/14/2025 07:31]  [Razor]: Added 1286389930 to ignore list
[10/14/2025 07:31]  Razor: unidentified assassin's knife
[10/14/2025 07:31]  [Razor]: Added 1285902088 to ignore list
[10/14/2025 07:31]  Razor: unidentified hunting knife
[10/14/2025 07:31]  [Razor]: Added 1286201120 to ignore list
[10/14/2025 07:31]  Razor: unidentified hunting knife
[10/14/2025 07:31]  [Razor]: Added 1286356004 to ignore list
[10/14/2025 07:31]  Razor: unidentified kryss
[10/14/2025 07:31]  [Razor]: Added 1286140650 to ignore list
[10/14/2025 07:31]  Razor: unidentified hurdy-gurdy (500/500 uses remaining)
[10/14/2025 07:31]  [Razor]: Added 1286215216 to ignore list
[10/14/2025 07:31]  Razor: unidentified lute (500/500 uses remaining)
[10/14/2025 07:31]  [Razor]: Added 1285847321 to ignore list
[10/14/2025 07:31]  Razor: unidentified flanged mace
[10/14/2025 07:31]  [Razor]: Added 1286172277 to ignore list
[10/14/2025 07:31]  Razor: unidentified giant club
[10/14/2025 07:31]  [Razor]: Added 1285865042 to ignore list
[10/14/2025 07:31]  Razor: unidentified great sledge
[10/14/2025 07:31]  [Razor]: Added 1286345938 to ignore list
[10/14/2025 07:31]  Razor: unidentified quarter staff
[10/14/2025 07:31]  [Razor]: Added 1286377046 to ignore list
[10/14/2025 07:31]  Razor: unidentified war mace
[10/14/2025 07:31]  [Razor]: Added 1286403180 to ignore list
[10/14/2025 07:31]  Razor: unidentified magic spellbook
[10/14/2025 07:31]  [Razor]: Added 1285966747 to ignore list
[10/14/2025 07:31]  Razor: unidentified magic spellbook
[10/14/2025 07:31]  [Razor]: Added 1286070733 to ignore list
[10/14/2025 07:31]  Razor: unidentified bronze shield
[10/14/2025 07:31]  [Razor]: Added 1286484054 to ignore list
[10/14/2025 07:31]  Razor: unidentified broadsword
[10/14/2025 07:31]  [Razor]: Added 1286096963 to ignore list
[10/14/2025 07:31]  You see: pouch
[10/14/2025 07:31]  You see: (2 items, 5 stones)
[10/14/2025 07:31]  Razor: unidentified viking sword
[10/14/2025 07:31]  [Razor]: Added 1286333560 to ignore list
[10/14/2025 07:31]  GawdHammer: -1
[10/14/2025 07:31]  System: You now have 0 trapped pouches remaining.
[10/14/2025 07:31]  System: The world will save in 15 seconds.
[10/14/2025 07:31]  Razor: Done.
[10/14/2025 07:31]  You see: pouch
[10/14/2025 07:31]  You see: (1 items, 1 stones)
[10/14/2025 07:31]  You see: wooden chest
[10/14/2025 07:31]  You see: [secure]
[10/14/2025 07:31]  You see: (103 items, 616 stones)
[10/14/2025 07:31]  You see: survival gear : 4
[10/14/2025 07:31]  System: The world is saving, please wait.
[10/14/2025 07:31]  System: World save complete. The entire process took 3.7 seconds.
[10/14/2025 07:31]  a giant rat: a giant rat
[10/14/2025 07:31]  You see: research materials : 6
[10/14/2025 07:31]  You see: (double-click to research)
[10/14/2025 07:31]  a horse: a horse
[10/14/2025 07:31]  You see: a bank deposit safe
[10/14/2025 07:31]  You see: (138,657 gold balance)
[10/14/2025 07:31]  You see: [locked down]
[10/14/2025 07:32]  You see: a storage shelf
[10/14/2025 07:32]  You see: [secure]
[10/14/2025 07:32]  System: Resupply complete.
[10/14/2025 07:33]  a wolf: a wolf
[10/14/2025 07:33]  a forest ostard: a forest ostard
[10/14/2025 07:33]  System: Meat Locker has completed the achievement: Humanoid Slayer (Basic).
[10/14/2025 07:33]  a wolf: a wolf
[10/14/2025 07:35]  a wolf: a wolf
[10/14/2025 07:36]  a wolf: a wolf
[10/14/2025 07:37]  System: Mr.Yost has completed the achievement: Paragon Slayer (Basic).
[10/14/2025 07:37]  System: Odd Jobb has completed the achievement: Relic Hunter (Basic).
[10/14/2025 07:38]  a grizzly bear: a grizzly bear
[10/14/2025 07:38]  System: Katastro has completed the achievement: Versatility (Basic).
[10/14/2025 07:38]  a hind: a hind
[10/14/2025 07:39]  System: Lizapodliza has completed the achievement: Aspect Mastery (Basic).
[10/14/2025 07:39]  a giant rat: a giant rat
[10/14/2025 07:40]  a crow: a crow
[10/14/2025 07:40]  System: JabbaWockeez has completed the achievement: Triggered (Basic).
[10/14/2025 07:41]  a hind: a hind
[10/14/2025 07:42]  a cougar: a cougar
[10/14/2025 07:42]  a wolf: a wolf
[10/14/2025 07:43]  System: Bootie has completed the achievement: Captain (Basic).
[10/14/2025 07:43]  System: Drumheller has completed the achievement: Culinary Savvy (Basic).
[10/14/2025 07:43]  a wolf: a wolf
[10/14/2025 07:44]  a grizzly bear: a grizzly bear
[10/14/2025 07:46]  System: [DF] A Dungeon Flashpoint will begin in 15 minutes. Type [BattleHotbar to view the Battle Hotbar for this event.
[10/14/2025 07:46]  System: The world will save in 15 seconds.
[10/14/2025 07:46]  System: The world is saving, please wait.
[10/14/2025 07:46]  System: World save complete. The entire process took 3.5 seconds.
[10/14/2025 07:47]  System: Sonics has completed the achievement: Backstabber (Advanced).
[10/14/2025 07:48]  a great hart: a great hart
[10/14/2025 07:48]  a hind: a hind
[10/14/2025 07:49]  System: Rage Entreri has completed the achievement: Salvager (Epic).
[10/14/2025 07:50]  : marble floor
[10/14/2025 07:51]  a grizzly bear: a grizzly bear
[10/14/2025 07:51]  a grizzly bear: a grizzly bear
[10/14/2025 07:52]  System: Justus Schottel has completed the achievement: Scribe (Basic).
[10/14/2025 07:52]  a grizzly bear: a grizzly bear
[10/14/2025 07:53]  a hind: a hind
[10/14/2025 07:53]  System: Grimmire has completed the achievement: Seafarer Initiate.
[10/14/2025 07:55]  System: Melphena has completed the achievement: Savant (Basic).
[10/14/2025 07:55]  System: [DF] A Dungeon Flashpoint will begin in 5 minutes. Type [BattleHotbar to view the Battle Hotbar for this event.
[10/14/2025 07:56]  System: Glow has completed the achievement: Guardian of Pulma (Basic).
[10/14/2025 07:56]  a great hart: a great hart
[10/14/2025 07:56]  System: Grimmire has completed the achievement: Pirate (Basic).
[10/14/2025 07:56]  a hind: a hind
[10/14/2025 07:58]  an eagle: an eagle
[10/14/2025 07:58]  System: GrannyFlaps has completed the achievement: Paragon Slayer (Basic).
[10/14/2025 07:58]  an eagle: an eagle
[10/14/2025 07:59]  a hind: a hind
[10/14/2025 08:00]  System: [DF] A Dungeon Flashpoint for The Mausoleum has begun! Type [BattleHotbar to view the Battle Hotbar for this event.
[10/14/2025 08:01]  System: The world will save in 15 seconds.
[10/14/2025 08:01]  System: The world is saving, please wait.
[10/14/2025 08:01]  System: World save complete. The entire process took 3.4 seconds.
[10/14/2025 08:01]  a hind: a hind
[10/14/2025 08:01]  a hind: a hind
[10/14/2025 08:03]  a grizzly bear: a grizzly bear
[10/14/2025 08:04]  System: Kraftee has completed the achievement: Order of Armorers Dignitary.
[10/14/2025 08:06]  System: Target the container to Grab items into.
[10/14/2025 08:06]  System: Grab Bag set: 1286639051
[10/14/2025 08:06]  System: Opening door...
[10/14/2025 08:06]  System: As the owner, you refresh the house and its contents.
[10/14/2025 08:06]  a grizzly bear: a grizzly bear
[10/14/2025 08:06]  [Razor]: Queuing action request 1... 0.7 seconds left.
[10/14/2025 08:06]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:10]  GawdHammer: Kal Ort Por
[10/14/2025 08:10]  System: One or more of your pack animals is not allowed in this location.
[10/14/2025 08:10]  GawdHammer: You dismiss your mount.
[10/14/2025 08:10]  System: Your skill in Tactics has increased by 78.0.  It is now 78.0.
[10/14/2025 08:10]  You see: a stonescale tribal corpse
[10/14/2025 08:10]  You see: the remains of dailyllama
[10/14/2025 08:10]  a stonescale tribal: a stonescale tribal
[10/14/2025 08:10]  a dragon whelp: a dragon whelp
[10/14/2025 08:10]  You see: a monitor hatchling corpse
[10/14/2025 08:10]  You see: a drake whelp corpse
[10/14/2025 08:10]  You see: a drake whelp corpse
[10/14/2025 08:10]  You see: a monitor corpse
[10/14/2025 08:10]  You see: a stonescale tribal corpse
[10/14/2025 08:10]  You see: a monitor corpse
[10/14/2025 08:10]  You see: a monitor corpse
[10/14/2025 08:10]  You see: a primordial corpse
[10/14/2025 08:10]  You see: a monitor corpse
[10/14/2025 08:10]  a primordial whelp: a primordial whelp
[10/14/2025 08:10]  You see: a primordial whelp corpse
[10/14/2025 08:10]  a monitor: a monitor
[10/14/2025 08:10]  a dragon whelp: a dragon whelp
[10/14/2025 08:10]  an army ant: an army ant
[10/14/2025 08:10]  You see: the remains of KaaPow
[10/14/2025 08:10]  a dragon whelp: a dragon whelp
[10/14/2025 08:10]  You see: the remains of NicktheReal
[10/14/2025 08:10]  You see: an earth drake corpse
[10/14/2025 08:10]  You see: the remains of Labyrinthh
[10/14/2025 08:10]  Kurwa Pierdole: [Initiate, who]
[10/14/2025 08:10]  Kurwa Pierdole: Kurwa Pierdole
[10/14/2025 08:10]  an earth drake: an earth drake
[10/14/2025 08:10]  You see: a drake corpse
[10/14/2025 08:10]  You see: a drake corpse
[10/14/2025 08:10]  an army ant: an army ant
[10/14/2025 08:10]  an earth drake: an earth drake
[10/14/2025 08:10]  a drake: a drake
[10/14/2025 08:10]  a chameleon: a chameleon
[10/14/2025 08:10]  an earth drake: an earth drake
[10/14/2025 08:10]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:10]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:10]  [Razor]: Steps: 0
[10/14/2025 08:10]  [Razor]: Steps: 0
[10/14/2025 08:10]  System: You are no longer hindered.
[10/14/2025 08:10]  System: You have 29 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 29
[10/14/2025 08:10]  System: You have 28 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 28
[10/14/2025 08:10]  System: You have 27 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 27
[10/14/2025 08:10]  System: You have 26 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 26
[10/14/2025 08:10]  System: You have 25 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: New target set.
[10/14/2025 08:10]  Razor: Target: an earth drake
[10/14/2025 08:10]  [Razor]: Steps: 25
[10/14/2025 08:10]  System: You have 24 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 24
[10/14/2025 08:10]  System: You have 23 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: New target set.
[10/14/2025 08:10]  Razor: Target: an earth drake
[10/14/2025 08:10]  [Razor]: Steps: 23
[10/14/2025 08:10]  You see: a drake corpse
[10/14/2025 08:10]  Razor: Attack: an earth drake
[10/14/2025 08:10]  System: You have 22 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 22
[10/14/2025 08:10]  System: You have 21 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 21
[10/14/2025 08:10]  You see: a drake corpse
[10/14/2025 08:10]  System: You have 20 stealth steps remaining.
[10/14/2025 08:10]  [Razor]: Steps: 20
[10/14/2025 08:10]  a drake: a drake
[10/14/2025 08:10]  System: You strike your target from the shadows!
[10/14/2025 08:10]  GawdHammer: You steal 243 gold coin.
[10/14/2025 08:10]  System: You steal 243 gold coin.
[10/14/2025 08:10]  GawdHammer: (Shadow Aspect 66,491.9/250000 xp)
[10/14/2025 08:10]  System: You have lost a little karma.
[10/14/2025 08:10]  System: You have gained a lot of karma.
[10/14/2025 08:10]  GawdHammer: (Shadow Aspect 66,510.5/250000 xp)
[10/14/2025 08:10]  System: -6000
[10/14/2025 08:10]  You see: an earth drake corpse
[10/14/2025 08:10]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:10]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:10]  You see: the remains of NicktheReal
[10/14/2025 08:10]  You see: an earth drake corpse
[10/14/2025 08:10]  You see: the remains of Labyrinthh
[10/14/2025 08:10]  You see: a drake corpse
[10/14/2025 08:10]  a drake: a drake
[10/14/2025 08:11]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:11]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:11]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:11]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:11]  System: You have 23 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 23
[10/14/2025 08:11]  System: You have 22 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 22
[10/14/2025 08:11]  [Razor]: New target set.
[10/14/2025 08:11]  Razor: Target: a drake
[10/14/2025 08:11]  System: You have 21 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 21
[10/14/2025 08:11]  System: You have 20 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 20
[10/14/2025 08:11]  Razor: Attack: a drake
[10/14/2025 08:11]  System: You have 19 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 19
[10/14/2025 08:11]  System: You have 18 stealth steps remaining.
[10/14/2025 08:11]  System: You strike your target from the shadows!
[10/14/2025 08:11]  GawdHammer: You steal 210 gold coin.
[10/14/2025 08:11]  System: You steal 210 gold coin.
[10/14/2025 08:11]  GawdHammer: (Shadow Aspect 66,514.6/250000 xp)
[10/14/2025 08:11]  System: You have lost a little karma.
[10/14/2025 08:11]  System: You have gained a lot of karma.
[10/14/2025 08:11]  GawdHammer: (Shadow Aspect 66,531.0/250000 xp)
[10/14/2025 08:11]  System: -6000
[10/14/2025 08:11]  You see: a drake corpse
[10/14/2025 08:11]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:11]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:11]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:11]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 08:11]  System: Damage tracker has been reset.
[10/14/2025 08:11]  System: Damage tracker has been stopped.
[10/14/2025 08:11]  System: Damage tracker is now running.
[10/14/2025 08:11]  You see: a drake corpse
[10/14/2025 08:11]  KaaPow: [Initiate, who]
[10/14/2025 08:11]  KaaPow: KaaPow
[10/14/2025 08:11]  a drake: a drake
[10/14/2025 08:11]  You see: a drake corpse
[10/14/2025 08:11]  You see: a drake corpse
[10/14/2025 08:11]  an adder: an adder
[10/14/2025 08:11]  a smoke drake: a smoke drake
[10/14/2025 08:11]  a smoke drake: a smoke drake
[10/14/2025 08:11]  System: You must wait another 4 seconds before you may use another smoke bomb.
[10/14/2025 08:11]  a smoke drake: a smoke drake
[10/14/2025 08:11]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:11]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:11]  [Razor]: New target set.
[10/14/2025 08:11]  Razor: Target: a smoke drake
[10/14/2025 08:11]  System: You may now use another smoke bomb.
[10/14/2025 08:11]  Kurwa Pierdole: [Initiate, who]
[10/14/2025 08:11]  Kurwa Pierdole: Kurwa Pierdole
[10/14/2025 08:11]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:11]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:11]  System: You have 23 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 23
[10/14/2025 08:11]  System: You have 22 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 22
[10/14/2025 08:11]  System: You have 21 stealth steps remaining.
[10/14/2025 08:11]  [Razor]: Steps: 21
[10/14/2025 08:12]  System: You have 20 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 20
[10/14/2025 08:12]  [Razor]: New target set.
[10/14/2025 08:12]  Razor: Attack: a smoke drake
[10/14/2025 08:12]  System: You strike your target from the shadows!
[10/14/2025 08:12]  GawdHammer: You steal 228 gold coin.
[10/14/2025 08:12]  System: You steal 228 gold coin.
[10/14/2025 08:12]  GawdHammer: (Shadow Aspect 66,535.9/250000 xp)
[10/14/2025 08:12]  System: You have lost a little karma.
[10/14/2025 08:12]  System: You have gained a lot of karma.
[10/14/2025 08:12]  GawdHammer: (Shadow Aspect 66,555.4/250000 xp)
[10/14/2025 08:12]  System: -6000
[10/14/2025 08:12]  You see: a smoke drake corpse
[10/14/2025 08:12]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:12]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:12]  [Razor]: New target set.
[10/14/2025 08:12]  Razor: Target: a smoke drake
[10/14/2025 08:12]  System: a Ninja Paragon has completed the achievement: Guardian of Ossuary (Intermediate).
[10/14/2025 08:12]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:12]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:12]  Razor: Attack: a smoke drake
[10/14/2025 08:12]  System: You have 23 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 23
[10/14/2025 08:12]  System: You have 22 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 22
[10/14/2025 08:12]  System: You have 21 stealth steps remaining.
[10/14/2025 08:12]  System: You strike your target from the shadows!
[10/14/2025 08:12]  GawdHammer: You steal 232 gold coin.
[10/14/2025 08:12]  System: You steal 232 gold coin.
[10/14/2025 08:12]  GawdHammer: (Shadow Aspect 66,560.3/250000 xp)
[10/14/2025 08:12]  System: You have lost a little karma.
[10/14/2025 08:12]  System: You have gained a lot of karma.
[10/14/2025 08:12]  GawdHammer: (Shadow Aspect 66,579.8/250000 xp)
[10/14/2025 08:12]  System: -6000
[10/14/2025 08:12]  You see: a smoke drake corpse
[10/14/2025 08:12]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:12]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:12]  GawdHammer: That is out of my line of sight.
[10/14/2025 08:12]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:12]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:12]  an eldritch drake: an eldritch drake
[10/14/2025 08:12]  You see: an eldritch drake corpse
[10/14/2025 08:12]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:12]  an eldritch drake: an eldritch drake
[10/14/2025 08:12]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:12]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:12]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:12]  System: You have 23 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 23
[10/14/2025 08:12]  System: You have 22 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 22
[10/14/2025 08:12]  System: You have 21 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 21
[10/14/2025 08:12]  [Razor]: New target set.
[10/14/2025 08:12]  Razor: Target: an eldritch drake
[10/14/2025 08:12]  System: You have 20 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 20
[10/14/2025 08:12]  System: You have 19 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 19
[10/14/2025 08:12]  System: You have 18 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 18
[10/14/2025 08:12]  an eldritch drake: an eldritch drake
[10/14/2025 08:12]  System: You have 17 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 17
[10/14/2025 08:12]  System: You have 16 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 16
[10/14/2025 08:12]  You see: an eldritch drake corpse
[10/14/2025 08:12]  System: You have 15 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 15
[10/14/2025 08:12]  System: You have 14 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 14
[10/14/2025 08:12]  System: You have 13 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 13
[10/14/2025 08:12]  Razor: Attack: an eldritch drake
[10/14/2025 08:12]  System: You strike your target from the shadows!
[10/14/2025 08:12]  GawdHammer: You steal 326 gold coin.
[10/14/2025 08:12]  System: You steal 326 gold coin.
[10/14/2025 08:12]  GawdHammer: (Shadow Aspect 66,586.1/250000 xp)
[10/14/2025 08:12]  System: You have lost a little karma.
[10/14/2025 08:12]  System: You have gained a very large amount of karma.
[10/14/2025 08:12]  GawdHammer: (Shadow Aspect 66,611.4/250000 xp)
[10/14/2025 08:12]  System: -6000
[10/14/2025 08:12]  You see: an eldritch drake corpse
[10/14/2025 08:12]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:12]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:12]  [Razor]: New target set.
[10/14/2025 08:12]  Razor: Target: an eldritch drake
[10/14/2025 08:12]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:12]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:12]  System: You have 23 stealth steps remaining.
[10/14/2025 08:12]  [Razor]: Steps: 23
[10/14/2025 08:12]  System: You have 22 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 22
[10/14/2025 08:13]  System: You have 21 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 21
[10/14/2025 08:13]  Razor: Attack: an eldritch drake
[10/14/2025 08:13]  System: You will automatically stealth when out of steps.
[10/14/2025 08:13]  Razor: [Auto-stealth ready.]
[10/14/2025 08:13]  System: You strike your target from the shadows!
[10/14/2025 08:13]  GawdHammer: You steal 309 gold coin.
[10/14/2025 08:13]  System: You steal 309 gold coin.
[10/14/2025 08:13]  GawdHammer: (Shadow Aspect 66,617.7/250000 xp)
[10/14/2025 08:13]  System: You have lost a little karma.
[10/14/2025 08:13]  System: You have gained a very large amount of karma.
[10/14/2025 08:13]  GawdHammer: (Shadow Aspect 66,642.9/250000 xp)
[10/14/2025 08:13]  System: -6000
[10/14/2025 08:13]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:13]  You see: an eldritch drake corpse
[10/14/2025 08:13]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:13]  System: Rockhopper has completed the achievement: Culinary Savvy (Basic).
[10/14/2025 08:13]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:13]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:13]  System: You have 23 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 23
[10/14/2025 08:13]  [Razor]: New target set.
[10/14/2025 08:13]  Razor: Target: an eldritch drake
[10/14/2025 08:13]  System: You have 22 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 22
[10/14/2025 08:13]  System: You have 21 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 21
[10/14/2025 08:13]  System: You have 20 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 20
[10/14/2025 08:13]  System: You have 19 stealth steps remaining.
[10/14/2025 08:13]  Razor: Attack: an eldritch drake
[10/14/2025 08:13]  [Razor]: Steps: 19
[10/14/2025 08:13]  System: You have 18 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 18
[10/14/2025 08:13]  System: You have 17 stealth steps remaining.
[10/14/2025 08:13]  System: You strike your target from the shadows!
[10/14/2025 08:13]  GawdHammer: You steal 301 gold coin.
[10/14/2025 08:13]  System: You steal 301 gold coin.
[10/14/2025 08:13]  GawdHammer: (Shadow Aspect 66,649.2/250000 xp)
[10/14/2025 08:13]  System: You have lost a little karma.
[10/14/2025 08:13]  System: You have gained a very large amount of karma.
[10/14/2025 08:13]  GawdHammer: (Shadow Aspect 66,674.5/250000 xp)
[10/14/2025 08:13]  System: -6000
[10/14/2025 08:13]  You see: an eldritch drake corpse
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:13]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:13]  You see: an eldritch drake corpse
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:13]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:13]  You see: an eldritch drake corpse
[10/14/2025 08:13]  System: Ruined Lady has completed the achievement: Monster Hunter Associate.
[10/14/2025 08:13]  an ember drake: an ember drake
[10/14/2025 08:13]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:13]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:13]  [Razor]: Steps: 0
[10/14/2025 08:13]  System: You are no longer hindered.
[10/14/2025 08:13]  System: You have 29 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 29
[10/14/2025 08:13]  System: You have 28 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: New target set.
[10/14/2025 08:13]  Razor: Target: an ember drake
[10/14/2025 08:13]  [Razor]: Steps: 28
[10/14/2025 08:13]  System: You have 27 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 27
[10/14/2025 08:13]  System: You have 26 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 26
[10/14/2025 08:13]  Razor: Attack: an ember drake
[10/14/2025 08:13]  System: You have 25 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 25
[10/14/2025 08:13]  System: You have 24 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 24
[10/14/2025 08:13]  System: You have 23 stealth steps remaining.
[10/14/2025 08:13]  [Razor]: Steps: 23
[10/14/2025 08:13]  System: You have 22 stealth steps remaining.
[10/14/2025 08:13]  System: You strike your target from the shadows!
[10/14/2025 08:13]  System: You fail to steal from the creature.
[10/14/2025 08:13]  System: You have lost a little karma.
[10/14/2025 08:13]  System: You have gained a lot of karma.
[10/14/2025 08:13]  GawdHammer: (Shadow Aspect 66,692.4/250000 xp)
[10/14/2025 08:13]  System: -6000
[10/14/2025 08:13]  You see: an ember drake corpse
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:13]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:13]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:13]  an ember drake: an ember drake
[10/14/2025 08:13]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:13]  System: You must wait a few moments to use another skill.
[10/14/2025 08:13]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:13]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:13]  [Razor]: New target set.
[10/14/2025 08:13]  Razor: Target: an ember drake
[10/14/2025 08:13]  Kurwa Pierdole: [Initiate, who]
[10/14/2025 08:13]  Kurwa Pierdole: Kurwa Pierdole
[10/14/2025 08:14]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:14]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:14]  System: You have 23 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 23
[10/14/2025 08:14]  System: You have 22 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 22
[10/14/2025 08:14]  System: You have 21 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 21
[10/14/2025 08:14]  System: You have 20 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 20
[10/14/2025 08:14]  System: You have 19 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 19
[10/14/2025 08:14]  System: You have 18 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 18
[10/14/2025 08:14]  System: You have 17 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 17
[10/14/2025 08:14]  System: You have 16 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 16
[10/14/2025 08:14]  System: You have 15 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 15
[10/14/2025 08:14]  System: You have 14 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 14
[10/14/2025 08:14]  Benicio: Monster Hunter Initiate
[10/14/2025 08:14]  Benicio: [Recruit, Yew]
[10/14/2025 08:14]  Benicio: Lord Benicio
[10/14/2025 08:14]  System: You have 13 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 13
[10/14/2025 08:14]  You see: an eldritch drake corpse
[10/14/2025 08:14]  System: You have 12 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 12
[10/14/2025 08:14]  System: You have 11 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 11
[10/14/2025 08:14]  System: You have 10 stealth steps remaining.
[10/14/2025 08:14]  Razor: [10 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 10
[10/14/2025 08:14]  System: You have 9 stealth steps remaining.
[10/14/2025 08:14]  Razor: [9 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 9
[10/14/2025 08:14]  System: You have 8 stealth steps remaining.
[10/14/2025 08:14]  Razor: [8 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 8
[10/14/2025 08:14]  System: You have 7 stealth steps remaining.
[10/14/2025 08:14]  Razor: [7 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 7
[10/14/2025 08:14]  System: You have 6 stealth steps remaining.
[10/14/2025 08:14]  Razor: [6 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 6
[10/14/2025 08:14]  System: You have 5 stealth steps remaining.
[10/14/2025 08:14]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:14]  [Razor]: Steps: 5
[10/14/2025 08:14]  System: You have 4 stealth steps remaining.
[10/14/2025 08:14]  Razor: [4 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 4
[10/14/2025 08:14]  System: You have 3 stealth steps remaining.
[10/14/2025 08:14]  Razor: [3 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 3
[10/14/2025 08:14]  System: You have 2 stealth steps remaining.
[10/14/2025 08:14]  Razor: [2 Steps...]
[10/14/2025 08:14]  [Razor]: Steps: 2
[10/14/2025 08:14]  System: You have 1 stealth steps remaining.
[10/14/2025 08:14]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 08:14]  [Razor]: Steps: 1
[10/14/2025 08:14]  System: You have 0 stealth steps remaining.
[10/14/2025 08:14]  Razor: [Stop!]
[10/14/2025 08:14]  [Razor]: Steps: 0
[10/14/2025 08:14]  System: You feel ready to continue stealthing.
[10/14/2025 08:14]  You see: an eldritch drake corpse
[10/14/2025 08:14]  You see: an eldritch drake corpse
[10/14/2025 08:14]  : boulder
[10/14/2025 08:14]  System: That is too far away.
[10/14/2025 08:14]  System: That is too far away.
[10/14/2025 08:14]  You see: an ember drake corpse
[10/14/2025 08:14]  You see: an eldritch drake corpse
[10/14/2025 08:14]  You see: an ember drake corpse
[10/14/2025 08:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:14]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:14]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:14]  [Razor]: No one matching that was found on your screen.
[10/14/2025 08:14]  [Razor]: New target set.
[10/14/2025 08:14]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:14]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:14]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:14]  System: You have 23 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 23
[10/14/2025 08:14]  System: You have 22 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 22
[10/14/2025 08:14]  System: You have 21 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 21
[10/14/2025 08:14]  System: You have 20 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 20
[10/14/2025 08:14]  System: You have 19 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 19
[10/14/2025 08:14]  System: You have 18 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 18
[10/14/2025 08:14]  System: You have 17 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 17
[10/14/2025 08:14]  System: You have 16 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 16
[10/14/2025 08:14]  System: You have 15 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 15
[10/14/2025 08:14]  System: You have 14 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 14
[10/14/2025 08:14]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:14]  You see: a wyvern corpse
[10/14/2025 08:14]  System: You have 13 stealth steps remaining.
[10/14/2025 08:14]  [Razor]: Steps: 13
[10/14/2025 08:14]  System: You have 12 stealth steps remaining.
[10/14/2025 08:14]  System: You strike your target from the shadows!
[10/14/2025 08:14]  GawdHammer: You steal 260 gold coin.
[10/14/2025 08:14]  System: You steal 260 gold coin.
[10/14/2025 08:14]  GawdHammer: You steal blood distillation.
[10/14/2025 08:14]  System: You steal blood distillation.
[10/14/2025 08:14]  GawdHammer: (Shadow Aspect 66,697.9/250000 xp)
[10/14/2025 08:14]  System: You have lost a little karma.
[10/14/2025 08:14]  an ophidian dragonguard: -7766
[10/14/2025 08:14]  System: You may now use another smoke bomb.
[10/14/2025 08:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:14]  GawdHammer: -12
[10/14/2025 08:14]  GawdHammer: * You feel extremely weak and are in severe pain! *
[10/14/2025 08:14]  System: That potion was not strong enough to cure your ailment!
[10/14/2025 08:14]  System: You receive a free cure potion consumption (next available in 30 seconds).
[10/14/2025 08:14]  System: Your attack crushes their armor!
[10/14/2025 08:14]  System: You have gained a very large amount of karma.
[10/14/2025 08:14]  GawdHammer: (Shadow Aspect 66,720.3/250000 xp)
[10/14/2025 08:14]  System: -234
[10/14/2025 08:14]  System: You feel cured of poison!
[10/14/2025 08:14]  You see: an ophidian dragonguard corpse
[10/14/2025 08:14]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:14]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:14]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:14]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:14]  You see: a wyvern corpse
[10/14/2025 08:14]  You see: an ophidian shaman corpse
[10/14/2025 08:14]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:14]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:14]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:15]  [Razor]: New target set.
[10/14/2025 08:15]  Razor: Target: an ophidian shaman
[10/14/2025 08:15]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:15]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:15]  System: You have 23 stealth steps remaining.
[10/14/2025 08:15]  GawdHammer: +5
[10/14/2025 08:15]  System: some damage has been healed : 5
[10/14/2025 08:15]  [Razor]: Steps: 23
[10/14/2025 08:15]  You see: an earth dragon corpse
[10/14/2025 08:15]  System: You have 22 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 22
[10/14/2025 08:15]  System: You have 21 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 21
[10/14/2025 08:15]  System: You have 20 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 20
[10/14/2025 08:15]  You see: an earth dragon corpse
[10/14/2025 08:15]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:15]  System: You have 19 stealth steps remaining.
[10/14/2025 08:15]  GawdHammer: +2
[10/14/2025 08:15]  System: some damage has been healed : 2
[10/14/2025 08:15]  [Razor]: Steps: 19
[10/14/2025 08:15]  System: You have 18 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 18
[10/14/2025 08:15]  System: You have 17 stealth steps remaining.
[10/14/2025 08:15]  Razor: Attack: an ophidian shaman
[10/14/2025 08:15]  [Razor]: Steps: 17
[10/14/2025 08:15]  System: You have 16 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 16
[10/14/2025 08:15]  System: You have 15 stealth steps remaining.
[10/14/2025 08:15]  System: You strike your target from the shadows!
[10/14/2025 08:15]  System: Society Job Progress: 6/10 [Steal 10 Black Goods].
[10/14/2025 08:15]  GawdHammer: You steal 314 gold coin.
[10/14/2025 08:15]  System: You steal 314 gold coin.
[10/14/2025 08:15]  GawdHammer: You steal very common black goods.
[10/14/2025 08:15]  System: You steal very common black goods.
[10/14/2025 08:15]  GawdHammer: (Shadow Aspect 66,726.2/250000 xp)
[10/14/2025 08:15]  System: You have lost a little karma.
[10/14/2025 08:15]  an ophidian shaman: Sathra thasythasth.
[10/14/2025 08:15]  an ophidian shaman: NOOooo!
[10/14/2025 08:15]  System: You have gained a very large amount of karma.
[10/14/2025 08:15]  GawdHammer: (Shadow Aspect 66,750.0/250000 xp)
[10/14/2025 08:15]  System: -7000
[10/14/2025 08:15]  You see: an ophidian shaman corpse
[10/14/2025 08:15]  : cave floor
[10/14/2025 08:15]  : cave floor
[10/14/2025 08:15]  : cave floor
[10/14/2025 08:15]  : cave floor
[10/14/2025 08:15]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:15]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:15]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:15]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 08:15]  You see: an earth dragon corpse
[10/14/2025 08:15]  a dragon: a dragon
[10/14/2025 08:15]  a dragon: a dragon
[10/14/2025 08:15]  You see: a cave dracolisk corpse
[10/14/2025 08:15]  a harvestman: a harvestman
[10/14/2025 08:15]  Benicio: Monster Hunter Initiate
[10/14/2025 08:15]  Benicio: [Recruit, Yew]
[10/14/2025 08:15]  Benicio: Lord Benicio
[10/14/2025 08:15]  Benicio: Kal Vas Flam
[10/14/2025 08:15]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:15]  You see: a cave dracolisk corpse
[10/14/2025 08:15]  a harvestman: a harvestman
[10/14/2025 08:15]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:15]  Benicio: Kal Vas Flam
[10/14/2025 08:15]  You see: a harvestman corpse
[10/14/2025 08:15]  a cave dracolisk: *roars*
[10/14/2025 08:15]  You see: a forktongue battlekhan corpse
[10/14/2025 08:15]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:15]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:15]  You see: a cave dracolisk corpse
[10/14/2025 08:15]  System: Weapon ability ready.
[10/14/2025 08:15]  Benicio: Des Sanct
[10/14/2025 08:15]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:15]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:15]  System: You have 23 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 23
[10/14/2025 08:15]  a cave dracolisk: *roars*
[10/14/2025 08:15]  System: You have 22 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 22
[10/14/2025 08:15]  System: You have 21 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 21
[10/14/2025 08:15]  System: You have 20 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 20
[10/14/2025 08:15]  System: You have 19 stealth steps remaining.
[10/14/2025 08:15]  Benicio: An Mani
[10/14/2025 08:15]  [Razor]: Steps: 19
[10/14/2025 08:15]  System: You have 18 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 18
[10/14/2025 08:15]  System: You have 17 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 17
[10/14/2025 08:15]  System: You have 16 stealth steps remaining.
[10/14/2025 08:15]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:15]  [Razor]: Steps: 16
[10/14/2025 08:15]  System: You have 15 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 15
[10/14/2025 08:15]  Benicio: In Por Ylem
[10/14/2025 08:15]  System: You have 14 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 14
[10/14/2025 08:15]  System: The world will save in 15 seconds.
[10/14/2025 08:15]  System: You have 13 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 13
[10/14/2025 08:15]  System: You have 12 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 12
[10/14/2025 08:15]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:15]  System: You have 11 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 11
[10/14/2025 08:15]  System: You have 10 stealth steps remaining.
[10/14/2025 08:15]  Razor: [10 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 10
[10/14/2025 08:15]  System: You have 9 stealth steps remaining.
[10/14/2025 08:15]  Razor: [9 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 9
[10/14/2025 08:15]  System: You have 8 stealth steps remaining.
[10/14/2025 08:15]  Razor: [8 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 8
[10/14/2025 08:15]  System: You have 7 stealth steps remaining.
[10/14/2025 08:15]  Razor: [7 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 7
[10/14/2025 08:15]  System: You have 6 stealth steps remaining.
[10/14/2025 08:15]  Razor: [6 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 6
[10/14/2025 08:15]  System: You have 5 stealth steps remaining.
[10/14/2025 08:15]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:15]  [Razor]: Steps: 5
[10/14/2025 08:15]  System: You have 4 stealth steps remaining.
[10/14/2025 08:15]  Razor: [4 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 4
[10/14/2025 08:15]  System: You have 3 stealth steps remaining.
[10/14/2025 08:15]  Razor: [3 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 3
[10/14/2025 08:15]  System: You have 2 stealth steps remaining.
[10/14/2025 08:15]  Razor: [2 Steps...]
[10/14/2025 08:15]  [Razor]: Steps: 2
[10/14/2025 08:15]  System: You will automatically stealth when out of steps.
[10/14/2025 08:15]  Razor: [Auto-stealth ready.]
[10/14/2025 08:15]  System: You have 1 stealth steps remaining.
[10/14/2025 08:15]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 08:15]  [Razor]: Steps: 1
[10/14/2025 08:15]  System: You have 0 stealth steps remaining.
[10/14/2025 08:15]  System: You feel ready to continue stealthing.
[10/14/2025 08:15]  Razor: [Stop!]
[10/14/2025 08:15]  [Razor]: Steps: 0
[10/14/2025 08:15]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:15]  System: You have 23 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 23
[10/14/2025 08:15]  System: You have 22 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 22
[10/14/2025 08:15]  System: You have 21 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 21
[10/14/2025 08:15]  System: You have 20 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 20
[10/14/2025 08:15]  System: You have 19 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 19
[10/14/2025 08:15]  System: You have 18 stealth steps remaining.
[10/14/2025 08:15]  [Razor]: Steps: 18
[10/14/2025 08:15]  System: You have 17 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 17
[10/14/2025 08:16]  System: You have 16 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 16
[10/14/2025 08:16]  System: You have 15 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 15
[10/14/2025 08:16]  System: You have 14 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 14
[10/14/2025 08:16]  System: You have 13 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 13
[10/14/2025 08:16]  System: You have 12 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 12
[10/14/2025 08:16]  System: You have 11 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 11
[10/14/2025 08:16]  System: You have 10 stealth steps remaining.
[10/14/2025 08:16]  Razor: [10 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 10
[10/14/2025 08:16]  System: You have 9 stealth steps remaining.
[10/14/2025 08:16]  Razor: [9 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 9
[10/14/2025 08:16]  System: You have 8 stealth steps remaining.
[10/14/2025 08:16]  Razor: [8 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 8
[10/14/2025 08:16]  System: You have 7 stealth steps remaining.
[10/14/2025 08:16]  Razor: [7 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 7
[10/14/2025 08:16]  System: You have 6 stealth steps remaining.
[10/14/2025 08:16]  Razor: [6 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 6
[10/14/2025 08:16]  System: You have 5 stealth steps remaining.
[10/14/2025 08:16]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:16]  [Razor]: Steps: 5
[10/14/2025 08:16]  System: You have 4 stealth steps remaining.
[10/14/2025 08:16]  Razor: [4 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 4
[10/14/2025 08:16]  System: The world is saving, please wait.
[10/14/2025 08:16]  System: World save complete. The entire process took 3.2 seconds.
[10/14/2025 08:16]  [Razor]: Steps: 4
[10/14/2025 08:16]  System: You have 3 stealth steps remaining.
[10/14/2025 08:16]  Razor: [3 Steps...]
[10/14/2025 08:16]  System: You have 2 stealth steps remaining.
[10/14/2025 08:16]  Razor: [2 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 2
[10/14/2025 08:16]  System: You have 1 stealth steps remaining.
[10/14/2025 08:16]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 08:16]  [Razor]: Steps: 1
[10/14/2025 08:16]  System: You will automatically stealth when out of steps.
[10/14/2025 08:16]  Razor: [Auto-stealth ready.]
[10/14/2025 08:16]  System: You have 0 stealth steps remaining.
[10/14/2025 08:16]  System: You feel ready to continue stealthing.
[10/14/2025 08:16]  Razor: [Stop!]
[10/14/2025 08:16]  [Razor]: Steps: 0
[10/14/2025 08:16]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:16]  System: You have 23 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 23
[10/14/2025 08:16]  System: You have 22 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 22
[10/14/2025 08:16]  System: You have 21 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 21
[10/14/2025 08:16]  System: You have 20 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 20
[10/14/2025 08:16]  System: You have 19 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 19
[10/14/2025 08:16]  System: You have 18 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 18
[10/14/2025 08:16]  You see: a wyvern corpse
[10/14/2025 08:16]  System: You have 17 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 17
[10/14/2025 08:16]  System: You have 16 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 16
[10/14/2025 08:16]  System: You have 15 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 15
[10/14/2025 08:16]  System: You have 14 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 14
[10/14/2025 08:16]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:16]  System: You have 13 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 13
[10/14/2025 08:16]  System: You have 12 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 12
[10/14/2025 08:16]  System: You have 11 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 11
[10/14/2025 08:16]  System: You have 10 stealth steps remaining.
[10/14/2025 08:16]  Razor: [10 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 10
[10/14/2025 08:16]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:16]  System: You have 9 stealth steps remaining.
[10/14/2025 08:16]  Razor: [9 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 9
[10/14/2025 08:16]  System: You have 8 stealth steps remaining.
[10/14/2025 08:16]  Razor: [8 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 8
[10/14/2025 08:16]  System: You have 7 stealth steps remaining.
[10/14/2025 08:16]  Razor: [7 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 7
[10/14/2025 08:16]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:16]  System: You have 6 stealth steps remaining.
[10/14/2025 08:16]  Razor: [6 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 6
[10/14/2025 08:16]  You see: gold coin : 444
[10/14/2025 08:16]  System: You will automatically stealth when out of steps.
[10/14/2025 08:16]  Razor: [Auto-stealth ready.]
[10/14/2025 08:16]  System: You have 5 stealth steps remaining.
[10/14/2025 08:16]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:16]  [Razor]: Steps: 5
[10/14/2025 08:16]  System: You have 4 stealth steps remaining.
[10/14/2025 08:16]  Razor: [4 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 4
[10/14/2025 08:16]  a bonfire wisp: a bonfire wisp
[10/14/2025 08:16]  System: You have 3 stealth steps remaining.
[10/14/2025 08:16]  Razor: [3 Steps...]
[10/14/2025 08:16]  [Razor]: Steps: 3
[10/14/2025 08:16]  System: You have 2 stealth steps remaining.
[10/14/2025 08:16]  Razor: [2 Steps...]
[10/14/2025 08:16]  [Razor]: New target set.
[10/14/2025 08:16]  Razor: Target: an ophidian shaman
[10/14/2025 08:16]  [Razor]: Steps: 2
[10/14/2025 08:16]  System: You have 1 stealth steps remaining.
[10/14/2025 08:16]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 08:16]  [Razor]: Steps: 1
[10/14/2025 08:16]  a wyvern: a wyvern
[10/14/2025 08:16]  a wyvern: a wyvern
[10/14/2025 08:16]  System: You have 0 stealth steps remaining.
[10/14/2025 08:16]  System: You feel ready to continue stealthing.
[10/14/2025 08:16]  Razor: [Stop!]
[10/14/2025 08:16]  [Razor]: Steps: 0
[10/14/2025 08:16]  a wyvern: a wyvern
[10/14/2025 08:16]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:16]  System: You have 23 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 23
[10/14/2025 08:16]  You see: a wyvern corpse
[10/14/2025 08:16]  System: You have 22 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 22
[10/14/2025 08:16]  System: You have 21 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 21
[10/14/2025 08:16]  Razor: Attack: an ophidian shaman
[10/14/2025 08:16]  System: You strike your target from the shadows!
[10/14/2025 08:16]  GawdHammer: You steal 287 gold coin.
[10/14/2025 08:16]  System: You steal 287 gold coin.
[10/14/2025 08:16]  GawdHammer: (Shadow Aspect 66,755.9/250000 xp)
[10/14/2025 08:16]  System: You have lost a little karma.
[10/14/2025 08:16]  System: You have gained a very large amount of karma.
[10/14/2025 08:16]  GawdHammer: (Shadow Aspect 66,779.7/250000 xp)
[10/14/2025 08:16]  System: -7000
[10/14/2025 08:16]  You see: an ophidian shaman corpse
[10/14/2025 08:16]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:16]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:16]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:16]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:16]  System: You have 23 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 23
[10/14/2025 08:16]  System: You have 22 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 22
[10/14/2025 08:16]  System: You have 21 stealth steps remaining.
[10/14/2025 08:16]  System: Tobidas has completed the achievement: Guardian of The Mausoleum (Advanced).
[10/14/2025 08:16]  [Razor]: Steps: 21
[10/14/2025 08:16]  System: You have 20 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 20
[10/14/2025 08:16]  System: You have 19 stealth steps remaining.
[10/14/2025 08:16]  [Razor]: Steps: 19
[10/14/2025 08:16]  [Razor]: New target set.
[10/14/2025 08:16]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:16]  a wyvern: a wyvern
[10/14/2025 08:16]  System: You will automatically stealth when out of steps.
[10/14/2025 08:16]  Razor: [Auto-stealth ready.]
[10/14/2025 08:16]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:16]  System: You strike your target from the shadows!
[10/14/2025 08:16]  GawdHammer: You steal 268 gold coin.
[10/14/2025 08:16]  System: You steal 268 gold coin.
[10/14/2025 08:16]  GawdHammer: (Shadow Aspect 66,785.3/250000 xp)
[10/14/2025 08:16]  System: You have lost a little karma.
[10/14/2025 08:16]  an ophidian dragonguard: -6668
[10/14/2025 08:16]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:16]  GawdHammer: You can't seem to hide right now.
[10/14/2025 08:16]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:16]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:16]  System: You are no longer hindered.
[10/14/2025 08:16]  [Razor]: New target set.
[10/14/2025 08:17]  System: You must wait a moment before using that skill.
[10/14/2025 08:17]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:17]  System: You will automatically stealth when out of steps.
[10/14/2025 08:17]  Razor: [Auto-stealth ready.]
[10/14/2025 08:17]  System: You strike your target from the shadows!
[10/14/2025 08:17]  System: You have already stolen from this creature.
[10/14/2025 08:17]  an ophidian dragonguard: Als salyts! Sthi.
[10/14/2025 08:17]  an ophidian dragonguard: NOOooo!
[10/14/2025 08:17]  System: You have gained a very large amount of karma.
[10/14/2025 08:17]  GawdHammer: (Shadow Aspect 66,807.6/250000 xp)
[10/14/2025 08:17]  System: -1332
[10/14/2025 08:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:17]  You see: an ophidian dragonguard corpse
[10/14/2025 08:17]  System: You must wait a few moments to use another skill.
[10/14/2025 08:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:17]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:17]  System: You must wait a few moments to use another skill.
[10/14/2025 08:17]  [Razor]: New target set.
[10/14/2025 08:17]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:17]  a wyvern: a wyvern
[10/14/2025 08:17]  [Razor]: New target set.
[10/14/2025 08:17]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:17]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:17]  a wyvern: a wyvern
[10/14/2025 08:17]  [Razor]: New target set.
[10/14/2025 08:17]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:17]  System: You strike your target from the shadows!
[10/14/2025 08:17]  GawdHammer: You steal 279 gold coin.
[10/14/2025 08:17]  System: You steal 279 gold coin.
[10/14/2025 08:17]  GawdHammer: (Shadow Aspect 66,813.2/250000 xp)
[10/14/2025 08:17]  System: You have lost a little karma.
[10/14/2025 08:17]  an ophidian dragonguard: -7087
[10/14/2025 08:17]  Bleep: [Councilor, Yew]
[10/14/2025 08:17]  Bleep: Bleep
[10/14/2025 08:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:17]  GawdHammer: You can't seem to hide right now.
[10/14/2025 08:17]  GawdHammer: -20
[10/14/2025 08:17]  GawdHammer: -19
[10/14/2025 08:17]  GawdHammer: +34
[10/14/2025 08:17]  System: some damage has been healed : 34
[10/14/2025 08:17]  System: You drink a healing potion.
[10/14/2025 08:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:17]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:17]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:17]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:17]  System: You have 23 stealth steps remaining.
[10/14/2025 08:17]  GawdHammer: +2
[10/14/2025 08:17]  System: some damage has been healed : 2
[10/14/2025 08:17]  [Razor]: Steps: 23
[10/14/2025 08:17]  System: You have 22 stealth steps remaining.
[10/14/2025 08:17]  [Razor]: Steps: 22
[10/14/2025 08:17]  System: You have 21 stealth steps remaining.
[10/14/2025 08:17]  System: You strike your target from the shadows!
[10/14/2025 08:17]  System: You have already stolen from this creature.
[10/14/2025 08:17]  System: You have gained a very large amount of karma.
[10/14/2025 08:17]  GawdHammer: (Shadow Aspect 66,835.6/250000 xp)
[10/14/2025 08:17]  System: -913
[10/14/2025 08:17]  You see: an ophidian dragonguard corpse
[10/14/2025 08:17]  a stone adder: a stone adder
[10/14/2025 08:17]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:17]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:17]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:17]  You see: an ophidian shaman corpse
[10/14/2025 08:17]  You see: an ophidian dragonguard corpse
[10/14/2025 08:17]  a bonfire wisp: a bonfire wisp
[10/14/2025 08:17]  a wyvern: a wyvern
[10/14/2025 08:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:17]  a wyvern: a wyvern
[10/14/2025 08:17]  System: You must wait a few moments to use another skill.
[10/14/2025 08:17]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:17]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:17]  [Razor]: New target set.
[10/14/2025 08:17]  Razor: Target: a bonfire wisp
[10/14/2025 08:17]  System: You may now use another smoke bomb.
[10/14/2025 08:18]  System: Tapete has completed the achievement: Enchanter (Advanced).
[10/14/2025 08:18]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:18]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:18]  System: You have 23 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 23
[10/14/2025 08:18]  System: You have 22 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 22
[10/14/2025 08:18]  a wyvern: a wyvern
[10/14/2025 08:18]  System: You have 21 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 21
[10/14/2025 08:18]  System: You have 20 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 20
[10/14/2025 08:18]  System: You have 19 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 19
[10/14/2025 08:18]  System: You have 18 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 18
[10/14/2025 08:18]  System: You have 17 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 17
[10/14/2025 08:18]  System: You have 16 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 16
[10/14/2025 08:18]  Razor: Attack: a bonfire wisp
[10/14/2025 08:18]  System: You have 15 stealth steps remaining.
[10/14/2025 08:18]  [Razor]: Steps: 15
[10/14/2025 08:18]  System: You have 14 stealth steps remaining.
[10/14/2025 08:18]  System: You strike your target from the shadows!
[10/14/2025 08:18]  GawdHammer: You steal 353 gold coin.
[10/14/2025 08:18]  System: You steal 353 gold coin.
[10/14/2025 08:18]  GawdHammer: (Shadow Aspect 66,842.5/250000 xp)
[10/14/2025 08:18]  System: You have lost a little karma.
[10/14/2025 08:18]  GawdHammer: -10
[10/14/2025 08:18]  System: You have gained a very large amount of karma.
[10/14/2025 08:18]  GawdHammer: (Shadow Aspect 66,870.1/250000 xp)
[10/14/2025 08:18]  System: -6000
[10/14/2025 08:18]  You see: a bonfire wisp corpse
[10/14/2025 08:18]  System: NotHere has completed the achievement: Summoning (Advanced).
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:18]  [Razor]: Finished 6 queued actions in 3.0 seconds.
[10/14/2025 08:18]  a wyvern: a wyvern
[10/14/2025 08:18]  a wyvern: a wyvern
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:18]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:18]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:18]  You see: an ophidian dragonguard corpse
[10/14/2025 08:18]  Guy Swingalot: Brilliant
[10/14/2025 08:18]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:18]  You see: a cave dracolisk corpse
[10/14/2025 08:18]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 08:18]  You see: a cave dracolisk corpse
[10/14/2025 08:18]  You see: an ophidian shaman corpse
[10/14/2025 08:18]  an ophidian shaman: [Paragon]
[10/14/2025 08:18]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:18]  You see: an ophidian shaman corpse
[10/14/2025 08:18]  You see: a dragon corpse
[10/14/2025 08:18]  You see: an earth dragon corpse
[10/14/2025 08:18]  a dragon: a dragon
[10/14/2025 08:18]  an earth dragon: an earth dragon
[10/14/2025 08:18]  an earth dragon: an earth dragon
[10/14/2025 08:19]  You see: a dragon corpse
[10/14/2025 08:19]  You see: an ophidian shaman corpse
[10/14/2025 08:19]  You see: an ophidian shaman corpse
[10/14/2025 08:19]  an ophidian shaman: [Paragon]
[10/14/2025 08:19]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:19]  You see: an earth dragon corpse
[10/14/2025 08:19]  You see: a dragon corpse
[10/14/2025 08:19]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:19]  an earth dragon: an earth dragon
[10/14/2025 08:19]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:19]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:19]  [Razor]: Steps: 0
[10/14/2025 08:19]  [Razor]: Steps: 0
[10/14/2025 08:19]  System: You are no longer hindered.
[10/14/2025 08:19]  System: You have 29 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: New target set.
[10/14/2025 08:19]  Razor: Target: an ophidian shaman
[10/14/2025 08:19]  [Razor]: Steps: 29
[10/14/2025 08:19]  System: You have 28 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 28
[10/14/2025 08:19]  System: You have 27 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 27
[10/14/2025 08:19]  System: You have 26 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 26
[10/14/2025 08:19]  Razor: Attack: an ophidian shaman
[10/14/2025 08:19]  System: You have 25 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 25
[10/14/2025 08:19]  System: You have 24 stealth steps remaining.
[10/14/2025 08:19]  System: You strike your target from the shadows!
[10/14/2025 08:19]  System: Society Job Progress: 7/10 [Steal 10 Black Goods].
[10/14/2025 08:19]  GawdHammer: You steal very common black goods.
[10/14/2025 08:19]  System: You steal very common black goods.
[10/14/2025 08:19]  GawdHammer: You steal 462 gold coin.
[10/14/2025 08:19]  System: You steal 462 gold coin.
[10/14/2025 08:19]  GawdHammer: (Shadow Aspect 66,878.6/250000 xp)
[10/14/2025 08:19]  System: You have lost a little karma.
[10/14/2025 08:19]  an ophidian shaman: -7321
[10/14/2025 08:19]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:19]  an earth dragon: an earth dragon
[10/14/2025 08:19]  You see: an earth dragon corpse
[10/14/2025 08:19]  an earth dragon: an earth dragon
[10/14/2025 08:19]  You see: a dragon corpse
[10/14/2025 08:19]  an earth dragon: an earth dragon
[10/14/2025 08:19]  You see: a dragon corpse
[10/14/2025 08:19]  GawdHammer: -23
[10/14/2025 08:19]  GawdHammer: +23
[10/14/2025 08:19]  System: some damage has been healed : 23
[10/14/2025 08:19]  System: You drink a healing potion.
[10/14/2025 08:19]  You see: a dragon corpse
[10/14/2025 08:19]  GawdHammer: -22
[10/14/2025 08:19]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:19]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:19]  an earth dragon: an earth dragon
[10/14/2025 08:19]  an earth dragon: an earth dragon
[10/14/2025 08:19]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:19]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:19]  System: You have 23 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 23
[10/14/2025 08:19]  System: You have 22 stealth steps remaining.
[10/14/2025 08:19]  GawdHammer: +5
[10/14/2025 08:19]  System: some damage has been healed : 5
[10/14/2025 08:19]  [Razor]: Steps: 22
[10/14/2025 08:19]  System: You have 21 stealth steps remaining.
[10/14/2025 08:19]  GawdHammer: +5
[10/14/2025 08:19]  System: some damage has been healed : 5
[10/14/2025 08:19]  [Razor]: Steps: 21
[10/14/2025 08:19]  System: You have 20 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 20
[10/14/2025 08:19]  System: You have 19 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 19
[10/14/2025 08:19]  System: You have 18 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 18
[10/14/2025 08:19]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:19]  System: You have 17 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 17
[10/14/2025 08:19]  System: You have 16 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 16
[10/14/2025 08:19]  System: You have 15 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 15
[10/14/2025 08:19]  System: You have 14 stealth steps remaining.
[10/14/2025 08:19]  GawdHammer: +5
[10/14/2025 08:19]  System: some damage has been healed : 5
[10/14/2025 08:19]  [Razor]: Steps: 14
[10/14/2025 08:19]  System: You have 13 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 13
[10/14/2025 08:19]  System: You have 12 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 12
[10/14/2025 08:19]  System: You have 11 stealth steps remaining.
[10/14/2025 08:19]  GawdHammer: +4
[10/14/2025 08:19]  System: some damage has been healed : 4
[10/14/2025 08:19]  [Razor]: Steps: 11
[10/14/2025 08:19]  System: You have 10 stealth steps remaining.
[10/14/2025 08:19]  Razor: [10 Steps...]
[10/14/2025 08:19]  [Razor]: Steps: 10
[10/14/2025 08:19]  System: You have 9 stealth steps remaining.
[10/14/2025 08:19]  Razor: [9 Steps...]
[10/14/2025 08:19]  [Razor]: Steps: 9
[10/14/2025 08:19]  System: You have 8 stealth steps remaining.
[10/14/2025 08:19]  Razor: [8 Steps...]
[10/14/2025 08:19]  [Razor]: Steps: 8
[10/14/2025 08:19]  System: You have 7 stealth steps remaining.
[10/14/2025 08:19]  Razor: [7 Steps...]
[10/14/2025 08:19]  [Razor]: Steps: 7
[10/14/2025 08:19]  System: You have 6 stealth steps remaining.
[10/14/2025 08:19]  Razor: [6 Steps...]
[10/14/2025 08:19]  [Razor]: Steps: 6
[10/14/2025 08:19]  System: You have 5 stealth steps remaining.
[10/14/2025 08:19]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:19]  System: You strike your target from the shadows!
[10/14/2025 08:19]  System: You have already stolen from this creature.
[10/14/2025 08:19]  System: You have gained a very large amount of karma.
[10/14/2025 08:19]  GawdHammer: (Shadow Aspect 66,912.5/250000 xp)
[10/14/2025 08:19]  System: -1989
[10/14/2025 08:19]  You see: an ophidian shaman corpse
[10/14/2025 08:19]  : cave floor
[10/14/2025 08:19]  : cave floor
[10/14/2025 08:19]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:19]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:19]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:19]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:19]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 08:19]  [Razor]: Queuing dragdrop request 2... 1.2 seconds left.
[10/14/2025 08:19]  [Razor]: Finished 7 queued actions in 3.6 seconds.
[10/14/2025 08:19]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:19]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:19]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:19]  You see: an ophidian dragonguard corpse
[10/14/2025 08:19]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:19]  [Razor]: New target set.
[10/14/2025 08:19]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:19]  System: Chewbarka has completed the achievement: Guardian of Pulma (Basic).
[10/14/2025 08:19]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:19]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:19]  System: You have 23 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 23
[10/14/2025 08:19]  System: You have 22 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 22
[10/14/2025 08:19]  System: You have 21 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 21
[10/14/2025 08:19]  System: You have 20 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 20
[10/14/2025 08:19]  System: You have 19 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 19
[10/14/2025 08:19]  System: You have 18 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 18
[10/14/2025 08:19]  System: You have 17 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 17
[10/14/2025 08:19]  System: You have 16 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 16
[10/14/2025 08:19]  System: You have 15 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 15
[10/14/2025 08:19]  System: You have 14 stealth steps remaining.
[10/14/2025 08:19]  [Razor]: Steps: 14
[10/14/2025 08:19]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:19]  System: You have 13 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 13
[10/14/2025 08:20]  System: You have 12 stealth steps remaining.
[10/14/2025 08:20]  System: You strike your target from the shadows!
[10/14/2025 08:20]  GawdHammer: You steal 548 gold coin.
[10/14/2025 08:20]  System: You steal 548 gold coin.
[10/14/2025 08:20]  GawdHammer: (Shadow Aspect 66,918.0/250000 xp)
[10/14/2025 08:20]  System: You have lost a little karma.
[10/14/2025 08:20]  an ophidian dragonguard: -7938
[10/14/2025 08:20]  GawdHammer: -12
[10/14/2025 08:20]  a wyvern: a wyvern
[10/14/2025 08:20]  a wyvern: a wyvern
[10/14/2025 08:20]  You see: an ophidian shaman corpse
[10/14/2025 08:20]  You see: an ophidian shaman corpse
[10/14/2025 08:20]  You see: an ophidian shaman corpse
[10/14/2025 08:20]  System: You have gained a very large amount of karma.
[10/14/2025 08:20]  GawdHammer: (Shadow Aspect 66,940.4/250000 xp)
[10/14/2025 08:20]  System: -62
[10/14/2025 08:20]  You see: an ophidian dragonguard corpse
[10/14/2025 08:20]  System: You may now use another smoke bomb.
[10/14/2025 08:20]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:20]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:20]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:20]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:20]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:20]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:20]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:20]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:20]  System: You have 23 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 23
[10/14/2025 08:20]  System: You have 22 stealth steps remaining.
[10/14/2025 08:20]  GawdHammer: +5
[10/14/2025 08:20]  System: some damage has been healed : 5
[10/14/2025 08:20]  [Razor]: Steps: 22
[10/14/2025 08:20]  System: You have 21 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 21
[10/14/2025 08:20]  System: You have 20 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 20
[10/14/2025 08:20]  System: You have 19 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 19
[10/14/2025 08:20]  You see: an ophidian shaman corpse
[10/14/2025 08:20]  You see: an ophidian shaman corpse
[10/14/2025 08:20]  System: You have 18 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 18
[10/14/2025 08:20]  System: You have 17 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 17
[10/14/2025 08:20]  System: You have 16 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 16
[10/14/2025 08:20]  System: You have 15 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 15
[10/14/2025 08:20]  an earth dragon: an earth dragon
[10/14/2025 08:20]  System: You have 14 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: New target set.
[10/14/2025 08:20]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:20]  [Razor]: Steps: 14
[10/14/2025 08:20]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:20]  System: You have 13 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 13
[10/14/2025 08:20]  System: You will automatically stealth when out of steps.
[10/14/2025 08:20]  Razor: [Auto-stealth ready.]
[10/14/2025 08:20]  System: You have 12 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 12
[10/14/2025 08:20]  System: You have 11 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 11
[10/14/2025 08:20]  System: You have 10 stealth steps remaining.
[10/14/2025 08:20]  Razor: [10 Steps...]
[10/14/2025 08:20]  [Razor]: Steps: 10
[10/14/2025 08:20]  System: You have 9 stealth steps remaining.
[10/14/2025 08:20]  Razor: [9 Steps...]
[10/14/2025 08:20]  [Razor]: Steps: 9
[10/14/2025 08:20]  System: You have 8 stealth steps remaining.
[10/14/2025 08:20]  Razor: [8 Steps...]
[10/14/2025 08:20]  [Razor]: New target set.
[10/14/2025 08:20]  [Razor]: Steps: 8
[10/14/2025 08:20]  You see: an ophidian dragonguard corpse
[10/14/2025 08:20]  System: You have 7 stealth steps remaining.
[10/14/2025 08:20]  Razor: [7 Steps...]
[10/14/2025 08:20]  [Razor]: Steps: 7
[10/14/2025 08:20]  System: You have 6 stealth steps remaining.
[10/14/2025 08:20]  Razor: [6 Steps...]
[10/14/2025 08:20]  [Razor]: Steps: 6
[10/14/2025 08:20]  System: You have 5 stealth steps remaining.
[10/14/2025 08:20]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:20]  System: You strike your target from the shadows!
[10/14/2025 08:20]  GawdHammer: You steal 255 gold coin.
[10/14/2025 08:20]  System: You steal 255 gold coin.
[10/14/2025 08:20]  GawdHammer: (Shadow Aspect 66,946.0/250000 xp)
[10/14/2025 08:20]  System: You have lost a little karma.
[10/14/2025 08:20]  System: You have gained a very large amount of karma.
[10/14/2025 08:20]  GawdHammer: (Shadow Aspect 66,968.4/250000 xp)
[10/14/2025 08:20]  System: -8000
[10/14/2025 08:20]  You see: an ophidian dragonguard corpse
[10/14/2025 08:20]  an earth dragon: an earth dragon
[10/14/2025 08:20]  GawdHammer: That is out of my line of sight.
[10/14/2025 08:20]  GawdHammer: That is out of my line of sight.
[10/14/2025 08:20]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:20]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:20]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:20]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:20]  System: You have 23 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 23
[10/14/2025 08:20]  System: You have 22 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 22
[10/14/2025 08:20]  [Razor]: New target set.
[10/14/2025 08:20]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:20]  System: You have 21 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 21
[10/14/2025 08:20]  System: You have 20 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 20
[10/14/2025 08:20]  System: You have 19 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 19
[10/14/2025 08:20]  System: You have 18 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 18
[10/14/2025 08:20]  System: You have 17 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 17
[10/14/2025 08:20]  System: You have 16 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 16
[10/14/2025 08:20]  an earth dragon: an earth dragon
[10/14/2025 08:20]  System: You have 15 stealth steps remaining.
[10/14/2025 08:20]  [Razor]: Steps: 15
[10/14/2025 08:20]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:21]  System: You have 14 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 14
[10/14/2025 08:21]  System: You have 13 stealth steps remaining.
[10/14/2025 08:21]  System: You strike your target from the shadows!
[10/14/2025 08:21]  GawdHammer: You steal 288 gold coin.
[10/14/2025 08:21]  System: You steal 288 gold coin.
[10/14/2025 08:21]  GawdHammer: (Shadow Aspect 66,974.0/250000 xp)
[10/14/2025 08:21]  System: You have lost a little karma.
[10/14/2025 08:21]  System: You have gained a very large amount of karma.
[10/14/2025 08:21]  GawdHammer: (Shadow Aspect 66,996.3/250000 xp)
[10/14/2025 08:21]  System: -8000
[10/14/2025 08:21]  You see: an ophidian dragonguard corpse
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:21]  System: Weapon ability ready.
[10/14/2025 08:21]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:21]  an earth dragon: an earth dragon
[10/14/2025 08:21]  You see: an ophidian shaman corpse
[10/14/2025 08:21]  You see: an ophidian shaman corpse
[10/14/2025 08:21]  You see: an ophidian dragonguard corpse
[10/14/2025 08:21]  an earth dragon: an earth dragon
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:21]  an earth dragon: an earth dragon
[10/14/2025 08:21]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:21]  System: Being perfectly rested, you shove something invisible out of the way.
[10/14/2025 08:21]  an eldritch drake: an eldritch drake
[10/14/2025 08:21]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:21]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:21]  [Razor]: Steps: 0
[10/14/2025 08:21]  [Razor]: Steps: 0
[10/14/2025 08:21]  System: You are no longer hindered.
[10/14/2025 08:21]  System: You have 29 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 29
[10/14/2025 08:21]  System: You have 28 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 28
[10/14/2025 08:21]  [Razor]: New target set.
[10/14/2025 08:21]  Razor: Target: an eldritch drake
[10/14/2025 08:21]  You see: an eldritch drake corpse
[10/14/2025 08:21]  System: You have 27 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 27
[10/14/2025 08:21]  an ember drake: an ember drake
[10/14/2025 08:21]  System: You have 26 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 26
[10/14/2025 08:21]  System: You have 25 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 25
[10/14/2025 08:21]  You see: an eldritch drake corpse
[10/14/2025 08:21]  System: You have 24 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 24
[10/14/2025 08:21]  an eldritch drake: an eldritch drake
[10/14/2025 08:21]  System: You have 23 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 23
[10/14/2025 08:21]  System: You have 22 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 22
[10/14/2025 08:21]  System: You have 21 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 21
[10/14/2025 08:21]  System: You have 20 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 20
[10/14/2025 08:21]  System: You have 19 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 19
[10/14/2025 08:21]  System: You have 18 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 18
[10/14/2025 08:21]  System: You have 17 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 17
[10/14/2025 08:21]  System: You have 16 stealth steps remaining.
[10/14/2025 08:21]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:21]  System: You will automatically stealth when out of steps.
[10/14/2025 08:21]  Razor: [Auto-stealth ready.]
[10/14/2025 08:21]  [Razor]: Steps: 16
[10/14/2025 08:21]  System: You have 15 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 15
[10/14/2025 08:21]  System: You have 14 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 14
[10/14/2025 08:21]  System: You have 13 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 13
[10/14/2025 08:21]  Razor: Attack: an eldritch drake
[10/14/2025 08:21]  System: You have 12 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 12
[10/14/2025 08:21]  System: You have 11 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 11
[10/14/2025 08:21]  System: You have 10 stealth steps remaining.
[10/14/2025 08:21]  Razor: [10 Steps...]
[10/14/2025 08:21]  System: You strike your target from the shadows!
[10/14/2025 08:21]  System: You fail to steal from the creature.
[10/14/2025 08:21]  System: You have lost a little karma.
[10/14/2025 08:21]  System: You have gained a very large amount of karma.
[10/14/2025 08:21]  GawdHammer: (Shadow Aspect 67,021.6/250000 xp)
[10/14/2025 08:21]  System: -6000
[10/14/2025 08:21]  You see: an eldritch drake corpse
[10/14/2025 08:21]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:21]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:21]  [Razor]: New target set.
[10/14/2025 08:21]  Razor: Target: an eldritch drake
[10/14/2025 08:21]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:21]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:21]  System: You have 23 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 23
[10/14/2025 08:21]  System: You have 22 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 22
[10/14/2025 08:21]  Razor: Attack: an eldritch drake
[10/14/2025 08:21]  System: You have 21 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 21
[10/14/2025 08:21]  System: You have 20 stealth steps remaining.
[10/14/2025 08:21]  [Razor]: Steps: 20
[10/14/2025 08:21]  System: You have 19 stealth steps remaining.
[10/14/2025 08:21]  System: You strike your target from the shadows!
[10/14/2025 08:21]  GawdHammer: You steal 303 gold coin.
[10/14/2025 08:21]  System: You steal 303 gold coin.
[10/14/2025 08:21]  GawdHammer: (Shadow Aspect 67,027.9/250000 xp)
[10/14/2025 08:21]  System: You have lost a little karma.
[10/14/2025 08:21]  System: You have gained a very large amount of karma.
[10/14/2025 08:21]  GawdHammer: (Shadow Aspect 67,053.2/250000 xp)
[10/14/2025 08:21]  System: -6000
[10/14/2025 08:21]  You see: an eldritch drake corpse
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:21]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 08:21]  [Razor]: Queuing dragdrop request 2... 1.3 seconds left.
[10/14/2025 08:21]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 08:21]  an ember drake: an ember drake
[10/14/2025 08:22]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:22]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:22]  [Razor]: New target set.
[10/14/2025 08:22]  Razor: Target: an ember drake
[10/14/2025 08:22]  Benicio: Monster Hunter Initiate
[10/14/2025 08:22]  Benicio: [Recruit, Yew]
[10/14/2025 08:22]  Benicio: Lord Benicio
[10/14/2025 08:22]  System: After Midnight has completed the achievement: Maester (Basic).
[10/14/2025 08:22]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:22]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:22]  System: You have 23 stealth steps remaining.
[10/14/2025 08:22]  [Razor]: Steps: 23
[10/14/2025 08:22]  System: You have 22 stealth steps remaining.
[10/14/2025 08:22]  [Razor]: Steps: 22
[10/14/2025 08:22]  System: You have 21 stealth steps remaining.
[10/14/2025 08:22]  Razor: Attack: an ember drake
[10/14/2025 08:22]  [Razor]: Steps: 21
[10/14/2025 08:22]  System: You have 20 stealth steps remaining.
[10/14/2025 08:22]  System: You strike your target from the shadows!
[10/14/2025 08:22]  GawdHammer: You steal 220 gold coin.
[10/14/2025 08:22]  System: You steal 220 gold coin.
[10/14/2025 08:22]  GawdHammer: (Shadow Aspect 67,057.6/250000 xp)
[10/14/2025 08:22]  System: You have lost a little karma.
[10/14/2025 08:22]  System: You have gained a lot of karma.
[10/14/2025 08:22]  GawdHammer: (Shadow Aspect 67,075.5/250000 xp)
[10/14/2025 08:22]  System: -6000
[10/14/2025 08:22]  You see: an ember drake corpse
[10/14/2025 08:22]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:22]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:22]  an ember drake: an ember drake
[10/14/2025 08:22]  an ember drake: an ember drake
[10/14/2025 08:22]  System: You may now use another smoke bomb.
[10/14/2025 08:22]  System: You must wait a few moments to use another skill.
[10/14/2025 08:22]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:22]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:22]  [Razor]: New target set.
[10/14/2025 08:22]  Razor: Target: an ember drake
[10/14/2025 08:22]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:22]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:22]  System: You have 23 stealth steps remaining.
[10/14/2025 08:22]  [Razor]: Steps: 23
[10/14/2025 08:22]  Razor: Attack: an ember drake
[10/14/2025 08:22]  System: You have 22 stealth steps remaining.
[10/14/2025 08:22]  [Razor]: Steps: 22
[10/14/2025 08:22]  System: You strike your target from the shadows!
[10/14/2025 08:22]  GawdHammer: You steal 238 gold coin.
[10/14/2025 08:22]  System: You steal 238 gold coin.
[10/14/2025 08:22]  GawdHammer: You steal extremely common seed.
[10/14/2025 08:22]  System: You steal extremely common seed.
[10/14/2025 08:22]  GawdHammer: (Shadow Aspect 67,079.9/250000 xp)
[10/14/2025 08:22]  System: You have lost a little karma.
[10/14/2025 08:22]  System: You have gained a lot of karma.
[10/14/2025 08:22]  GawdHammer: (Shadow Aspect 67,097.8/250000 xp)
[10/14/2025 08:22]  System: -6000
[10/14/2025 08:22]  You see: an ember drake corpse
[10/14/2025 08:22]  You see: an ember drake corpse
[10/14/2025 08:22]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:22]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:22]  [Razor]: New target set.
[10/14/2025 08:22]  Razor: Target: an ember drake
[10/14/2025 08:22]  System: You are no longer hindered.
[10/14/2025 08:22]  System: You have 29 stealth steps remaining.
[10/14/2025 08:22]  [Razor]: Steps: 29
[10/14/2025 08:22]  System: You have 28 stealth steps remaining.
[10/14/2025 08:22]  Razor: Attack: an ember drake
[10/14/2025 08:22]  [Razor]: Steps: 28
[10/14/2025 08:22]  System: You have 27 stealth steps remaining.
[10/14/2025 08:22]  System: You strike your target from the shadows!
[10/14/2025 08:22]  System: Society Job Progress: 8/10 [Steal 10 Black Goods].
[10/14/2025 08:22]  GawdHammer: You steal 234 gold coin.
[10/14/2025 08:22]  System: You steal 234 gold coin.
[10/14/2025 08:22]  GawdHammer: You steal extremely common black goods.
[10/14/2025 08:22]  System: You steal extremely common black goods.
[10/14/2025 08:22]  GawdHammer: (Shadow Aspect 67,102.3/250000 xp)
[10/14/2025 08:22]  System: You have lost a little karma.
[10/14/2025 08:22]  System: You have gained a lot of karma.
[10/14/2025 08:22]  GawdHammer: (Shadow Aspect 67,120.1/250000 xp)
[10/14/2025 08:22]  System: -6000
[10/14/2025 08:22]  You see: an ember drake corpse
[10/14/2025 08:22]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:22]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:22]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:22]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:22]  You see: an ember drake corpse
[10/14/2025 08:22]  You see: an eldritch drake corpse
[10/14/2025 08:23]  System: Redhead has completed the achievement: Savant (Advanced).
[10/14/2025 08:23]  You see: an ophidian dragonguard corpse
[10/14/2025 08:23]  You see: an ophidian dragonguard corpse
[10/14/2025 08:23]  System: Lakito has completed the achievement: Beastial Slayer (Basic).
[10/14/2025 08:23]  You see: an ophidian dragonguard corpse
[10/14/2025 08:23]  a wyvern: a wyvern
[10/14/2025 08:23]  a wyvern: a wyvern
[10/14/2025 08:23]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:23]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 08:23]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:23]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:23]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:23]  [Razor]: New target set.
[10/14/2025 08:23]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:23]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:23]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:23]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:23]  System: You have 23 stealth steps remaining.
[10/14/2025 08:23]  System: You strike your target from the shadows!
[10/14/2025 08:23]  GawdHammer: You steal 287 gold coin.
[10/14/2025 08:23]  System: You steal 287 gold coin.
[10/14/2025 08:23]  GawdHammer: (Shadow Aspect 67,125.7/250000 xp)
[10/14/2025 08:23]  System: You have lost a little karma.
[10/14/2025 08:23]  an ophidian dragonguard: -7422
[10/14/2025 08:23]  GawdHammer: -13
[10/14/2025 08:23]  System: Mirclened has completed the achievement: Connoisseur (Epic).
[10/14/2025 08:23]  You see: an ophidian dragonguard corpse
[10/14/2025 08:23]  System: Your attack crushes their armor!
[10/14/2025 08:23]  an ophidian dragonguard: -378
[10/14/2025 08:23]  GawdHammer: -11
[10/14/2025 08:23]  System: You have gained a very large amount of karma.
[10/14/2025 08:23]  GawdHammer: (Shadow Aspect 67,148.1/250000 xp)
[10/14/2025 08:23]  System: -200
[10/14/2025 08:23]  You see: an ophidian dragonguard corpse
[10/14/2025 08:23]  : stone roof
[10/14/2025 08:23]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:23]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:23]  You see: an ophidian dragonguard corpse
[10/14/2025 08:23]  GawdHammer: +22
[10/14/2025 08:23]  System: some damage has been healed : 22
[10/14/2025 08:23]  System: You drink a healing potion.
[10/14/2025 08:23]  a wyvern: a wyvern
[10/14/2025 08:23]  a wyvern: a wyvern
[10/14/2025 08:23]  You see: an ophidian shaman corpse
[10/14/2025 08:23]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:23]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:23]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:23]  [Razor]: New target set.
[10/14/2025 08:23]  Razor: Target: an ophidian shaman
[10/14/2025 08:23]  System: You may now use another smoke bomb.
[10/14/2025 08:23]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:23]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:23]  System: You have 23 stealth steps remaining.
[10/14/2025 08:23]  [Razor]: Steps: 23
[10/14/2025 08:23]  Razor: Attack: an ophidian shaman
[10/14/2025 08:23]  System: You have 22 stealth steps remaining.
[10/14/2025 08:23]  [Razor]: Steps: 22
[10/14/2025 08:23]  System: You have 21 stealth steps remaining.
[10/14/2025 08:23]  [Razor]: Steps: 21
[10/14/2025 08:23]  System: You have 20 stealth steps remaining.
[10/14/2025 08:23]  [Razor]: Steps: 20
[10/14/2025 08:23]  You see: an ophidian dragonguard corpse
[10/14/2025 08:23]  System: You have 19 stealth steps remaining.
[10/14/2025 08:23]  System: You strike your target from the shadows!
[10/14/2025 08:23]  System: Society Job Progress: 9/10 [Steal 10 Black Goods].
[10/14/2025 08:23]  GawdHammer: You steal rare black goods.
[10/14/2025 08:23]  System: You steal rare black goods.
[10/14/2025 08:23]  GawdHammer: You steal 300 gold coin.
[10/14/2025 08:23]  System: You steal 300 gold coin.
[10/14/2025 08:23]  GawdHammer: (Shadow Aspect 67,154.0/250000 xp)
[10/14/2025 08:23]  System: You have lost a little karma.
[10/14/2025 08:23]  an ophidian shaman: Shashsilitssys! Sissylthth.
[10/14/2025 08:23]  an ophidian shaman: Oooh, me hurt...
[10/14/2025 08:23]  System: You have gained a very large amount of karma.
[10/14/2025 08:23]  GawdHammer: (Shadow Aspect 67,177.8/250000 xp)
[10/14/2025 08:23]  System: -7000
[10/14/2025 08:23]  an earth dragon: an earth dragon
[10/14/2025 08:23]  Guy Swingalot: Brilliant
[10/14/2025 08:23]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:23]  You see: an ophidian shaman corpse
[10/14/2025 08:23]  : cave floor
[10/14/2025 08:23]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:23]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:23]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:23]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 08:23]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:23]  You see: an earth dragon corpse
[10/14/2025 08:23]  You see: an earth dragon corpse
[10/14/2025 08:23]  Gnuhag Alkebulan: [Syn]
[10/14/2025 08:23]  Gnuhag Alkebulan: Lord Gnuhag Alkebulan
[10/14/2025 08:23]  You see: a dragon corpse
[10/14/2025 08:23]  a dragon: a dragon
[10/14/2025 08:23]  You see: an earth dragon corpse
[10/14/2025 08:23]  a dragon: a dragon
[10/14/2025 08:24]  a harvestman: a harvestman
[10/14/2025 08:24]  You see: a harvestman corpse
[10/14/2025 08:24]  You see: a cave dracolisk corpse
[10/14/2025 08:24]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:24]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:24]  You see: a harvestman corpse
[10/14/2025 08:24]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:24]  You see: a cave dracolisk corpse
[10/14/2025 08:24]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:24]  a harvestman: a harvestman
[10/14/2025 08:24]  Benicio: Monster Hunter Initiate
[10/14/2025 08:24]  Benicio: [Recruit, Yew]
[10/14/2025 08:24]  Benicio: Lord Benicio
[10/14/2025 08:24]  Benicio: Des Sanct
[10/14/2025 08:24]  Benicio: An Mani
[10/14/2025 08:24]  a cave dracolisk: *roars*
[10/14/2025 08:24]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:24]  Benicio: In Por Ylem
[10/14/2025 08:24]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:24]  Benicio: Kal Vas Flam
[10/14/2025 08:24]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:24]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:24]  System: You have 23 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 23
[10/14/2025 08:24]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:24]  System: Weapon ability ready.
[10/14/2025 08:24]  System: You have 22 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 22
[10/14/2025 08:24]  System: You have 21 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 21
[10/14/2025 08:24]  Benicio: Por Ort Grav
[10/14/2025 08:24]  System: You have 20 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 20
[10/14/2025 08:24]  You see: a forktongue battlekhan corpse
[10/14/2025 08:24]  System: You have 19 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 19
[10/14/2025 08:24]  System: You have 18 stealth steps remaining.
[10/14/2025 08:24]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:24]  [Razor]: Steps: 18
[10/14/2025 08:24]  System: You have 17 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 17
[10/14/2025 08:24]  [Razor]: New target set.
[10/14/2025 08:24]  Razor: Target: a harvestman
[10/14/2025 08:24]  System: You have 16 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 16
[10/14/2025 08:24]  Benicio: Kal Vas Flam
[10/14/2025 08:24]  System: You have 15 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 15
[10/14/2025 08:24]  System: You have 14 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 14
[10/14/2025 08:24]  System: You have 13 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 13
[10/14/2025 08:24]  System: You have 12 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 12
[10/14/2025 08:24]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:24]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 08:24]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:24]  Benicio: Kal Vas Flam
[10/14/2025 08:24]  You see: a cave dracolisk corpse
[10/14/2025 08:24]  a cave dracolisk: *roars*
[10/14/2025 08:24]  You see: a forktongue battlekhan corpse
[10/14/2025 08:24]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:24]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:24]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:24]  [Razor]: Steps: 0
[10/14/2025 08:24]  You see: a forktongue battlekhan corpse
[10/14/2025 08:24]  System: You are no longer hindered.
[10/14/2025 08:24]  System: You have 29 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: Steps: 29
[10/14/2025 08:24]  Benicio: Kal Vas Flam
[10/14/2025 08:24]  System: You have 28 stealth steps remaining.
[10/14/2025 08:24]  [Razor]: New target set.
[10/14/2025 08:24]  [Razor]: Steps: 28
[10/14/2025 08:24]  Razor: Attack: a harvestman
[10/14/2025 08:24]  System: You strike your target from the shadows!
[10/14/2025 08:24]  GawdHammer: You steal 271 gold coin.
[10/14/2025 08:24]  System: You steal 271 gold coin.
[10/14/2025 08:24]  GawdHammer: (Shadow Aspect 67,182.9/250000 xp)
[10/14/2025 08:24]  System: You have lost a little karma.
[10/14/2025 08:24]  System: You have gained a very large amount of karma.
[10/14/2025 08:24]  GawdHammer: (Shadow Aspect 67,203.4/250000 xp)
[10/14/2025 08:24]  System: -6000
[10/14/2025 08:24]  You see: a harvestman corpse
[10/14/2025 08:24]  a cave dracolisk: *looks violently ill*
[10/14/2025 08:24]  Benicio: An Mani
[10/14/2025 08:24]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:24]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 08:24]  You see: a cave dracolisk corpse
[10/14/2025 08:24]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:24]  Benicio: In Vas Mani
[10/14/2025 08:24]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:24]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:24]  [Razor]: New target set.
[10/14/2025 08:24]  Razor: Target: a harvestman
[10/14/2025 08:24]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:24]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:24]  Razor: Attack: a harvestman
[10/14/2025 08:25]  System: You have 23 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 23
[10/14/2025 08:25]  System: You have 22 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 22
[10/14/2025 08:25]  System: You have 21 stealth steps remaining.
[10/14/2025 08:25]  System: You strike your target from the shadows!
[10/14/2025 08:25]  GawdHammer: You steal 267 gold coin.
[10/14/2025 08:25]  System: You steal 267 gold coin.
[10/14/2025 08:25]  GawdHammer: (Shadow Aspect 67,208.5/250000 xp)
[10/14/2025 08:25]  System: You have lost a little karma.
[10/14/2025 08:25]  System: You have gained a very large amount of karma.
[10/14/2025 08:25]  GawdHammer: (Shadow Aspect 67,229.1/250000 xp)
[10/14/2025 08:25]  System: -6000
[10/14/2025 08:25]  You see: a harvestman corpse
[10/14/2025 08:25]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:25]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:25]  You see: a cave dracolisk corpse
[10/14/2025 08:25]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:25]  Benicio: Monster Hunter Initiate
[10/14/2025 08:25]  Benicio: [Recruit, Yew]
[10/14/2025 08:25]  Benicio: Lord Benicio
[10/14/2025 08:25]  Benicio: *shocked*
[10/14/2025 08:25]  a forktongue shocktrooper: a forktongue shocktrooper
[10/14/2025 08:25]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:25]  Benicio: In Vas Mani
[10/14/2025 08:25]  You see: a cave dracolisk corpse
[10/14/2025 08:25]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:25]  Benicio: An Mani
[10/14/2025 08:25]  a forktongue shocktrooper: *looks violently ill*
[10/14/2025 08:25]  Benicio: Por Ort Grav
[10/14/2025 08:25]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:25]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:25]  System: You must wait another 11 seconds before you may use another smoke bomb.
[10/14/2025 08:25]  You see: a wyvern corpse
[10/14/2025 08:25]  You see: a stone adder corpse
[10/14/2025 08:25]  a bonfire wisp: a bonfire wisp
[10/14/2025 08:25]  You see: a stone adder corpse
[10/14/2025 08:25]  You see: a stone adder corpse
[10/14/2025 08:25]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:25]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:25]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:25]  System: You may now use another smoke bomb.
[10/14/2025 08:25]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:25]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:25]  System: You have 23 stealth steps remaining.
[10/14/2025 08:25]  System: You must wait a few moments to use another skill.
[10/14/2025 08:25]  [Razor]: Steps: 23
[10/14/2025 08:25]  System: You have 22 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 22
[10/14/2025 08:25]  System: You have 21 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 21
[10/14/2025 08:25]  System: You have 20 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 20
[10/14/2025 08:25]  System: You have 19 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 19
[10/14/2025 08:25]  System: You have 18 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 18
[10/14/2025 08:25]  System: You have 17 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 17
[10/14/2025 08:25]  System: You have 16 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 16
[10/14/2025 08:25]  System: You have 15 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 15
[10/14/2025 08:25]  System: You have 14 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 14
[10/14/2025 08:25]  System: You have 13 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 13
[10/14/2025 08:25]  System: You have 12 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 12
[10/14/2025 08:25]  System: You have 11 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 11
[10/14/2025 08:25]  System: You have 10 stealth steps remaining.
[10/14/2025 08:25]  Razor: [10 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 10
[10/14/2025 08:25]  System: You have 9 stealth steps remaining.
[10/14/2025 08:25]  Razor: [9 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 9
[10/14/2025 08:25]  System: You have 8 stealth steps remaining.
[10/14/2025 08:25]  Razor: [8 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 8
[10/14/2025 08:25]  System: You have 7 stealth steps remaining.
[10/14/2025 08:25]  Razor: [7 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 7
[10/14/2025 08:25]  System: You have 6 stealth steps remaining.
[10/14/2025 08:25]  Razor: [6 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 6
[10/14/2025 08:25]  You see: a lava elemental corpse
[10/14/2025 08:25]  System: You have 5 stealth steps remaining.
[10/14/2025 08:25]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:25]  [Razor]: Steps: 5
[10/14/2025 08:25]  System: You have 4 stealth steps remaining.
[10/14/2025 08:25]  Razor: [4 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 4
[10/14/2025 08:25]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:25]  System: You have 3 stealth steps remaining.
[10/14/2025 08:25]  Razor: [3 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 3
[10/14/2025 08:25]  an ophidian matron: an ophidian matron
[10/14/2025 08:25]  a flame purger: a flame purger
[10/14/2025 08:25]  System: You will automatically stealth when out of steps.
[10/14/2025 08:25]  Razor: [Auto-stealth ready.]
[10/14/2025 08:25]  System: You have 2 stealth steps remaining.
[10/14/2025 08:25]  Razor: [2 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 2
[10/14/2025 08:25]  an ophidian matron: an ophidian matron
[10/14/2025 08:25]  System: You have 1 stealth steps remaining.
[10/14/2025 08:25]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 08:25]  [Razor]: Steps: 1
[10/14/2025 08:25]  System: You have 0 stealth steps remaining.
[10/14/2025 08:25]  System: You feel ready to continue stealthing.
[10/14/2025 08:25]  Razor: [Stop!]
[10/14/2025 08:25]  [Razor]: Steps: 0
[10/14/2025 08:25]  You see: a stone adder corpse
[10/14/2025 08:25]  You see: a stone adder corpse
[10/14/2025 08:25]  You see: a stone adder corpse
[10/14/2025 08:25]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:25]  System: You have 23 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 23
[10/14/2025 08:25]  System: You have 22 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 22
[10/14/2025 08:25]  System: You have 21 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 21
[10/14/2025 08:25]  System: You have 20 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 20
[10/14/2025 08:25]  System: You have 19 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 19
[10/14/2025 08:25]  System: You have 18 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 18
[10/14/2025 08:25]  System: You have 17 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 17
[10/14/2025 08:25]  System: You have 16 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 16
[10/14/2025 08:25]  System: You have 15 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 15
[10/14/2025 08:25]  System: You have 14 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 14
[10/14/2025 08:25]  Bleep: [Councilor, Yew]
[10/14/2025 08:25]  Bleep: Bleep
[10/14/2025 08:25]  System: You have 13 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 13
[10/14/2025 08:25]  System: You have 12 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 12
[10/14/2025 08:25]  [Razor]: New target set.
[10/14/2025 08:25]  Razor: Target: a flame purger
[10/14/2025 08:25]  Bleep: [Councilor, Yew]
[10/14/2025 08:25]  Bleep: Bleep
[10/14/2025 08:25]  System: You have 11 stealth steps remaining.
[10/14/2025 08:25]  [Razor]: Steps: 11
[10/14/2025 08:25]  System: You have 10 stealth steps remaining.
[10/14/2025 08:25]  Razor: [10 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 10
[10/14/2025 08:25]  System: You have 9 stealth steps remaining.
[10/14/2025 08:25]  Razor: [9 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 9
[10/14/2025 08:25]  System: You have 8 stealth steps remaining.
[10/14/2025 08:25]  Razor: [8 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 8
[10/14/2025 08:25]  System: Anvil & Forge (A&F) guild has reached prestige level 82.
[10/14/2025 08:25]  System: You have 7 stealth steps remaining.
[10/14/2025 08:25]  Razor: [7 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 7
[10/14/2025 08:25]  Razor: Attack: a flame purger
[10/14/2025 08:25]  System: You have 6 stealth steps remaining.
[10/14/2025 08:25]  Razor: [6 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 6
[10/14/2025 08:25]  System: You have 5 stealth steps remaining.
[10/14/2025 08:25]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:25]  [Razor]: Steps: 5
[10/14/2025 08:25]  System: You have 4 stealth steps remaining.
[10/14/2025 08:25]  Razor: [4 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 4
[10/14/2025 08:25]  a volcanite: a volcanite
[10/14/2025 08:25]  fire elemental: fire elemental
[10/14/2025 08:25]  System: You have 3 stealth steps remaining.
[10/14/2025 08:25]  Razor: [3 Steps...]
[10/14/2025 08:25]  [Razor]: Steps: 3
[10/14/2025 08:25]  a smoke dragon: a smoke dragon
[10/14/2025 08:25]  System: You have 2 stealth steps remaining.
[10/14/2025 08:25]  Razor: [2 Steps...]
[10/14/2025 08:25]  System: You strike your target from the shadows!
[10/14/2025 08:25]  System: You fail to steal from the creature.
[10/14/2025 08:25]  System: You have lost a little karma.
[10/14/2025 08:25]  System: You have gained a lot of karma.
[10/14/2025 08:25]  GawdHammer: (Shadow Aspect 67,242.1/250000 xp)
[10/14/2025 08:25]  System: -5000
[10/14/2025 08:25]  You see: a flame purger corpse
[10/14/2025 08:25]  an ember drake: an ember drake
[10/14/2025 08:25]  an ember drake: an ember drake
[10/14/2025 08:26]  You see: a flame purger corpse
[10/14/2025 08:26]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:26]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:26]  a lava elemental: a lava elemental
[10/14/2025 08:26]  a living flame: a living flame
[10/14/2025 08:26]  an ember dragon: an ember dragon
[10/14/2025 08:26]  System: You are now under the protection of the town guards.
[10/14/2025 08:26]  [Razor]: Steps: 0
[10/14/2025 08:26]  GawdHammer: You summon your mount.
[10/14/2025 08:26]  Troglodyte Rind: Troglodyte Rind
[10/14/2025 08:26]  Cayla: Cayla the mage
[10/14/2025 08:26]  Louis: Louis the thief
[10/14/2025 08:26]  Jacinda: Jacinda the healer
[10/14/2025 08:26]  Migina: Migina the provisioner
[10/14/2025 08:26]  Bitalo: Bitalo the beggar
[10/14/2025 08:26]  Braden: Braden the tailor
[10/14/2025 08:26]  Jacelyn: Jacelyn the scribe
[10/14/2025 08:26]  Ridgley: Ridgley the tanner
[10/14/2025 08:26]  Hiroshi: Hiroshi the butcher
[10/14/2025 08:26]  Takiyah: Takiyah the carpenter
[10/14/2025 08:26]  Yesmina: Yesmina the bard
[10/14/2025 08:26]  System: 68° 22'N, 177° 53'W
[10/14/2025 08:26]  System: Your skill in Tactics has decreased by 78.0.  It is now 0.0.
[10/14/2025 08:26]  GawdHammer: Kal Ort Por
[10/14/2025 08:26]  System: Now tracking: Cornwallis X (82 spaces to target)
[10/14/2025 08:26]  System: You have left the protection of the town guards.
[10/14/2025 08:26]  a great hart: a great hart
[10/14/2025 08:26]  a pack horse: (tame)
[10/14/2025 08:26]  a pack horse: [458/2000 stones]
[10/14/2025 08:26]  a pack horse: [125/125 items]
[10/14/2025 08:26]  a pack horse: a pack horse
[10/14/2025 08:26]  PaigeMe: [Recruit, OL]
[10/14/2025 08:26]  PaigeMe: PaigeMe
[10/14/2025 08:26]  a giant rat: a giant rat
[10/14/2025 08:26]  an ettin: an ettin
[10/14/2025 08:26]  a hind: a hind
[10/14/2025 08:26]  a pack horse: (tame)
[10/14/2025 08:26]  a pack horse: [198/2000 stones]
[10/14/2025 08:26]  a pack horse: [47/125 items]
[10/14/2025 08:26]  a pack horse: a pack horse
[10/14/2025 08:26]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 08:26]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 08:26]  You see: a storage shelf
[10/14/2025 08:26]  You see: [secure]
[10/14/2025 08:26]  System: Which container do you wish to restock this container from? (you may target yourself or a nearby friendly pack animal)
[10/14/2025 08:26]  System: 26 items were added.
[10/14/2025 08:26]  [Razor]: Warning: Spider's Silk amount is now 0!
[10/14/2025 08:26]  [Razor]: Warning: Ginseng amount is now 0!
[10/14/2025 08:26]  [Razor]: Warning: Black Pearl amount is now 0!
[10/14/2025 08:26]  [Razor]: Warning: Nightshade amount is now 0!
[10/14/2025 08:26]  [Razor]: Warning: Garlic amount is now 0!
[10/14/2025 08:26]  [Razor]: Ignore List cleared
[10/14/2025 08:26]  [Razor]: drop - Not holding anything
[10/14/2025 08:26]  Razor: Select sort container
[10/14/2025 08:26]  [Razor]: Select target for variable 'sort_container'
[10/14/2025 08:26]  [Razor]: 'sort_container' script variable updated to '0x4CB08DCB'
[10/14/2025 08:26]  Razor: unidentified female leather chest
[10/14/2025 08:26]  [Razor]: Added 1288413436 to ignore list
[10/14/2025 08:26]  Razor: unidentified studded skirt
[10/14/2025 08:26]  [Razor]: Added 1288389143 to ignore list
[10/14/2025 08:26]  Razor: unidentified studded skirt
[10/14/2025 08:26]  [Razor]: Added 1288086114 to ignore list
[10/14/2025 08:26]  Razor: unidentified ringmail arms
[10/14/2025 08:26]  [Razor]: Added 1288225321 to ignore list
[10/14/2025 08:26]  Razor: unidentified ringmail chest
[10/14/2025 08:26]  [Razor]: Added 1288265044 to ignore list
[10/14/2025 08:26]  Razor: unidentified chainmail arms
[10/14/2025 08:26]  [Razor]: Added 1288425838 to ignore list
[10/14/2025 08:26]  Razor: unidentified chainmail arms
[10/14/2025 08:26]  [Razor]: Added 1288021602 to ignore list
[10/14/2025 08:26]  Razor: unidentified female platemail chest
[10/14/2025 08:26]  [Razor]: Added 1288470533 to ignore list
[10/14/2025 08:26]  Razor: unidentified platemail skirt
[10/14/2025 08:26]  [Razor]: Added 1288397575 to ignore list
[10/14/2025 08:26]  Razor: unidentified celestial staff
[10/14/2025 08:26]  [Razor]: Added 1288107160 to ignore list
[10/14/2025 08:26]  Razor: unidentified celestial staff
[10/14/2025 08:26]  [Razor]: Added 1288513973 to ignore list
[10/14/2025 08:26]  Razor: unidentified fiendish staff
[10/14/2025 08:26]  [Razor]: Added 1288205514 to ignore list
[10/14/2025 08:26]  Razor: unidentified shaman staff
[10/14/2025 08:26]  [Razor]: Added 1288140711 to ignore list
[10/14/2025 08:26]  Razor: unidentified hunting bow
[10/14/2025 08:26]  [Razor]: Added 1288501696 to ignore list
[10/14/2025 08:26]  Razor: unidentified rapier
[10/14/2025 08:26]  [Razor]: Added 1288317066 to ignore list
[10/14/2025 08:26]  Razor: unidentified war fork
[10/14/2025 08:26]  [Razor]: Added 1288008419 to ignore list
[10/14/2025 08:26]  Razor: unidentified gemshorn (500/500 uses remaining)
[10/14/2025 08:26]  [Razor]: Added 1288333723 to ignore list
[10/14/2025 08:26]  Razor: unidentified harp (500/500 uses remaining)
[10/14/2025 08:26]  [Razor]: Added 1288357004 to ignore list
[10/14/2025 08:26]  Razor: unidentified maul
[10/14/2025 08:26]  [Razor]: Added 1288458174 to ignore list
[10/14/2025 08:26]  Razor: unidentified magic spellbook
[10/14/2025 08:26]  [Razor]: Added 1288250177 to ignore list
[10/14/2025 08:26]  Razor: unidentified magic spellbook
[10/14/2025 08:26]  [Razor]: Added 1288093801 to ignore list
[10/14/2025 08:26]  Razor: unidentified order shield
[10/14/2025 08:26]  [Razor]: Added 1288154911 to ignore list
[10/14/2025 08:26]  Razor: unidentified parry dagger
[10/14/2025 08:26]  [Razor]: Added 1288050070 to ignore list
[10/14/2025 08:26]  Razor: amethyst : 10
[10/14/2025 08:26]  [Razor]: Added 1288021601 to ignore list
[10/14/2025 08:26]  Razor: emerald : 4
[10/14/2025 08:26]  [Razor]: Added 1288140710 to ignore list
[10/14/2025 08:26]  Razor: tourmaline : 6
[10/14/2025 08:26]  [Razor]: Added 1288086113 to ignore list
[10/14/2025 08:26]  Razor: sapphire : 2
[10/14/2025 08:26]  [Razor]: Added 1288431226 to ignore list
[10/14/2025 08:26]  Razor: citrine : 6
[10/14/2025 08:26]  [Razor]: Added 1288057861 to ignore list
[10/14/2025 08:26]  Razor: amber : 2
[10/14/2025 08:26]  [Razor]: Added 1288250176 to ignore list
[10/14/2025 08:26]  Razor: ruby : 2
[10/14/2025 08:26]  [Razor]: Added 1288546538 to ignore list
[10/14/2025 08:26]  Razor: star sapphire : 2
[10/14/2025 08:26]  [Razor]: Added 1288357003 to ignore list
[10/14/2025 08:26]  Razor: a chromatic core
[10/14/2025 08:26]  [Razor]: Added 1288205515 to ignore list
[10/14/2025 08:26]  Razor: an arcane scroll
[10/14/2025 08:26]  [Razor]: Added 1288317067 to ignore list
[10/14/2025 08:26]  Razor: (sealed) lesser paragon chest (ophidian shaman)
[10/14/2025 08:26]  [Razor]: Added 1288317068 to ignore list
[10/14/2025 08:26]  System: You deposit 29,584 gold into your bank box.
[10/14/2025 08:26]  System: You deposit 8,171 gold into your bank box.
[10/14/2025 08:26]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:26]  Razor: Done.
[10/14/2025 08:27]  You see: pouch
[10/14/2025 08:27]  You see: (3 items, 12 stones)
[10/14/2025 08:27]  GawdHammer: -1
[10/14/2025 08:27]  System: You now have 0 trapped pouches remaining.
[10/14/2025 08:27]  You see: backpack
[10/14/2025 08:27]  You see: (37 items, 73 stones)
[10/14/2025 08:27]  GawdHammer: Legendary Backstabber
[10/14/2025 08:27]  GawdHammer: Lord GawdHammer
[10/14/2025 08:27]  You see: backpack
[10/14/2025 08:27]  You see: (124 items, 456 stones)
[10/14/2025 08:27]  System: That container cannot hold more items.
[10/14/2025 08:27]  GawdHammer: Legendary Backstabber
[10/14/2025 08:27]  GawdHammer: Lord GawdHammer
[10/14/2025 08:28]  System: That container cannot hold more items.
[10/14/2025 08:28]  a pack horse: (tame)
[10/14/2025 08:28]  a pack horse: [287/2000 stones]
[10/14/2025 08:28]  a pack horse: [70/125 items]
[10/14/2025 08:28]  a pack horse: a pack horse
[10/14/2025 08:28]  an ettin: an ettin
[10/14/2025 08:28]  a pack horse: (tame)
[10/14/2025 08:28]  a pack horse: [458/2000 stones]
[10/14/2025 08:28]  a pack horse: [125/125 items]
[10/14/2025 08:28]  a pack horse: a pack horse
[10/14/2025 08:28]  System: Ruined Lady has completed the achievement: Weaponmastery (Basic).
[10/14/2025 08:28]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 08:28]  a pack horse: (tame)
[10/14/2025 08:28]  a pack horse: [458/2000 stones]
[10/14/2025 08:28]  a pack horse: [125/125 items]
[10/14/2025 08:28]  a pack horse: a pack horse
[10/14/2025 08:28]  an ettin: an ettin
[10/14/2025 08:28]  an ettin: an ettin
[10/14/2025 08:28]  Razor: Select equipment container
[10/14/2025 08:28]  [Razor]: Select target for variable 'equipment_container'
[10/14/2025 08:28]  Razor: Target: a pack horse
[10/14/2025 08:28]  [Razor]: 'equipment_container' script variable updated to '0xA581B'
[10/14/2025 08:28]  Razor: Select equipment container
[10/14/2025 08:28]  [Razor]: Select target for variable 'equipment_container'
[10/14/2025 08:28]  [Razor]: 'equipment_container' script variable updated to '0x509AD3E1'
[10/14/2025 08:29]  System: Aremisen has completed the achievement: Skill Mastery (Advanced).
[10/14/2025 08:29]  System: Aremisen has completed the achievement: Carpenter (Epic).
[10/14/2025 08:30]  : marble floor
[10/14/2025 08:30]  : marble floor
[10/14/2025 08:30]  You see: backpack
[10/14/2025 08:30]  You see: (22 items, 109 stones)
[10/14/2025 08:30]  You see: backpack
[10/14/2025 08:30]  You see: (86 items, 347 stones)
[10/14/2025 08:30]  a giant rat: a giant rat
[10/14/2025 08:30]  a pack horse: (tame)
[10/14/2025 08:30]  a pack horse: [458/2000 stones]
[10/14/2025 08:30]  a pack horse: [125/125 items]
[10/14/2025 08:30]  a pack horse: a pack horse
[10/14/2025 08:30]  System: M.A.X has completed the achievement: Artificers Enclave Luminary.
[10/14/2025 08:30]  You see: backpack
[10/14/2025 08:30]  You see: (124 items, 456 stones)
[10/14/2025 08:30]  System: The world will save in 15 seconds.
[10/14/2025 08:30]  an ettin: an ettin
[10/14/2025 08:30]  System: The world is saving, please wait.
[10/14/2025 08:30]  System: World save complete. The entire process took 3.4 seconds.
[10/14/2025 08:31]  System: Opening door...
[10/14/2025 08:31]  System: As the owner, you refresh the house and its contents.
[10/14/2025 08:31]  an ettin: an ettin
[10/14/2025 08:31]  You see: a storage shelf
[10/14/2025 08:31]  You see: [secure]
[10/14/2025 08:31]  System: Resupply complete.
[10/14/2025 08:31]  System: Arira has completed the achievement: Maester (Epic).
[10/14/2025 08:31]  GawdHammer: Kal Ort Por
[10/14/2025 08:31]  System: One or more of your pack animals is not allowed in this location.
[10/14/2025 08:31]  GawdHammer: You dismiss your mount.
[10/14/2025 08:31]  You see: a monitor corpse
[10/14/2025 08:31]  a dragon whelp: a dragon whelp
[10/14/2025 08:31]  a monitor: a monitor
[10/14/2025 08:31]  a stonescale tribal: a stonescale tribal
[10/14/2025 08:31]  a drake whelp: a drake whelp
[10/14/2025 08:31]  a monitor: a monitor
[10/14/2025 08:31]  a stonescale tribal: a stonescale tribal
[10/14/2025 08:31]  a dragon whelp: a dragon whelp
[10/14/2025 08:31]  System: Your skill in Tactics has increased by 78.0.  It is now 78.0.
[10/14/2025 08:31]  a drake whelp: a drake whelp
[10/14/2025 08:31]  a monitor: a monitor
[10/14/2025 08:31]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 08:31]  System: Your attack crushes their armor!
[10/14/2025 08:31]  a monitor: -186
[10/14/2025 08:31]  a dragon whelp: a dragon whelp
[10/14/2025 08:31]  an army ant: an army ant
[10/14/2025 08:31]  a drake whelp: a drake whelp
[10/14/2025 08:31]  a primordial whelp: a primordial whelp
[10/14/2025 08:31]  System: Damage tracker has been reset.
[10/14/2025 08:31]  System: Damage tracker has been stopped.
[10/14/2025 08:31]  You see: an earth drake corpse
[10/14/2025 08:31]  You see: an earth drake corpse
[10/14/2025 08:31]  You see: an earth drake corpse
[10/14/2025 08:31]  You see: the remains of Labyrinthh
[10/14/2025 08:31]  an earth drake: an earth drake
[10/14/2025 08:31]  an army ant: an army ant
[10/14/2025 08:31]  System: Damage tracker has been reset.
[10/14/2025 08:31]  System: Damage tracker is now running.
[10/14/2025 08:31]  You see: an earth drake corpse
[10/14/2025 08:31]  You see: an earth drake corpse
[10/14/2025 08:31]  an earth drake: an earth drake
[10/14/2025 08:31]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:31]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:31]  [Razor]: Steps: 0
[10/14/2025 08:31]  [Razor]: Steps: 0
[10/14/2025 08:31]  System: You are no longer hindered.
[10/14/2025 08:31]  System: You have 29 stealth steps remaining.
[10/14/2025 08:31]  [Razor]: New target set.
[10/14/2025 08:31]  Razor: Target: an earth drake
[10/14/2025 08:31]  [Razor]: Steps: 29
[10/14/2025 08:31]  System: You have 28 stealth steps remaining.
[10/14/2025 08:31]  [Razor]: Steps: 28
[10/14/2025 08:31]  System: You have 27 stealth steps remaining.
[10/14/2025 08:31]  [Razor]: Steps: 27
[10/14/2025 08:31]  Razor: Attack: an earth drake
[10/14/2025 08:31]  System: You have 26 stealth steps remaining.
[10/14/2025 08:31]  [Razor]: Steps: 26
[10/14/2025 08:31]  System: You have 25 stealth steps remaining.
[10/14/2025 08:31]  System: You strike your target from the shadows!
[10/14/2025 08:31]  System: You fail to steal from the creature.
[10/14/2025 08:31]  System: You have lost a little karma.
[10/14/2025 08:31]  System: You have gained a lot of karma.
[10/14/2025 08:31]  GawdHammer: (Shadow Aspect 67,260.7/250000 xp)
[10/14/2025 08:31]  System: -6000
[10/14/2025 08:31]  You see: an earth drake corpse
[10/14/2025 08:31]  System: Grab Bag not found, setting to Backpack.
[10/14/2025 08:31]  System: Target the container to Grab items into.
[10/14/2025 08:31]  System: Grab Bag set: 1288722980
[10/14/2025 08:32]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:32]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  [Razor]: New target set.
[10/14/2025 08:32]  Razor: Target: an earth drake
[10/14/2025 08:32]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:32]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:32]  System: You have 23 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 23
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  System: You have 22 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 22
[10/14/2025 08:32]  Razor: Attack: an earth drake
[10/14/2025 08:32]  a drake: a drake
[10/14/2025 08:32]  System: You have 21 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 21
[10/14/2025 08:32]  a drake: a drake
[10/14/2025 08:32]  System: You have 20 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 20
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  System: You have 19 stealth steps remaining.
[10/14/2025 08:32]  System: You strike your target from the shadows!
[10/14/2025 08:32]  System: You have completed a society job [Steal 10 Black Goods] and have earned 4 points within the Adventurer Lodge!
[10/14/2025 08:32]  GawdHammer: You steal 252 gold coin.
[10/14/2025 08:32]  System: You steal 252 gold coin.
[10/14/2025 08:32]  GawdHammer: You steal very common black goods.
[10/14/2025 08:32]  System: You steal very common black goods.
[10/14/2025 08:32]  GawdHammer: (Shadow Aspect 67,265.4/250000 xp)
[10/14/2025 08:32]  System: You have lost a little karma.
[10/14/2025 08:32]  System: You have gained a lot of karma.
[10/14/2025 08:32]  GawdHammer: (Shadow Aspect 67,283.9/250000 xp)
[10/14/2025 08:32]  System: -6000
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  You see: an earth drake corpse
[10/14/2025 08:32]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:32]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:32]  You see: the remains of Labyrinthh
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  a drake: a drake
[10/14/2025 08:32]  a drake: a drake
[10/14/2025 08:32]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:32]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:32]  [Razor]: New target set.
[10/14/2025 08:32]  Razor: Target: a drake
[10/14/2025 08:32]  System: Doom Slug has completed the achievement: Engineer (Basic).
[10/14/2025 08:32]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:32]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:32]  System: You have 23 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 23
[10/14/2025 08:32]  System: You have 22 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 22
[10/14/2025 08:32]  System: You have 21 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 21
[10/14/2025 08:32]  System: You have 20 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 20
[10/14/2025 08:32]  System: You have 19 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 19
[10/14/2025 08:32]  Razor: Attack: a drake
[10/14/2025 08:32]  System: You strike your target from the shadows!
[10/14/2025 08:32]  GawdHammer: You steal 208 gold coin.
[10/14/2025 08:32]  System: You steal 208 gold coin.
[10/14/2025 08:32]  GawdHammer: You steal very common black goods.
[10/14/2025 08:32]  System: You steal very common black goods.
[10/14/2025 08:32]  GawdHammer: (Shadow Aspect 67,288.0/250000 xp)
[10/14/2025 08:32]  System: You have lost a little karma.
[10/14/2025 08:32]  System: You have gained a lot of karma.
[10/14/2025 08:32]  GawdHammer: (Shadow Aspect 67,304.5/250000 xp)
[10/14/2025 08:32]  System: -6000
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:32]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:32]  System: You may now use another smoke bomb.
[10/14/2025 08:32]  [Razor]: New target set.
[10/14/2025 08:32]  Razor: Target: a drake
[10/14/2025 08:32]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:32]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:32]  System: You have 23 stealth steps remaining.
[10/14/2025 08:32]  [Razor]: Steps: 23
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  System: You have 22 stealth steps remaining.
[10/14/2025 08:32]  Razor: Attack: a drake
[10/14/2025 08:32]  [Razor]: Steps: 22
[10/14/2025 08:32]  System: You have 21 stealth steps remaining.
[10/14/2025 08:32]  System: You strike your target from the shadows!
[10/14/2025 08:32]  GawdHammer: You steal 192 gold coin.
[10/14/2025 08:32]  System: You steal 192 gold coin.
[10/14/2025 08:32]  GawdHammer: You steal fishing map.
[10/14/2025 08:32]  System: You steal fishing map.
[10/14/2025 08:32]  GawdHammer: (Shadow Aspect 67,308.6/250000 xp)
[10/14/2025 08:32]  System: You have lost a little karma.
[10/14/2025 08:32]  System: You have gained a lot of karma.
[10/14/2025 08:32]  GawdHammer: (Shadow Aspect 67,325.0/250000 xp)
[10/14/2025 08:32]  System: -6000
[10/14/2025 08:32]  You see: a drake corpse
[10/14/2025 08:32]  System: That is too far away.
[10/14/2025 08:32]  System: That is too far away.
[10/14/2025 08:33]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:33]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:33]  a drake: a drake
[10/14/2025 08:33]  You see: a drake corpse
[10/14/2025 08:33]  You see: a drake corpse
[10/14/2025 08:33]  You see: a drake corpse
[10/14/2025 08:33]  You see: the remains of KaaPow
[10/14/2025 08:33]  You see: a drake corpse
[10/14/2025 08:33]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:33]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:33]  [Razor]: New target set.
[10/14/2025 08:33]  Razor: Target: a drake
[10/14/2025 08:33]  System: Bard Intentions has completed the achievement: Skill Proficiency (Basic).
[10/14/2025 08:33]  Null: Lord Null
[10/14/2025 08:33]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:33]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:33]  Razor: Attack: a drake
[10/14/2025 08:33]  System: You have 23 stealth steps remaining.
[10/14/2025 08:33]  System: You strike your target from the shadows!
[10/14/2025 08:33]  GawdHammer: You steal 204 gold coin.
[10/14/2025 08:33]  System: You steal 204 gold coin.
[10/14/2025 08:33]  GawdHammer: (Shadow Aspect 67,329.1/250000 xp)
[10/14/2025 08:33]  System: You have lost a little karma.
[10/14/2025 08:33]  System: You have gained a lot of karma.
[10/14/2025 08:33]  GawdHammer: (Shadow Aspect 67,345.6/250000 xp)
[10/14/2025 08:33]  System: -6000
[10/14/2025 08:33]  You see: a drake corpse
[10/14/2025 08:33]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:33]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:33]  System: Aryx Steele has completed the achievement: Affluent (Basic).
[10/14/2025 08:33]  You see: a smoke drake corpse
[10/14/2025 08:33]  You see: an adder corpse
[10/14/2025 08:33]  You see: an adder corpse
[10/14/2025 08:33]  You see: a smoke drake corpse
[10/14/2025 08:33]  You see: a smoke drake corpse
[10/14/2025 08:33]  You see: a smoke drake corpse
[10/14/2025 08:33]  You see: an eldritch drake corpse
[10/14/2025 08:33]  You see: an eldritch drake corpse
[10/14/2025 08:33]  You see: an eldritch drake corpse
[10/14/2025 08:33]  You see: an eldritch drake corpse
[10/14/2025 08:33]  an eldritch drake: an eldritch drake
[10/14/2025 08:33]  System: Rub My Barney has completed the achievement: Monstrous Slayer (Basic).
[10/14/2025 08:33]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:33]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:33]  [Razor]: Steps: 0
[10/14/2025 08:33]  [Razor]: Steps: 0
[10/14/2025 08:33]  System: You are no longer hindered.
[10/14/2025 08:33]  [Razor]: New target set.
[10/14/2025 08:33]  Razor: Target: an eldritch drake
[10/14/2025 08:33]  System: You have 29 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 29
[10/14/2025 08:33]  System: You have 28 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 28
[10/14/2025 08:33]  System: You have 27 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 27
[10/14/2025 08:33]  System: You have 26 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 26
[10/14/2025 08:33]  System: You have 25 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 25
[10/14/2025 08:33]  System: You have 24 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 24
[10/14/2025 08:33]  System: You have 23 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 23
[10/14/2025 08:33]  System: You have 22 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 22
[10/14/2025 08:33]  System: You have 21 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 21
[10/14/2025 08:33]  System: You have 20 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 20
[10/14/2025 08:33]  System: You have 19 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 19
[10/14/2025 08:33]  System: You have 18 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 18
[10/14/2025 08:33]  System: You have 17 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 17
[10/14/2025 08:33]  System: You have 16 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 16
[10/14/2025 08:33]  Razor: Attack: an eldritch drake
[10/14/2025 08:33]  System: You have 15 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 15
[10/14/2025 08:33]  System: You have 14 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 14
[10/14/2025 08:33]  System: You have 13 stealth steps remaining.
[10/14/2025 08:33]  [Razor]: Steps: 13
[10/14/2025 08:33]  System: You have 12 stealth steps remaining.
[10/14/2025 08:33]  System: You strike your target from the shadows!
[10/14/2025 08:33]  GawdHammer: You steal uncommon black goods.
[10/14/2025 08:33]  System: You steal uncommon black goods.
[10/14/2025 08:33]  GawdHammer: You steal 338 gold coin.
[10/14/2025 08:33]  System: You steal 338 gold coin.
[10/14/2025 08:33]  GawdHammer: (Shadow Aspect 67,351.9/250000 xp)
[10/14/2025 08:33]  System: You have lost a little karma.
[10/14/2025 08:33]  System: You have gained a very large amount of karma.
[10/14/2025 08:33]  GawdHammer: (Shadow Aspect 67,377.1/250000 xp)
[10/14/2025 08:33]  System: -6000
[10/14/2025 08:33]  You see: an eldritch drake corpse
[10/14/2025 08:33]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:34]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:34]  [Razor]: Queuing dragdrop request 2... 1.2 seconds left.
[10/14/2025 08:34]  [Razor]: Queuing dragdrop request 2... 1.4 seconds left.
[10/14/2025 08:34]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:34]  You see: an ember drake corpse
[10/14/2025 08:34]  You see: an ember drake corpse
[10/14/2025 08:34]  You see: an ember drake corpse
[10/14/2025 08:34]  You see: an eldritch drake corpse
[10/14/2025 08:34]  You see: an eldritch drake corpse
[10/14/2025 08:34]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:34]  You see: an ophidian dragonguard corpse
[10/14/2025 08:34]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:34]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:34]  [Razor]: New target set.
[10/14/2025 08:34]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:34]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:34]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:34]  System: You have 23 stealth steps remaining.
[10/14/2025 08:34]  [Razor]: Steps: 23
[10/14/2025 08:34]  System: You have 22 stealth steps remaining.
[10/14/2025 08:34]  [Razor]: Steps: 22
[10/14/2025 08:34]  System: You have 21 stealth steps remaining.
[10/14/2025 08:34]  [Razor]: Steps: 21
[10/14/2025 08:34]  System: You have 20 stealth steps remaining.
[10/14/2025 08:34]  [Razor]: Steps: 20
[10/14/2025 08:34]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:34]  System: You have 19 stealth steps remaining.
[10/14/2025 08:34]  [Razor]: Steps: 19
[10/14/2025 08:34]  System: You have 18 stealth steps remaining.
[10/14/2025 08:34]  System: You strike your target from the shadows!
[10/14/2025 08:34]  System: You fail to steal from the creature.
[10/14/2025 08:34]  System: You have lost a little karma.
[10/14/2025 08:34]  an ophidian dragonguard: -7654
[10/14/2025 08:34]  System: You have gained a very large amount of karma.
[10/14/2025 08:34]  GawdHammer: (Shadow Aspect 67,399.5/250000 xp)
[10/14/2025 08:34]  System: -346
[10/14/2025 08:34]  You see: an ophidian dragonguard corpse
[10/14/2025 08:34]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:34]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:34]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:34]  You see: an ophidian dragonguard corpse
[10/14/2025 08:34]  an ophidian dragonguard: Shash. Lis sitthissthy. Liththasaththilsyth shi sthasthy!
[10/14/2025 08:34]  You see: an earth dragon corpse
[10/14/2025 08:34]  You see: an ophidian shaman corpse
[10/14/2025 08:34]  Guy Swingalot: Brilliant
[10/14/2025 08:34]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:34]  You see: an earth dragon corpse
[10/14/2025 08:34]  an earth dragon: an earth dragon
[10/14/2025 08:34]  You see: an earth dragon corpse
[10/14/2025 08:34]  You see: a dragon corpse
[10/14/2025 08:34]  You see: an earth dragon corpse
[10/14/2025 08:34]  You see: a dragon corpse
[10/14/2025 08:34]  You see: a dragon corpse
[10/14/2025 08:34]  You see: a dragon corpse
[10/14/2025 08:34]  a dragon: a dragon
[10/14/2025 08:34]  You see: a dragon corpse
[10/14/2025 08:34]  System: You may now use another smoke bomb.
[10/14/2025 08:34]  You see: an ophidian shaman corpse
[10/14/2025 08:34]  Itchynuts: 5
[10/14/2025 08:34]  a cave dracolisk: *pacified 46s discord 1m 17s*
[10/14/2025 08:34]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:34]  Itchynuts: Transmuter
[10/14/2025 08:34]  Itchynuts: Itchynuts
[10/14/2025 08:34]  a cave dracolisk: *potion stuck*
[10/14/2025 08:34]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:35]  Greater Explosion potion: 4
[10/14/2025 08:35]  a harvestman: a harvestman
[10/14/2025 08:35]  Greater Explosion potion: 3
[10/14/2025 08:35]  Greater Explosion potion: 2
[10/14/2025 08:35]  Greater Explosion potion: 1
[10/14/2025 08:35]  You see: a cave dracolisk corpse
[10/14/2025 08:35]  You see: a harvestman corpse
[10/14/2025 08:35]  a harvestman: a harvestman
[10/14/2025 08:35]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:35]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:35]  [Razor]: Steps: 0
[10/14/2025 08:35]  [Razor]: Steps: 0
[10/14/2025 08:35]  System: You are no longer hindered.
[10/14/2025 08:35]  System: You have 29 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 29
[10/14/2025 08:35]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:35]  [Razor]: New target set.
[10/14/2025 08:35]  Razor: Target: a cave dracolisk
[10/14/2025 08:35]  System: You have 28 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 28
[10/14/2025 08:35]  System: You have 27 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 27
[10/14/2025 08:35]  System: You have 26 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 26
[10/14/2025 08:35]  Itchynuts: Transmuter
[10/14/2025 08:35]  Itchynuts: Itchynuts
[10/14/2025 08:35]  System: You have 25 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 25
[10/14/2025 08:35]  System: You have 24 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 24
[10/14/2025 08:35]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:35]  System: You will automatically stealth when out of steps.
[10/14/2025 08:35]  Razor: [Auto-stealth ready.]
[10/14/2025 08:35]  Razor: Attack: a cave dracolisk
[10/14/2025 08:35]  System: You strike your target from the shadows!
[10/14/2025 08:35]  GawdHammer: You steal 259 gold coin.
[10/14/2025 08:35]  System: You steal 259 gold coin.
[10/14/2025 08:35]  GawdHammer: You steal uncommon black goods.
[10/14/2025 08:35]  System: You steal uncommon black goods.
[10/14/2025 08:35]  GawdHammer: (Shadow Aspect 67,405.2/250000 xp)
[10/14/2025 08:35]  System: You have lost a little karma.
[10/14/2025 08:35]  System: You have gained a very large amount of karma.
[10/14/2025 08:35]  GawdHammer: (Shadow Aspect 67,428.1/250000 xp)
[10/14/2025 08:35]  System: -8000
[10/14/2025 08:35]  You see: a cave dracolisk corpse
[10/14/2025 08:35]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:35]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:35]  [Razor]: New target set.
[10/14/2025 08:35]  Razor: Target: a harvestman
[10/14/2025 08:35]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:35]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:35]  System: You have 23 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 23
[10/14/2025 08:35]  System: You have 22 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 22
[10/14/2025 08:35]  System: You have 21 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 21
[10/14/2025 08:35]  System: You have 20 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 20
[10/14/2025 08:35]  System: You have 19 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 19
[10/14/2025 08:35]  System: You have 18 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 18
[10/14/2025 08:35]  System: You have 17 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: New target set.
[10/14/2025 08:35]  [Razor]: Steps: 17
[10/14/2025 08:35]  Razor: Attack: a harvestman
[10/14/2025 08:35]  System: You strike your target from the shadows!
[10/14/2025 08:35]  GawdHammer: You steal 236 gold coin.
[10/14/2025 08:35]  System: You steal 236 gold coin.
[10/14/2025 08:35]  GawdHammer: (Shadow Aspect 67,433.2/250000 xp)
[10/14/2025 08:35]  System: You have lost a little karma.
[10/14/2025 08:35]  System: You have gained a very large amount of karma.
[10/14/2025 08:35]  GawdHammer: (Shadow Aspect 67,453.7/250000 xp)
[10/14/2025 08:35]  System: -6000
[10/14/2025 08:35]  You see: a harvestman corpse
[10/14/2025 08:35]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:35]  System: You must wait a few moments to use another skill.
[10/14/2025 08:35]  a dusk dragon: a dusk dragon
[10/14/2025 08:35]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:35]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:35]  [Razor]: New target set.
[10/14/2025 08:35]  Razor: Target: a harvestman
[10/14/2025 08:35]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:35]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:35]  System: You have 23 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 23
[10/14/2025 08:35]  Razor: Attack: a harvestman
[10/14/2025 08:35]  System: You have 22 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 22
[10/14/2025 08:35]  You see: a harvestman corpse
[10/14/2025 08:35]  You see: a cave dracolisk corpse
[10/14/2025 08:35]  System: You have 21 stealth steps remaining.
[10/14/2025 08:35]  [Razor]: Steps: 21
[10/14/2025 08:35]  System: You have 20 stealth steps remaining.
[10/14/2025 08:35]  System: You strike your target from the shadows!
[10/14/2025 08:35]  GawdHammer: You steal 241 gold coin.
[10/14/2025 08:35]  System: You steal 241 gold coin.
[10/14/2025 08:35]  GawdHammer: (Shadow Aspect 67,458.8/250000 xp)
[10/14/2025 08:35]  System: You have lost a little karma.
[10/14/2025 08:35]  System: You have gained a very large amount of karma.
[10/14/2025 08:35]  GawdHammer: (Shadow Aspect 67,479.4/250000 xp)
[10/14/2025 08:35]  System: -6000
[10/14/2025 08:35]  You see: a harvestman corpse
[10/14/2025 08:35]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:35]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:35]  You see: a harvestman corpse
[10/14/2025 08:35]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:35]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:35]  You see: a cave dracolisk corpse
[10/14/2025 08:35]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:36]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:36]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:36]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:36]  System: You may now use another smoke bomb.
[10/14/2025 08:36]  You see: a harvestman corpse
[10/14/2025 08:36]  You see: a harvestman corpse
[10/14/2025 08:36]  You see: a wyvern corpse
[10/14/2025 08:36]  You see: a wyvern corpse
[10/14/2025 08:36]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:36]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:36]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:36]  a bonfire wisp: a bonfire wisp
[10/14/2025 08:36]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:36]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:36]  a wyvern: a wyvern
[10/14/2025 08:36]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:36]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:36]  System: You have 23 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 23
[10/14/2025 08:36]  System: You have 22 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 22
[10/14/2025 08:36]  System: You have 21 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 21
[10/14/2025 08:36]  System: You have 20 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 20
[10/14/2025 08:36]  System: You have 19 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 19
[10/14/2025 08:36]  [Razor]: New target set.
[10/14/2025 08:36]  Razor: Target: an ophidian shaman
[10/14/2025 08:36]  System: You have 18 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 18
[10/14/2025 08:36]  System: You have 17 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 17
[10/14/2025 08:36]  System: You have 16 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 16
[10/14/2025 08:36]  System: You have 15 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 15
[10/14/2025 08:36]  Razor: Attack: an ophidian shaman
[10/14/2025 08:36]  System: You will automatically stealth when out of steps.
[10/14/2025 08:36]  Razor: [Auto-stealth ready.]
[10/14/2025 08:36]  System: You strike your target from the shadows!
[10/14/2025 08:36]  GawdHammer: You steal 316 gold coin.
[10/14/2025 08:36]  System: You steal 316 gold coin.
[10/14/2025 08:36]  GawdHammer: (Shadow Aspect 67,485.3/250000 xp)
[10/14/2025 08:36]  System: You have lost a little karma.
[10/14/2025 08:36]  System: You have gained a very large amount of karma.
[10/14/2025 08:36]  GawdHammer: (Shadow Aspect 67,509.1/250000 xp)
[10/14/2025 08:36]  System: -6974
[10/14/2025 08:36]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:36]  You see: an ophidian shaman corpse
[10/14/2025 08:36]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:36]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:36]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:36]  System: You have 23 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 23
[10/14/2025 08:36]  System: You have 22 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 22
[10/14/2025 08:36]  You see: a wyvern corpse
[10/14/2025 08:36]  System: You have 21 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 21
[10/14/2025 08:36]  System: You have 20 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 20
[10/14/2025 08:36]  System: You have 19 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 19
[10/14/2025 08:36]  [Razor]: New target set.
[10/14/2025 08:36]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:36]  System: You have 18 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 18
[10/14/2025 08:36]  System: You have 17 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 17
[10/14/2025 08:36]  System: You have 16 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 16
[10/14/2025 08:36]  System: You have 15 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 15
[10/14/2025 08:36]  System: You have 14 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 14
[10/14/2025 08:36]  System: You have 13 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 13
[10/14/2025 08:36]  [Razor]: New target set.
[10/14/2025 08:36]  Razor: Target: a bonfire wisp
[10/14/2025 08:36]  System: You have 12 stealth steps remaining.
[10/14/2025 08:36]  [Razor]: Steps: 12
[10/14/2025 08:36]  System: You have 11 stealth steps remaining.
[10/14/2025 08:36]  Razor: Attack: a bonfire wisp
[10/14/2025 08:36]  [Razor]: Steps: 11
[10/14/2025 08:36]  System: You will automatically stealth when out of steps.
[10/14/2025 08:36]  Razor: [Auto-stealth ready.]
[10/14/2025 08:36]  System: You have 10 stealth steps remaining.
[10/14/2025 08:36]  Razor: [10 Steps...]
[10/14/2025 08:36]  System: You strike your target from the shadows!
[10/14/2025 08:36]  GawdHammer: You steal common black goods.
[10/14/2025 08:36]  System: You steal common black goods.
[10/14/2025 08:36]  GawdHammer: You steal 360 gold coin.
[10/14/2025 08:36]  System: You steal 360 gold coin.
[10/14/2025 08:36]  GawdHammer: (Shadow Aspect 67,516.0/250000 xp)
[10/14/2025 08:36]  System: You have lost a little karma.
[10/14/2025 08:36]  GawdHammer: -13
[10/14/2025 08:36]  System: You have gained a very large amount of karma.
[10/14/2025 08:36]  GawdHammer: (Shadow Aspect 67,543.6/250000 xp)
[10/14/2025 08:36]  System: -6000
[10/14/2025 08:36]  You see: a bonfire wisp corpse
[10/14/2025 08:36]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:36]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:37]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:37]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:37]  System: You have 23 stealth steps remaining.
[10/14/2025 08:37]  [Razor]: Steps: 23
[10/14/2025 08:37]  System: You have 22 stealth steps remaining.
[10/14/2025 08:37]  [Razor]: Steps: 22
[10/14/2025 08:37]  a wyvern: a wyvern
[10/14/2025 08:37]  System: You have 21 stealth steps remaining.
[10/14/2025 08:37]  [Razor]: Steps: 21
[10/14/2025 08:37]  You see: a wyvern corpse
[10/14/2025 08:37]  You see: a wyvern corpse
[10/14/2025 08:37]  System: You have 20 stealth steps remaining.
[10/14/2025 08:37]  [Razor]: Steps: 20
[10/14/2025 08:37]  [Razor]: New target set.
[10/14/2025 08:37]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:37]  System: You have 19 stealth steps remaining.
[10/14/2025 08:37]  [Razor]: Steps: 19
[10/14/2025 08:37]  System: You have 18 stealth steps remaining.
[10/14/2025 08:37]  [Razor]: Steps: 18
[10/14/2025 08:37]  System: You have 17 stealth steps remaining.
[10/14/2025 08:37]  GawdHammer: +5
[10/14/2025 08:37]  System: some damage has been healed : 5
[10/14/2025 08:37]  [Razor]: Steps: 17
[10/14/2025 08:37]  System: You have 16 stealth steps remaining.
[10/14/2025 08:37]  [Razor]: Steps: 16
[10/14/2025 08:37]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:37]  System: You will automatically stealth when out of steps.
[10/14/2025 08:37]  Razor: [Auto-stealth ready.]
[10/14/2025 08:37]  System: You strike your target from the shadows!
[10/14/2025 08:37]  System: You fail to steal from the creature.
[10/14/2025 08:37]  System: You have lost a little karma.
[10/14/2025 08:37]  System: You have gained a very large amount of karma.
[10/14/2025 08:37]  GawdHammer: (Shadow Aspect 67,566.0/250000 xp)
[10/14/2025 08:37]  System: -8000
[10/14/2025 08:37]  a wyvern: a wyvern
[10/14/2025 08:37]  You see: an ophidian dragonguard corpse
[10/14/2025 08:37]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:37]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:37]  [Razor]: New target set.
[10/14/2025 08:37]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:37]  a wyvern: a wyvern
[10/14/2025 08:37]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:37]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:37]  [Razor]: New target set.
[10/14/2025 08:37]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:37]  System: You strike your target from the shadows!
[10/14/2025 08:37]  GawdHammer: You steal 296 gold coin.
[10/14/2025 08:37]  System: You steal 296 gold coin.
[10/14/2025 08:37]  GawdHammer: (Shadow Aspect 67,571.5/250000 xp)
[10/14/2025 08:37]  System: You have lost a little karma.
[10/14/2025 08:37]  an ophidian dragonguard: -7733
[10/14/2025 08:37]  an ophidian dragonguard: -2
[10/14/2025 08:37]  System: You pulverize your target.
[10/14/2025 08:37]  an ophidian dragonguard: Las this.
[10/14/2025 08:37]  an ophidian dragonguard: Ouch! Me hurt!
[10/14/2025 08:37]  an ophidian dragonguard: -6
[10/14/2025 08:37]  a wyvern: a wyvern
[10/14/2025 08:37]  a wyvern: a wyvern
[10/14/2025 08:37]  an ophidian dragonguard: -237
[10/14/2025 08:37]  You see: a wyvern corpse
[10/14/2025 08:37]  You see: a wyvern corpse
[10/14/2025 08:37]  System: Your attack crushes their armor!
[10/14/2025 08:37]  System: You have gained a very large amount of karma.
[10/14/2025 08:37]  GawdHammer: (Shadow Aspect 67,593.9/250000 xp)
[10/14/2025 08:37]  System: -22
[10/14/2025 08:37]  You see: an ophidian dragonguard corpse
[10/14/2025 08:37]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:37]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:37]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:37]  a wyvern: a wyvern
[10/14/2025 08:37]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:37]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:37]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:37]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:37]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 08:37]  [Razor]: Queuing dragdrop request 2... 1.2 seconds left.
[10/14/2025 08:37]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:38]  You see: a cave dracolisk corpse
[10/14/2025 08:38]  You see: a cave dracolisk corpse
[10/14/2025 08:38]  You see: a harvestman corpse
[10/14/2025 08:38]  You see: an ophidian shaman corpse
[10/14/2025 08:38]  You see: a dragon corpse
[10/14/2025 08:38]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:38]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:38]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:38]  You see: an earth dragon corpse
[10/14/2025 08:38]  You see: an earth dragon corpse
[10/14/2025 08:38]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:38]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:38]  You see: an ophidian dragonguard corpse
[10/14/2025 08:38]  [Razor]: New target set.
[10/14/2025 08:38]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:38]  an earth dragon: an earth dragon
[10/14/2025 08:38]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:38]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:38]  System: You have 23 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 23
[10/14/2025 08:38]  System: You have 22 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 22
[10/14/2025 08:38]  System: You have 21 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 21
[10/14/2025 08:38]  System: You have 20 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 20
[10/14/2025 08:38]  Gawfang: Master of Aspects
[10/14/2025 08:38]  Gawfang: [Soldier, -.-]
[10/14/2025 08:38]  Gawfang: Gawfang
[10/14/2025 08:38]  a zombie: (summoned)
[10/14/2025 08:38]  a zombie: a zombie
[10/14/2025 08:38]  a rag witch: (summoned)
[10/14/2025 08:38]  a rag witch: a rag witch
[10/14/2025 08:38]  an ancient mummy: (summoned)
[10/14/2025 08:38]  an ancient mummy: an ancient mummy
[10/14/2025 08:38]  System: You have 19 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 19
[10/14/2025 08:38]  System: You have 18 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 18
[10/14/2025 08:38]  System: You have 17 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 17
[10/14/2025 08:38]  System: Weapon ability ready.
[10/14/2025 08:38]  System: You have 16 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 16
[10/14/2025 08:38]  You see: an earth dragon corpse
[10/14/2025 08:38]  You see: an earth dragon corpse
[10/14/2025 08:38]  System: You have 15 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 15
[10/14/2025 08:38]  Evengelion: [Soloer, SoLE]
[10/14/2025 08:38]  Evengelion: Lady Evengelion
[10/14/2025 08:38]  Gawfang: All Guard
[10/14/2025 08:38]  System: You have 14 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 14
[10/14/2025 08:38]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:38]  an earth dragon: an earth dragon
[10/14/2025 08:38]  System: You have 13 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 13
[10/14/2025 08:38]  System: You have 12 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 12
[10/14/2025 08:38]  You see: an ophidian dragonguard corpse
[10/14/2025 08:38]  an earth dragon: an earth dragon
[10/14/2025 08:38]  Guy Swingalot: Brilliant
[10/14/2025 08:38]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:38]  System: You have 11 stealth steps remaining.
[10/14/2025 08:38]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:38]  [Razor]: Steps: 11
[10/14/2025 08:38]  System: You will automatically stealth when out of steps.
[10/14/2025 08:38]  Razor: [Auto-stealth ready.]
[10/14/2025 08:38]  System: You have 10 stealth steps remaining.
[10/14/2025 08:38]  Razor: [10 Steps...]
[10/14/2025 08:38]  System: You strike your target from the shadows!
[10/14/2025 08:38]  GawdHammer: You steal 286 gold coin.
[10/14/2025 08:38]  System: You steal 286 gold coin.
[10/14/2025 08:38]  GawdHammer: You steal very common black goods.
[10/14/2025 08:38]  System: You steal very common black goods.
[10/14/2025 08:38]  GawdHammer: (Shadow Aspect 67,599.5/250000 xp)
[10/14/2025 08:38]  System: You have lost a little karma.
[10/14/2025 08:38]  System: You have gained a very large amount of karma.
[10/14/2025 08:38]  GawdHammer: (Shadow Aspect 67,621.9/250000 xp)
[10/14/2025 08:38]  System: -8000
[10/14/2025 08:38]  Gawfang: In Nox
[10/14/2025 08:38]  You see: an ophidian dragonguard corpse
[10/14/2025 08:38]  Gawfang: In Jux Sanct
[10/14/2025 08:38]  Gawfang: Des Mani
[10/14/2025 08:38]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:38]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:38]  Gawfang: All Guard
[10/14/2025 08:38]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:38]  an ophidian dragonguard: Lil lis lythythalssthsas.
[10/14/2025 08:38]  an ophidian dragonguard: Away with thee!
[10/14/2025 08:38]  Gawfang: In Nox
[10/14/2025 08:38]  an earth dragon: an earth dragon
[10/14/2025 08:38]  Guy Swingalot: Brilliant
[10/14/2025 08:38]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:38]  Gawfang: Des Sanct
[10/14/2025 08:38]  You see: an ophidian dragonguard corpse
[10/14/2025 08:38]  a rag witch: (summoned)
[10/14/2025 08:38]  a rag witch: a rag witch
[10/14/2025 08:38]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:38]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:38]  [Razor]: New target set.
[10/14/2025 08:38]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:38]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:38]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:38]  System: You have 23 stealth steps remaining.
[10/14/2025 08:38]  [Razor]: Steps: 23
[10/14/2025 08:38]  You see: an ophidian dragonguard corpse
[10/14/2025 08:39]  System: You have 22 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 22
[10/14/2025 08:39]  a wyvern: a wyvern
[10/14/2025 08:39]  System: You have 21 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 21
[10/14/2025 08:39]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:39]  System: You have 20 stealth steps remaining.
[10/14/2025 08:39]  System: You strike your target from the shadows!
[10/14/2025 08:39]  System: You fail to steal from the creature.
[10/14/2025 08:39]  System: You have lost a little karma.
[10/14/2025 08:39]  System: You have gained a very large amount of karma.
[10/14/2025 08:39]  GawdHammer: (Shadow Aspect 67,644.3/250000 xp)
[10/14/2025 08:39]  System: -8000
[10/14/2025 08:39]  You see: an ophidian dragonguard corpse
[10/14/2025 08:39]  System: You must wait to perform another action.
[10/14/2025 08:39]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:39]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:39]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:39]  You see: an ophidian dragonguard corpse
[10/14/2025 08:39]  You see: an ophidian shaman corpse
[10/14/2025 08:39]  a zombie: (summoned)
[10/14/2025 08:39]  a zombie: a zombie
[10/14/2025 08:39]  an ancient mummy: (summoned)
[10/14/2025 08:39]  an ancient mummy: an ancient mummy
[10/14/2025 08:39]  an ophidian shaman: [Lethal Poison]
[10/14/2025 08:39]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:39]  You see: an earth dragon corpse
[10/14/2025 08:39]  You see: an earth dragon corpse
[10/14/2025 08:39]  a rag witch: (summoned)
[10/14/2025 08:39]  a rag witch: a rag witch
[10/14/2025 08:39]  an ophidian shaman: Thistsi aismisslyth sasisa.
[10/14/2025 08:39]  an ophidian shaman: Good blow!
[10/14/2025 08:39]  Guy Swingalot: Brilliant
[10/14/2025 08:39]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:39]  You see: an earth dragon corpse
[10/14/2025 08:39]  an earth dragon: an earth dragon
[10/14/2025 08:39]  Gawfang: Master of Aspects
[10/14/2025 08:39]  Gawfang: [Soldier, -.-]
[10/14/2025 08:39]  Gawfang: Gawfang
[10/14/2025 08:39]  an ophidian shaman: *looks violently ill*
[10/14/2025 08:39]  an ophidian shaman: Ratysississsil ythkththathislyth yssthi!
[10/14/2025 08:39]  an ophidian shaman: Away with thee!
[10/14/2025 08:39]  You see: an ophidian shaman corpse
[10/14/2025 08:39]  You see: an ophidian dragonguard corpse
[10/14/2025 08:39]  You see: an ophidian dragonguard corpse
[10/14/2025 08:39]  You see: an ophidian dragonguard corpse
[10/14/2025 08:39]  a wyvern: a wyvern
[10/14/2025 08:39]  You see: an eldritch drake corpse
[10/14/2025 08:39]  You see: an ember drake corpse
[10/14/2025 08:39]  an ember drake: an ember drake
[10/14/2025 08:39]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:39]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:39]  [Razor]: Steps: 0
[10/14/2025 08:39]  [Razor]: Steps: 0
[10/14/2025 08:39]  System: You are no longer hindered.
[10/14/2025 08:39]  System: You have 29 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 29
[10/14/2025 08:39]  System: You have 28 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 28
[10/14/2025 08:39]  [Razor]: New target set.
[10/14/2025 08:39]  Razor: Target: an ember drake
[10/14/2025 08:39]  System: You have 27 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 27
[10/14/2025 08:39]  System: You have 26 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 26
[10/14/2025 08:39]  Razor: Attack: an ember drake
[10/14/2025 08:39]  System: You have 25 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 25
[10/14/2025 08:39]  System: You have 24 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 24
[10/14/2025 08:39]  an ember drake: an ember drake
[10/14/2025 08:39]  System: You have 23 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 23
[10/14/2025 08:39]  System: You have 22 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 22
[10/14/2025 08:39]  System: You have 21 stealth steps remaining.
[10/14/2025 08:39]  System: You strike your target from the shadows!
[10/14/2025 08:39]  GawdHammer: You steal 219 gold coin.
[10/14/2025 08:39]  System: You steal 219 gold coin.
[10/14/2025 08:39]  GawdHammer: (Shadow Aspect 67,648.7/250000 xp)
[10/14/2025 08:39]  System: You have lost a little karma.
[10/14/2025 08:39]  System: You have gained a lot of karma.
[10/14/2025 08:39]  GawdHammer: (Shadow Aspect 67,666.6/250000 xp)
[10/14/2025 08:39]  System: -6000
[10/14/2025 08:39]  You see: an ember drake corpse
[10/14/2025 08:39]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:39]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:39]  an ember drake: an ember drake
[10/14/2025 08:39]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:39]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:39]  [Razor]: New target set.
[10/14/2025 08:39]  Razor: Target: an ember drake
[10/14/2025 08:39]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:39]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:39]  System: You have 23 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 23
[10/14/2025 08:39]  System: You have 22 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 22
[10/14/2025 08:39]  System: You have 21 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 21
[10/14/2025 08:39]  System: You have 20 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 20
[10/14/2025 08:39]  System: You have 19 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 19
[10/14/2025 08:39]  System: You have 18 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 18
[10/14/2025 08:39]  System: You have 17 stealth steps remaining.
[10/14/2025 08:39]  [Razor]: Steps: 17
[10/14/2025 08:40]  System: You have 16 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 16
[10/14/2025 08:40]  System: You have 15 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 15
[10/14/2025 08:40]  Razor: Attack: an ember drake
[10/14/2025 08:40]  System: You strike your target from the shadows!
[10/14/2025 08:40]  GawdHammer: You steal 227 gold coin.
[10/14/2025 08:40]  System: You steal 227 gold coin.
[10/14/2025 08:40]  GawdHammer: (Shadow Aspect 67,671.0/250000 xp)
[10/14/2025 08:40]  System: You have lost a little karma.
[10/14/2025 08:40]  System: You have gained a lot of karma.
[10/14/2025 08:40]  GawdHammer: (Shadow Aspect 67,688.9/250000 xp)
[10/14/2025 08:40]  System: -6000
[10/14/2025 08:40]  You see: an ember drake corpse
[10/14/2025 08:40]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:40]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:40]  Evengelion: [Soloer, SoLE]
[10/14/2025 08:40]  Evengelion: Lady Evengelion
[10/14/2025 08:40]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:40]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:40]  System: You have 23 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 23
[10/14/2025 08:40]  [Razor]: New target set.
[10/14/2025 08:40]  Razor: Target: an ember drake
[10/14/2025 08:40]  System: You have 22 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 22
[10/14/2025 08:40]  System: You have 21 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 21
[10/14/2025 08:40]  Razor: Attack: an ember drake
[10/14/2025 08:40]  System: You have 20 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 20
[10/14/2025 08:40]  System: You have 19 stealth steps remaining.
[10/14/2025 08:40]  System: You strike your target from the shadows!
[10/14/2025 08:40]  GawdHammer: You steal 206 gold coin.
[10/14/2025 08:40]  System: You steal 206 gold coin.
[10/14/2025 08:40]  GawdHammer: (Shadow Aspect 67,693.4/250000 xp)
[10/14/2025 08:40]  System: You have lost a little karma.
[10/14/2025 08:40]  System: You have gained a lot of karma.
[10/14/2025 08:40]  GawdHammer: (Shadow Aspect 67,711.2/250000 xp)
[10/14/2025 08:40]  System: -6000
[10/14/2025 08:40]  You see: an ember drake corpse
[10/14/2025 08:40]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:40]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:40]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:40]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:40]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:40]  You see: an ember drake corpse
[10/14/2025 08:40]  You see: an ember drake corpse
[10/14/2025 08:40]  You see: an eldritch drake corpse
[10/14/2025 08:40]  You see: an eldritch drake corpse
[10/14/2025 08:40]  You see: an eldritch drake corpse
[10/14/2025 08:40]  You see: an eldritch drake corpse
[10/14/2025 08:40]  an eldritch drake: an eldritch drake
[10/14/2025 08:40]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:40]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:40]  System: CherEfe has completed the achievement: Pirate (Basic).
[10/14/2025 08:40]  [Razor]: New target set.
[10/14/2025 08:40]  Razor: Target: an eldritch drake
[10/14/2025 08:40]  System: You may now use another smoke bomb.
[10/14/2025 08:40]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:40]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:40]  System: You have 23 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 23
[10/14/2025 08:40]  System: You have 22 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 22
[10/14/2025 08:40]  System: You have 21 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 21
[10/14/2025 08:40]  System: You have 20 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 20
[10/14/2025 08:40]  System: You have 19 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 19
[10/14/2025 08:40]  Razor: Attack: an eldritch drake
[10/14/2025 08:40]  System: You have 18 stealth steps remaining.
[10/14/2025 08:40]  [Razor]: Steps: 18
[10/14/2025 08:40]  System: You have 17 stealth steps remaining.
[10/14/2025 08:40]  System: You strike your target from the shadows!
[10/14/2025 08:40]  GawdHammer: You steal 334 gold coin.
[10/14/2025 08:40]  System: You steal 334 gold coin.
[10/14/2025 08:40]  GawdHammer: (Shadow Aspect 67,717.5/250000 xp)
[10/14/2025 08:40]  System: You have lost a little karma.
[10/14/2025 08:40]  System: You have gained a very large amount of karma.
[10/14/2025 08:40]  GawdHammer: (Shadow Aspect 67,742.8/250000 xp)
[10/14/2025 08:40]  System: -6000
[10/14/2025 08:40]  You see: an eldritch drake corpse
[10/14/2025 08:40]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:40]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:40]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:40]  [Razor]: Finished 4 queued actions in 1.8 seconds.
[10/14/2025 08:40]  You see: an ember drake corpse
[10/14/2025 08:40]  You see: an ember drake corpse
[10/14/2025 08:41]  You see: an ophidian dragonguard corpse
[10/14/2025 08:41]  You see: an ophidian dragonguard corpse
[10/14/2025 08:41]  You see: an ophidian dragonguard corpse
[10/14/2025 08:41]  You see: an ophidian dragonguard corpse
[10/14/2025 08:41]  a wyvern: a wyvern
[10/14/2025 08:41]  You see: an ophidian shaman corpse
[10/14/2025 08:41]  You see: an ophidian shaman corpse
[10/14/2025 08:41]  You see: an earth dragon corpse
[10/14/2025 08:41]  You see: an earth dragon corpse
[10/14/2025 08:41]  an earth dragon: an earth dragon
[10/14/2025 08:41]  Guy Swingalot: Brilliant
[10/14/2025 08:41]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:41]  You see: an earth dragon corpse
[10/14/2025 08:41]  You see: a dragon corpse
[10/14/2025 08:41]  You see: a dragon corpse
[10/14/2025 08:41]  a dragon: a dragon
[10/14/2025 08:41]  You see: a dragon corpse
[10/14/2025 08:41]  You see: an ophidian shaman corpse
[10/14/2025 08:41]  Guy Swingalot: Brilliant
[10/14/2025 08:41]  Guy Swingalot: Guy Swingalot
[10/14/2025 08:41]  You see: a cave dracolisk corpse
[10/14/2025 08:41]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:41]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:41]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:41]  [Razor]: New target set.
[10/14/2025 08:41]  Razor: Target: a cave dracolisk
[10/14/2025 08:41]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:41]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:41]  System: You have 23 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 23
[10/14/2025 08:41]  System: You have 22 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 22
[10/14/2025 08:41]  System: You have 21 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 21
[10/14/2025 08:41]  System: You have 20 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 20
[10/14/2025 08:41]  Razor: Attack: a cave dracolisk
[10/14/2025 08:41]  System: You have 19 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 19
[10/14/2025 08:41]  System: You have 18 stealth steps remaining.
[10/14/2025 08:41]  System: You strike your target from the shadows!
[10/14/2025 08:41]  GawdHammer: You steal 591 gold coin.
[10/14/2025 08:41]  System: You steal 591 gold coin.
[10/14/2025 08:41]  GawdHammer: (Shadow Aspect 67,748.5/250000 xp)
[10/14/2025 08:41]  System: You have lost a little karma.
[10/14/2025 08:41]  System: You have gained a very large amount of karma.
[10/14/2025 08:41]  GawdHammer: (Shadow Aspect 67,771.3/250000 xp)
[10/14/2025 08:41]  System: -8000
[10/14/2025 08:41]  You see: a harvestman corpse
[10/14/2025 08:41]  You see: a cave dracolisk corpse
[10/14/2025 08:41]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:41]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:41]  a harvestman: a harvestman
[10/14/2025 08:41]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:41]  System: You must wait a few moments to use another skill.
[10/14/2025 08:41]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:41]  System: You must wait a few moments to use another skill.
[10/14/2025 08:41]  You see: a cave dracolisk corpse
[10/14/2025 08:41]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:41]  System: You must wait a few moments to use another skill.
[10/14/2025 08:41]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:41]  GawdHammer: You can't seem to hide right now.
[10/14/2025 08:41]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:41]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:41]  System: You are no longer hindered.
[10/14/2025 08:41]  [Razor]: New target set.
[10/14/2025 08:41]  Razor: Target: a harvestman
[10/14/2025 08:41]  System: You have 29 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 29
[10/14/2025 08:41]  Razor: Attack: a harvestman
[10/14/2025 08:41]  System: You have 28 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 28
[10/14/2025 08:41]  System: You have 27 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 27
[10/14/2025 08:41]  System: You have 26 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 26
[10/14/2025 08:41]  System: You have 25 stealth steps remaining.
[10/14/2025 08:41]  [Razor]: Steps: 25
[10/14/2025 08:41]  You see: a cave dracolisk corpse
[10/14/2025 08:41]  System: You have 24 stealth steps remaining.
[10/14/2025 08:41]  System: You strike your target from the shadows!
[10/14/2025 08:41]  GawdHammer: You steal 254 gold coin.
[10/14/2025 08:41]  System: You steal 254 gold coin.
[10/14/2025 08:41]  GawdHammer: (Shadow Aspect 67,776.5/250000 xp)
[10/14/2025 08:41]  System: You have lost a little karma.
[10/14/2025 08:41]  System: You have gained a very large amount of karma.
[10/14/2025 08:41]  GawdHammer: (Shadow Aspect 67,797.0/250000 xp)
[10/14/2025 08:41]  System: -6000
[10/14/2025 08:41]  You see: a harvestman corpse
[10/14/2025 08:41]  Razor: You fail to hide.
[10/14/2025 08:42]  System: K'os has completed the achievement: Maester (Advanced).
[10/14/2025 08:42]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:42]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:42]  [Razor]: No one matching that was found on your screen.
[10/14/2025 08:42]  [Razor]: No one matching that was found on your screen.
[10/14/2025 08:42]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:42]  [Razor]: New target set.
[10/14/2025 08:42]  Razor: Target: a cave dracolisk
[10/14/2025 08:42]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:42]  System: You have 23 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 23
[10/14/2025 08:42]  You see: a cave dracolisk corpse
[10/14/2025 08:42]  System: You have 22 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 22
[10/14/2025 08:42]  System: You have 21 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 21
[10/14/2025 08:42]  System: You have 20 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 20
[10/14/2025 08:42]  System: You have 19 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 19
[10/14/2025 08:42]  System: You have 18 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 18
[10/14/2025 08:42]  a forktongue shocktrooper: a forktongue shocktrooper
[10/14/2025 08:42]  System: You have 17 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 17
[10/14/2025 08:42]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:42]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:42]  System: You have 16 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 16
[10/14/2025 08:42]  System: You have 15 stealth steps remaining.
[10/14/2025 08:42]  Razor: Attack: a cave dracolisk
[10/14/2025 08:42]  [Razor]: Steps: 15
[10/14/2025 08:42]  System: You have 14 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 14
[10/14/2025 08:42]  System: You have 13 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 13
[10/14/2025 08:42]  System: You have 12 stealth steps remaining.
[10/14/2025 08:42]  System: You strike your target from the shadows!
[10/14/2025 08:42]  System: You fail to steal from the creature.
[10/14/2025 08:42]  System: You have lost a little karma.
[10/14/2025 08:42]  a cave dracolisk: -6621
[10/14/2025 08:42]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:42]  a forktongue shocktrooper: a forktongue shocktrooper
[10/14/2025 08:42]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:42]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:42]  You see: a cave dracolisk corpse
[10/14/2025 08:42]  You see: a cave dracolisk corpse
[10/14/2025 08:42]  a cave dracolisk: *roars*
[10/14/2025 08:42]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:42]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:42]  [Razor]: New target set.
[10/14/2025 08:42]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:42]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:42]  System: You have 23 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 23
[10/14/2025 08:42]  System: You have 22 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 22
[10/14/2025 08:42]  System: You have 21 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 21
[10/14/2025 08:42]  System: You have 20 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 20
[10/14/2025 08:42]  System: You have 19 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 19
[10/14/2025 08:42]  System: You have 18 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 18
[10/14/2025 08:42]  System: You have 17 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 17
[10/14/2025 08:42]  System: You have 16 stealth steps remaining.
[10/14/2025 08:42]  [Razor]: Steps: 16
[10/14/2025 08:42]  System: You have 15 stealth steps remaining.
[10/14/2025 08:42]  System: You strike your target from the shadows!
[10/14/2025 08:42]  GawdHammer: You steal 270 gold coin.
[10/14/2025 08:42]  System: You steal 270 gold coin.
[10/14/2025 08:42]  GawdHammer: (Shadow Aspect 67,802.7/250000 xp)
[10/14/2025 08:42]  System: You have lost a little karma.
[10/14/2025 08:42]  System: You have gained a very large amount of karma.
[10/14/2025 08:42]  GawdHammer: (Shadow Aspect 67,825.6/250000 xp)
[10/14/2025 08:42]  System: -1379
[10/14/2025 08:42]  You see: a cave dracolisk corpse
[10/14/2025 08:42]  You see: a cave dracolisk corpse
[10/14/2025 08:42]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:42]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:42]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:42]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:42]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:42]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:42]  a forktongue shocktrooper: a forktongue shocktrooper
[10/14/2025 08:42]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:42]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:42]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:42]  a forktongue shocktrooper: a forktongue shocktrooper
[10/14/2025 08:42]  You see: a lava elemental corpse
[10/14/2025 08:42]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:42]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:42]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:42]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:42]  System: You may now use another smoke bomb.
[10/14/2025 08:42]  a harvestman: a harvestman
[10/14/2025 08:42]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:42]  a harvestman: a harvestman
[10/14/2025 08:42]  You see: a cave dracolisk corpse
[10/14/2025 08:42]  You see: a cave dracolisk corpse
[10/14/2025 08:42]  a harvestman: a harvestman
[10/14/2025 08:42]  a harvestman: a harvestman
[10/14/2025 08:42]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:42]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:42]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:43]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:43]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:43]  System: You have 23 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 23
[10/14/2025 08:43]  System: You have 22 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 22
[10/14/2025 08:43]  System: You have 21 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 21
[10/14/2025 08:43]  [Razor]: New target set.
[10/14/2025 08:43]  Razor: Target: a harvestman
[10/14/2025 08:43]  System: You have 20 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 20
[10/14/2025 08:43]  System: You have 19 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 19
[10/14/2025 08:43]  System: You have 18 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 18
[10/14/2025 08:43]  Razor: Attack: a harvestman
[10/14/2025 08:43]  System: You strike your target from the shadows!
[10/14/2025 08:43]  System: You fail to steal from the creature.
[10/14/2025 08:43]  System: You have lost a little karma.
[10/14/2025 08:43]  System: You have gained a very large amount of karma.
[10/14/2025 08:43]  GawdHammer: (Shadow Aspect 67,846.1/250000 xp)
[10/14/2025 08:43]  System: -6000
[10/14/2025 08:43]  You see: a harvestman corpse
[10/14/2025 08:43]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:43]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:43]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:43]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:43]  [Razor]: New target set.
[10/14/2025 08:43]  Razor: Target: a harvestman
[10/14/2025 08:43]  System: You have 23 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 23
[10/14/2025 08:43]  System: You have 22 stealth steps remaining.
[10/14/2025 08:43]  Razor: Attack: a harvestman
[10/14/2025 08:43]  [Razor]: Steps: 22
[10/14/2025 08:43]  System: You have 21 stealth steps remaining.
[10/14/2025 08:43]  [Razor]: Steps: 21
[10/14/2025 08:43]  a dusk dragon: a dusk dragon
[10/14/2025 08:43]  a harvestman: a harvestman
[10/14/2025 08:43]  System: You have 20 stealth steps remaining.
[10/14/2025 08:43]  System: You strike your target from the shadows!
[10/14/2025 08:43]  GawdHammer: You steal 280 gold coin.
[10/14/2025 08:43]  System: You steal 280 gold coin.
[10/14/2025 08:43]  GawdHammer: (Shadow Aspect 67,851.2/250000 xp)
[10/14/2025 08:43]  System: You have lost a little karma.
[10/14/2025 08:43]  System: You have gained a very large amount of karma.
[10/14/2025 08:43]  GawdHammer: (Shadow Aspect 67,871.8/250000 xp)
[10/14/2025 08:43]  System: -6000
[10/14/2025 08:43]  You see: a harvestman corpse
[10/14/2025 08:43]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:43]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:43]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:43]  You see: a cave dracolisk corpse
[10/14/2025 08:43]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:43]  You see: a cave dracolisk corpse
[10/14/2025 08:43]  You see: a cave dracolisk corpse
[10/14/2025 08:43]  You see: a cave dracolisk corpse
[10/14/2025 08:43]  You see: a harvestman corpse
[10/14/2025 08:43]  You see: a cave dracolisk corpse
[10/14/2025 08:43]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:43]  You see: a lava elemental corpse
[10/14/2025 08:43]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:43]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:43]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:43]  You see: a forktongue spinebreaker corpse
[10/14/2025 08:43]  You see: a forktongue battlekhan corpse
[10/14/2025 08:43]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:43]  You see: a forktongue shocktrooper corpse
[10/14/2025 08:43]  You see: a forktongue battlekhan corpse
[10/14/2025 08:43]  You see: a ruby wyrm corpse
[10/14/2025 08:43]  POTHEADD: Mythical Mini-Boss Slayer
[10/14/2025 08:43]  POTHEADD: Lord POTHEADD
[10/14/2025 08:43]  Evengelion: [Soloer, SoLE]
[10/14/2025 08:43]  Evengelion: Lady Evengelion
[10/14/2025 08:43]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:44]  You see: a harvestman corpse
[10/14/2025 08:44]  You see: a harvestman corpse
[10/14/2025 08:44]  You see: a cave dracolisk corpse
[10/14/2025 08:44]  You see: a harvestman corpse
[10/14/2025 08:44]  a cave dracolisk: a cave dracolisk
[10/14/2025 08:44]  You see: a harvestman corpse
[10/14/2025 08:44]  You see: a cave dracolisk corpse
[10/14/2025 08:44]  You see: a cave dracolisk corpse
[10/14/2025 08:44]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:44]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:44]  [Razor]: Steps: 0
[10/14/2025 08:44]  [Razor]: Steps: 0
[10/14/2025 08:44]  System: You are no longer hindered.
[10/14/2025 08:44]  System: You have 29 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 29
[10/14/2025 08:44]  System: You have 28 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 28
[10/14/2025 08:44]  [Razor]: New target set.
[10/14/2025 08:44]  Razor: Target: a cave dracolisk
[10/14/2025 08:44]  System: You have 27 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 27
[10/14/2025 08:44]  System: You have 26 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 26
[10/14/2025 08:44]  System: You have 25 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 25
[10/14/2025 08:44]  Razor: Attack: a cave dracolisk
[10/14/2025 08:44]  System: You have 24 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 24
[10/14/2025 08:44]  System: You have 23 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 23
[10/14/2025 08:44]  System: You have 22 stealth steps remaining.
[10/14/2025 08:44]  System: You strike your target from the shadows!
[10/14/2025 08:44]  GawdHammer: You steal 279 gold coin.
[10/14/2025 08:44]  System: You steal 279 gold coin.
[10/14/2025 08:44]  GawdHammer: (Shadow Aspect 67,877.5/250000 xp)
[10/14/2025 08:44]  System: You have lost a little karma.
[10/14/2025 08:44]  a cave dracolisk: -7336
[10/14/2025 08:44]  a cave dracolisk: *roars*
[10/14/2025 08:44]  You see: a cave dracolisk corpse
[10/14/2025 08:44]  GawdHammer: -15
[10/14/2025 08:44]  System: Your attack crushes their armor!
[10/14/2025 08:44]  System: You have gained a very large amount of karma.
[10/14/2025 08:44]  GawdHammer: (Shadow Aspect 67,900.3/250000 xp)
[10/14/2025 08:44]  System: -664
[10/14/2025 08:44]  ghostly dragonknight: ghostly dragonknight
[10/14/2025 08:44]  You see: a cave dracolisk corpse
[10/14/2025 08:44]  You see: a cave dracolisk corpse
[10/14/2025 08:44]  You see: a cave dracolisk corpse
[10/14/2025 08:44]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:44]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:44]  You see: a harvestman corpse
[10/14/2025 08:44]  an ophidian shaman: an ophidian shaman
[10/14/2025 08:44]  You see: a bonfire wisp corpse
[10/14/2025 08:44]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:44]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:44]  a wyvern: a wyvern
[10/14/2025 08:44]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:44]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:44]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:44]  System: You have 23 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 23
[10/14/2025 08:44]  System: You have 22 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 22
[10/14/2025 08:44]  System: You have 21 stealth steps remaining.
[10/14/2025 08:44]  GawdHammer: +5
[10/14/2025 08:44]  System: some damage has been healed : 5
[10/14/2025 08:44]  [Razor]: Steps: 21
[10/14/2025 08:44]  a bonfire wisp: a bonfire wisp
[10/14/2025 08:44]  System: You have 20 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 20
[10/14/2025 08:44]  System: You have 19 stealth steps remaining.
[10/14/2025 08:44]  GawdHammer: +2
[10/14/2025 08:44]  System: some damage has been healed : 2
[10/14/2025 08:44]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:44]  [Razor]: Steps: 19
[10/14/2025 08:44]  System: You have 18 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 18
[10/14/2025 08:44]  a wyvern: a wyvern
[10/14/2025 08:44]  System: You have 17 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 17
[10/14/2025 08:44]  System: You have 16 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: New target set.
[10/14/2025 08:44]  Razor: Target: an ophidian shaman
[10/14/2025 08:44]  [Razor]: Steps: 16
[10/14/2025 08:44]  System: You will automatically stealth when out of steps.
[10/14/2025 08:44]  Razor: [Auto-stealth ready.]
[10/14/2025 08:44]  System: You have 15 stealth steps remaining.
[10/14/2025 08:44]  [Razor]: Steps: 15
[10/14/2025 08:44]  Razor: Attack: an ophidian shaman
[10/14/2025 08:44]  System: You strike your target from the shadows!
[10/14/2025 08:44]  System: You fail to steal from the creature.
[10/14/2025 08:44]  System: You have lost a little karma.
[10/14/2025 08:44]  System: You have gained a very large amount of karma.
[10/14/2025 08:44]  GawdHammer: (Shadow Aspect 67,924.1/250000 xp)
[10/14/2025 08:44]  System: -7000
[10/14/2025 08:44]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:44]  You see: an ophidian shaman corpse
[10/14/2025 08:44]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:44]  [Razor]: New target set.
[10/14/2025 08:44]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:45]  System: You may now use another smoke bomb.
[10/14/2025 08:45]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:45]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:45]  System: You have 23 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 23
[10/14/2025 08:45]  a wyvern: a wyvern
[10/14/2025 08:45]  System: You have 22 stealth steps remaining.
[10/14/2025 08:45]  an ophidian dragonguard: Myss! Ciathys syth is its lithlasyts.
[10/14/2025 08:45]  [Razor]: Steps: 22
[10/14/2025 08:45]  System: You have 21 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 21
[10/14/2025 08:45]  [Razor]: New target set.
[10/14/2025 08:45]  System: Weapon ability ready.
[10/14/2025 08:45]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:45]  System: You will automatically stealth when out of steps.
[10/14/2025 08:45]  Razor: [Auto-stealth ready.]
[10/14/2025 08:45]  System: You strike your target from the shadows!
[10/14/2025 08:45]  System: You fail to steal from the creature.
[10/14/2025 08:45]  System: You have lost a little karma.
[10/14/2025 08:45]  System: You have gained a very large amount of karma.
[10/14/2025 08:45]  GawdHammer: (Shadow Aspect 67,946.5/250000 xp)
[10/14/2025 08:45]  System: -8000
[10/14/2025 08:45]  You see: an ophidian dragonguard corpse
[10/14/2025 08:45]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:45]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:45]  [Razor]: New target set.
[10/14/2025 08:45]  Razor: Target: an ophidian dragonguard
[10/14/2025 08:45]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:45]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:45]  System: You have 23 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 23
[10/14/2025 08:45]  System: The world will save in 15 seconds.
[10/14/2025 08:45]  System: You have 22 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 22
[10/14/2025 08:45]  System: You have 21 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 21
[10/14/2025 08:45]  System: You have 20 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 20
[10/14/2025 08:45]  Razor: Attack: an ophidian dragonguard
[10/14/2025 08:45]  System: You will automatically stealth when out of steps.
[10/14/2025 08:45]  Razor: [Auto-stealth ready.]
[10/14/2025 08:45]  System: You strike your target from the shadows!
[10/14/2025 08:45]  GawdHammer: You steal 272 gold coin.
[10/14/2025 08:45]  System: You steal 272 gold coin.
[10/14/2025 08:45]  GawdHammer: (Shadow Aspect 67,952.0/250000 xp)
[10/14/2025 08:45]  System: You have lost a little karma.
[10/14/2025 08:45]  an ophidian dragonguard: -7251
[10/14/2025 08:45]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:45]  GawdHammer: You can't seem to hide right now.
[10/14/2025 08:45]  GawdHammer: -15
[10/14/2025 08:45]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:45]  System: You begin to move quietly. (30 steps)
[10/14/2025 08:45]  System: You are no longer hindered.
[10/14/2025 08:45]  System: The world is saving, please wait.
[10/14/2025 08:45]  System: World save complete. The entire process took 3.3 seconds.
[10/14/2025 08:45]  System: You must wait a moment before using that skill.
[10/14/2025 08:45]  [Razor]: New target set.
[10/14/2025 08:45]  System: You have 29 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 29
[10/14/2025 08:45]  System: You have 28 stealth steps remaining.
[10/14/2025 08:45]  [Razor]: Steps: 28
[10/14/2025 08:45]  System: You strike your target from the shadows!
[10/14/2025 08:45]  System: You have already stolen from this creature.
[10/14/2025 08:45]  System: You have gained a very large amount of karma.
[10/14/2025 08:45]  GawdHammer: (Shadow Aspect 67,974.4/250000 xp)
[10/14/2025 08:45]  System: -749
[10/14/2025 08:45]  You see: an ophidian dragonguard corpse
[10/14/2025 08:45]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:45]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:46]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:46]  System: [TS] A Town Struggle (mounts allowed) will begin in 15 minutes. Town struggles may be accessed via green moongates near each town bank. Type [TownStruggle to view more details.
[10/14/2025 08:46]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:46]  System: You have 23 stealth steps remaining.
[10/14/2025 08:46]  GawdHammer: +5
[10/14/2025 08:46]  System: some damage has been healed : 5
[10/14/2025 08:46]  [Razor]: Steps: 23
[10/14/2025 08:46]  System: You have 22 stealth steps remaining.
[10/14/2025 08:46]  GawdHammer: +5
[10/14/2025 08:46]  System: some damage has been healed : 5
[10/14/2025 08:46]  [Razor]: New target set.
[10/14/2025 08:46]  Razor: Target: a bonfire wisp
[10/14/2025 08:46]  [Razor]: Steps: 22
[10/14/2025 08:46]  System: You have 21 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 21
[10/14/2025 08:46]  System: You have 20 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 20
[10/14/2025 08:46]  Razor: Attack: a bonfire wisp
[10/14/2025 08:46]  System: You have 19 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 19
[10/14/2025 08:46]  System: You have 18 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 18
[10/14/2025 08:46]  System: You have 17 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 17
[10/14/2025 08:46]  System: You have 16 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 16
[10/14/2025 08:46]  System: You have 15 stealth steps remaining.
[10/14/2025 08:46]  GawdHammer: -3
[10/14/2025 08:46]  System: You strike your target from the shadows!
[10/14/2025 08:46]  System: You fail to steal from the creature.
[10/14/2025 08:46]  System: You have lost a little karma.
[10/14/2025 08:46]  System: You have gained a very large amount of karma.
[10/14/2025 08:46]  GawdHammer: (Shadow Aspect 68,002.0/250000 xp)
[10/14/2025 08:46]  System: -6000
[10/14/2025 08:46]  You see: a bonfire wisp corpse
[10/14/2025 08:46]  GawdHammer: -1
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 1.0 seconds left.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 1.1 seconds left.
[10/14/2025 08:46]  [Razor]: Finished 5 queued actions in 2.4 seconds.
[10/14/2025 08:46]  a wyvern: a wyvern
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.9 seconds left.
[10/14/2025 08:46]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:46]  a wyvern: a wyvern
[10/14/2025 08:46]  You see: an ophidian dragonguard corpse
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.8 seconds left.
[10/14/2025 08:46]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:46]  You see: an ophidian shaman corpse
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:46]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.7 seconds left.
[10/14/2025 08:46]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:46]  [Razor]: Queuing dragdrop request 1... 0.6 seconds left.
[10/14/2025 08:46]  [Razor]: Finished 3 queued actions in 1.2 seconds.
[10/14/2025 08:46]  a wyvern: a wyvern
[10/14/2025 08:46]  You see: a wyvern corpse
[10/14/2025 08:46]  System: You must wait another 2 seconds before you may use another smoke bomb.
[10/14/2025 08:46]  Razor: Hiding and waiting to sneak...
[10/14/2025 08:46]  GawdHammer: You have hidden yourself well.
[10/14/2025 08:46]  System: You may now use another smoke bomb.
[10/14/2025 08:46]  System: You feel comfortable enough to begin stealthing.
[10/14/2025 08:46]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:46]  System: You have 23 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 23
[10/14/2025 08:46]  System: You have 22 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 22
[10/14/2025 08:46]  System: You have 21 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 21
[10/14/2025 08:46]  You see: a lava elemental corpse
[10/14/2025 08:46]  an ophidian matron: an ophidian matron
[10/14/2025 08:46]  System: You have 20 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 20
[10/14/2025 08:46]  an ophidian dragonguard: an ophidian dragonguard
[10/14/2025 08:46]  System: You have 19 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 19
[10/14/2025 08:46]  You see: an ember dragon corpse
[10/14/2025 08:46]  an ophidian matron: an ophidian matron
[10/14/2025 08:46]  System: You have 18 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 18
[10/14/2025 08:46]  You see: a lava elemental corpse
[10/14/2025 08:46]  You see: a flame purger corpse
[10/14/2025 08:46]  System: You have 17 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 17
[10/14/2025 08:46]  System: You have 16 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 16
[10/14/2025 08:46]  System: You have 15 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 15
[10/14/2025 08:46]  System: You have 14 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 14
[10/14/2025 08:46]  You see: an ophidian matron corpse
[10/14/2025 08:46]  System: You have 13 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 13
[10/14/2025 08:46]  You see: a lava elemental corpse
[10/14/2025 08:46]  System: You have 12 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 12
[10/14/2025 08:46]  System: You have 11 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 11
[10/14/2025 08:46]  System: You have 10 stealth steps remaining.
[10/14/2025 08:46]  Razor: [10 Steps...]
[10/14/2025 08:46]  [Razor]: Steps: 10
[10/14/2025 08:46]  System: You have 9 stealth steps remaining.
[10/14/2025 08:46]  Razor: [9 Steps...]
[10/14/2025 08:46]  [Razor]: Steps: 9
[10/14/2025 08:46]  System: You have 8 stealth steps remaining.
[10/14/2025 08:46]  Razor: [8 Steps...]
[10/14/2025 08:46]  [Razor]: Steps: 8
[10/14/2025 08:46]  System: You have 7 stealth steps remaining.
[10/14/2025 08:46]  Razor: [7 Steps...]
[10/14/2025 08:46]  [Razor]: Steps: 7
[10/14/2025 08:46]  System: You have 6 stealth steps remaining.
[10/14/2025 08:46]  Razor: [6 Steps...]
[10/14/2025 08:46]  [Razor]: Steps: 6
[10/14/2025 08:46]  You see: an ophidian matron corpse
[10/14/2025 08:46]  System: You have 5 stealth steps remaining.
[10/14/2025 08:46]  Razor: [Warning! - 5 steps remaining.]
[10/14/2025 08:46]  [Razor]: Steps: 5
[10/14/2025 08:46]  System: You have 4 stealth steps remaining.
[10/14/2025 08:46]  Razor: [4 Steps...]
[10/14/2025 08:46]  [Razor]: Steps: 4
[10/14/2025 08:46]  System: You will automatically stealth when out of steps.
[10/14/2025 08:46]  Razor: [Auto-stealth ready.]
[10/14/2025 08:46]  System: You have 3 stealth steps remaining.
[10/14/2025 08:46]  Razor: [3 Steps...]
[10/14/2025 08:46]  a stone adder: a stone adder
[10/14/2025 08:46]  [Razor]: Steps: 3
[10/14/2025 08:46]  You see: a living flame corpse
[10/14/2025 08:46]  You see: a fire elemental corpse
[10/14/2025 08:46]  System: You have 2 stealth steps remaining.
[10/14/2025 08:46]  Razor: [2 Steps...]
[10/14/2025 08:46]  [Razor]: Steps: 2
[10/14/2025 08:46]  System: You have 1 stealth steps remaining.
[10/14/2025 08:46]  Razor: [Warning! - 1 step remaining.]
[10/14/2025 08:46]  [Razor]: Steps: 1
[10/14/2025 08:46]  System: You have 0 stealth steps remaining.
[10/14/2025 08:46]  System: You feel ready to continue stealthing.
[10/14/2025 08:46]  Razor: [Stop!]
[10/14/2025 08:46]  a stone adder: a stone adder
[10/14/2025 08:46]  [Razor]: Steps: 0
[10/14/2025 08:46]  System: You begin to move quietly. (24 steps)
[10/14/2025 08:46]  System: You have 23 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 23
[10/14/2025 08:46]  System: You have 22 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 22
[10/14/2025 08:46]  System: You have 21 stealth steps remaining.
[10/14/2025 08:46]  [Razor]: Steps: 21
[10/14/2025 08:46]  System: You have 20 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 20
[10/14/2025 08:47]  System: You have 19 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 19
[10/14/2025 08:47]  System: You have 18 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 18
[10/14/2025 08:47]  You see: a volcanite corpse
[10/14/2025 08:47]  System: You have 17 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 17
[10/14/2025 08:47]  You see: a flame purger corpse
[10/14/2025 08:47]  System: You have 16 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 16
[10/14/2025 08:47]  an ember drake: an ember drake
[10/14/2025 08:47]  an ember drake: an ember drake
[10/14/2025 08:47]  System: You have 15 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 15
[10/14/2025 08:47]  System: You have 14 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 14
[10/14/2025 08:47]  a lava elemental: a lava elemental
[10/14/2025 08:47]  System: You have 13 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 13
[10/14/2025 08:47]  System: You have 12 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 12
[10/14/2025 08:47]  System: You have 11 stealth steps remaining.
[10/14/2025 08:47]  [Razor]: Steps: 11
[10/14/2025 08:47]  an ember dragon: an ember dragon
[10/14/2025 08:47]  System: You have 10 stealth steps remaining.
[10/14/2025 08:47]  Razor: [10 Steps...]
[10/14/2025 08:47]  [Razor]: Steps: 10
[10/14/2025 08:47]  System: You have 9 stealth steps remaining.
[10/14/2025 08:47]  Razor: [9 Steps...]
[10/14/2025 08:47]  [Razor]: Steps: 9
[10/14/2025 08:47]  System: You have 8 stealth steps remaining.
[10/14/2025 08:47]  Razor: [8 Steps...]
[10/14/2025 08:47]  [Razor]: Steps: 8
[10/14/2025 08:47]  System: You have 7 stealth steps remaining.
[10/14/2025 08:47]  Razor: [7 Steps...]
[10/14/2025 08:47]  [Razor]: Steps: 7
[10/14/2025 08:47]  System: You have 6 stealth steps remaining.
[10/14/2025 08:47]  Razor: [6 Steps...]
[10/14/2025 08:47]  [Razor]: Steps: 6
[10/14/2025 08:47]  an ember dragon: an ember dragon
[10/14/2025 08:47]  System: You are now under the protection of the town guards.
[10/14/2025 08:47]  [Razor]: Steps: 6
[10/14/2025 08:47]  GawdHammer: You summon your mount.
[10/14/2025 08:47]  Troglodyte Rind: Troglodyte Rind
[10/14/2025 08:47]  Cayla: Cayla the mage
[10/14/2025 08:47]  Louis: Louis the thief
[10/14/2025 08:47]  Jacinda: Jacinda the healer
[10/14/2025 08:47]  Migina: Migina the provisioner
[10/14/2025 08:47]  Bitalo: Bitalo the beggar
[10/14/2025 08:47]  Braden: Braden the tailor
[10/14/2025 08:47]  Jacelyn: Jacelyn the scribe
[10/14/2025 08:47]  Ridgley: Ridgley the tanner
[10/14/2025 08:47]  Hiroshi: Hiroshi the butcher
[10/14/2025 08:47]  Takiyah: Takiyah the carpenter
[10/14/2025 08:47]  Yesmina: Yesmina the bard
[10/14/2025 08:47]  System: Now tracking: Cornwallis X (82 spaces to target)
[10/14/2025 08:47]  [Razor]: Steps: 6
[10/14/2025 08:47]  Townsend: Townsend the alchemist
[10/14/2025 08:47]  Rusty: Rusty the farmer
[10/14/2025 08:47]  System: 68° 22'N, 177° 53'W
[10/14/2025 08:47]  System: Your skill in Tactics has decreased by 78.0.  It is now 0.0.
[10/14/2025 08:47]  GawdHammer: Kal Ort Por
[10/14/2025 08:47]  System: You have left the protection of the town guards.
[10/14/2025 08:47]  an ettin: an ettin
[10/14/2025 08:47]  a pack horse: (tame)
[10/14/2025 08:47]  a pack horse: [2/2000 stones]
[10/14/2025 08:47]  a pack horse: [1/125 items]
[10/14/2025 08:47]  a pack horse: a pack horse
[10/14/2025 08:47]  PaigeMe: [Recruit, OL]
[10/14/2025 08:47]  PaigeMe: PaigeMe
[10/14/2025 08:47]  a great hart: a great hart
[10/14/2025 08:47]  a pack horse: (tame)
[10/14/2025 08:47]  a pack horse: [297/2000 stones]
[10/14/2025 08:47]  a pack horse: [71/125 items]
[10/14/2025 08:47]  a pack horse: a pack horse
[10/14/2025 08:47]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 08:47]  : marble wall
[10/14/2025 08:47]  You see: a bank deposit safe
[10/14/2025 08:47]  You see: (176,412 gold balance)
[10/14/2025 08:47]  You see: [locked down]
[10/14/2025 08:47]  : grass
[10/14/2025 08:47]  You see: a storage shelf
[10/14/2025 08:47]  You see: [secure]
[10/14/2025 08:47]  System: Which container do you wish to restock this container from? (you may target yourself or a nearby friendly pack animal)
[10/14/2025 08:47]  System: 24 items were added.
[10/14/2025 08:47]  [Razor]: Warning: Mandrake Root amount is now 0!
[10/14/2025 08:47]  [Razor]: Warning: Nightshade amount is now 0!
[10/14/2025 08:47]  [Razor]: Warning: Blood Moss amount is now 0!
[10/14/2025 08:47]  [Razor]: Warning: Garlic amount is now 0!
[10/14/2025 08:47]  [Razor]: Ignore List cleared
[10/14/2025 08:47]  You see: pouch
[10/14/2025 08:47]  You see: (36 items, 472 stones)
[10/14/2025 08:47]  [Razor]: drop - Not holding anything
[10/14/2025 08:47]  Razor: Select sort container
[10/14/2025 08:47]  [Razor]: Select target for variable 'sort_container'
[10/14/2025 08:47]  [Razor]: 'sort_container' script variable updated to '0x4CD05A24'
[10/14/2025 08:47]  Razor: unidentified leather skirt
[10/14/2025 08:47]  [Razor]: Added 1288745259 to ignore list
[10/14/2025 08:47]  Razor: unidentified ringmail gloves
[10/14/2025 08:47]  [Razor]: Added 1289176630 to ignore list
[10/14/2025 08:47]  Razor: unidentified chainmail coif
[10/14/2025 08:47]  [Razor]: Added 1289088164 to ignore list
[10/14/2025 08:47]  Razor: unidentified platemail chest
[10/14/2025 08:47]  [Razor]: Added 1289140444 to ignore list
[10/14/2025 08:47]  Razor: unidentified celestial staff
[10/14/2025 08:47]  [Razor]: Added 1288837179 to ignore list
[10/14/2025 08:47]  Razor: unidentified vine staff
[10/14/2025 08:47]  [Razor]: Added 1288960643 to ignore list
[10/14/2025 08:47]  Razor: unidentified balestra
[10/14/2025 08:47]  [Razor]: Added 1289058642 to ignore list
[10/14/2025 08:47]  Razor: unidentified bow
[10/14/2025 08:47]  [Razor]: Added 1289164097 to ignore list
[10/14/2025 08:47]  Razor: unidentified hunting knife
[10/14/2025 08:47]  [Razor]: Added 1288903531 to ignore list
[10/14/2025 08:47]  Razor: unidentified harp (500/500 uses remaining)
[10/14/2025 08:47]  [Razor]: Added 1288780507 to ignore list
[10/14/2025 08:47]  Razor: unidentified mace
[10/14/2025 08:47]  [Razor]: Added 1288992642 to ignore list
[10/14/2025 08:47]  Razor: unidentified shepherd's crook
[10/14/2025 08:47]  [Razor]: Added 1289029332 to ignore list
[10/14/2025 08:47]  Razor: unidentified shepherd's crook
[10/14/2025 08:47]  [Razor]: Added 1288933855 to ignore list
[10/14/2025 08:47]  Razor: unidentified magic spellbook
[10/14/2025 08:47]  [Razor]: Added 1289202713 to ignore list
[10/14/2025 08:47]  Razor: unidentified magic spellbook
[10/14/2025 08:47]  [Razor]: Added 1289005770 to ignore list
[10/14/2025 08:47]  Razor: unidentified magic spellbook
[10/14/2025 08:47]  [Razor]: Added 1288860551 to ignore list
[10/14/2025 08:47]  Razor: unidentified magic spellbook
[10/14/2025 08:47]  [Razor]: Added 1288923181 to ignore list
[10/14/2025 08:47]  Razor: unidentified magic spellbook
[10/14/2025 08:47]  [Razor]: Added 1288815187 to ignore list
[10/14/2025 08:47]  Razor: unidentified bone shield
[10/14/2025 08:47]  [Razor]: Added 1289112093 to ignore list
[10/14/2025 08:47]  Razor: unidentified heater shield
[10/14/2025 08:47]  [Razor]: Added 1288913012 to ignore list
[10/14/2025 08:47]  Razor: unidentified wooden shield
[10/14/2025 08:47]  [Razor]: Added 1289195264 to ignore list
[10/14/2025 08:47]  Razor: unidentified executioner's axe
[10/14/2025 08:47]  [Razor]: Added 1288879095 to ignore list
[10/14/2025 08:47]  Razor: unidentified martial manual
[10/14/2025 08:47]  [Razor]: Added 1289012389 to ignore list
[10/14/2025 08:47]  Razor: unidentified harpoon
[10/14/2025 08:47]  [Razor]: Added 1288972290 to ignore list
[10/14/2025 08:47]  Razor: amethyst : 8
[10/14/2025 08:47]  [Razor]: Added 1288757489 to ignore list
[10/14/2025 08:47]  Razor: emerald : 2
[10/14/2025 08:47]  [Razor]: Added 1288903530 to ignore list
[10/14/2025 08:47]  Razor: tourmaline : 4
[10/14/2025 08:47]  [Razor]: Added 1288860550 to ignore list
[10/14/2025 08:47]  Razor: sapphire : 6
[10/14/2025 08:47]  [Razor]: Added 1289065661 to ignore list
[10/14/2025 08:47]  Razor: citrine : 4
[10/14/2025 08:47]  [Razor]: Added 1288871800 to ignore list
[10/14/2025 08:47]  Razor: ruby : 2
[10/14/2025 08:47]  [Razor]: Added 1288815186 to ignore list
[10/14/2025 08:47]  Razor: very common seed
[10/14/2025 08:47]  [Razor]: Added 1288817424 to ignore list
[10/14/2025 08:47]  Razor: common seed
[10/14/2025 08:47]  [Razor]: Added 1288972291 to ignore list
[10/14/2025 08:47]  Razor: Discipline Aspect Core
[10/14/2025 08:47]  [Razor]: Added 1288960644 to ignore list
[10/14/2025 08:47]  Razor: Gadget Aspect Distillation
[10/14/2025 08:47]  [Razor]: Added 1289112094 to ignore list
[10/14/2025 08:47]  Razor: verewood lumber map (undeciphered)
[10/14/2025 08:47]  [Razor]: Added 1288933856 to ignore list
[10/14/2025 08:47]  : [locked down]
[10/14/2025 08:47]  System: Lockdowns Used: 832 / 875
[10/14/2025 08:47]  System: You deposit 36,347 gold into your bank box.
[10/14/2025 08:48]  System: You deposit 7,139 gold into your bank box.
[10/14/2025 08:48]  [Razor]: Finished 2 queued actions in 0.6 seconds.
[10/14/2025 08:48]  Razor: Done.
[10/14/2025 08:48]  You see: pouch
[10/14/2025 08:48]  You see: (0 items, 0 stones)
[10/14/2025 08:48]  You see: a storage shelf
[10/14/2025 08:48]  You see: [secure]
[10/14/2025 08:48]  You see: a storage shelf
[10/14/2025 08:48]  You see: [secure]
[10/14/2025 08:48]  System: Resupply complete.
[10/14/2025 08:48]  System: Target the container to Grab items into.
[10/14/2025 08:48]  System: Grab Bag set: 1288722980
[10/14/2025 08:48]  PaigeMe: [Recruit, OL]
[10/14/2025 08:48]  PaigeMe: PaigeMe
[10/14/2025 08:48]  : white flowers
[10/14/2025 08:49]  System: Opening door...
[10/14/2025 08:49]  System: As the owner, you refresh the house and its contents.
[10/14/2025 08:49]  System: 89° 49'N, 71° 9'E
[10/14/2025 08:49]  GawdHammer: Kal Ort Por
[10/14/2025 08:49]  Nitara: Nitara
[10/14/2025 08:49]  Aricia: Aricia the noble
[10/14/2025 08:49]  ---------------E,T,C: ---------------E,T,C
[10/14/2025 08:49]  Soupous's Wares: Soupous's Wares
[10/14/2025 08:49]  Allen: Allen the house steward
[10/14/2025 08:49]  CORE / MATS / SCROLL: CORE / MATS / SCROLL
[10/14/2025 08:49]  Payton Andaria Gate: Payton Andaria Gate
[10/14/2025 08:49]  GRMNK SHOP: GRMNK SHOP
[10/14/2025 08:49]  Dusan: Dusan
[10/14/2025 08:49]  Andoria Mall N.Gate: Andoria Mall N.Gate
[10/14/2025 08:49]  Willard: Willard
[10/14/2025 08:49]  Price DROP: Price DROP
[10/14/2025 08:49]  TMAPPER TOOLS: TMAPPER TOOLS
[10/14/2025 08:49]  ScavMart: ScavMart
[10/14/2025 08:49]  NimbleThimble: NimbleThimble
[10/14/2025 08:49]  YaPPPragim: YaPPPragim
[10/14/2025 08:49]  Asia: Asia
[10/14/2025 08:49]  Marden: Marden
[10/14/2025 08:49]  Cores Destills Stuff: Cores Destills Stuff
[10/14/2025 08:49]  Rutherford: Rutherford
[10/14/2025 08:49]  +++TOP CHEF+++: +++TOP CHEF+++
[10/14/2025 08:49]  ++MAGIC CREW FOOD++: ++MAGIC CREW FOOD++
[10/14/2025 08:49]  Zella: Zella
[10/14/2025 08:49]  Kegs Masterychains: Kegs Masterychains
[10/14/2025 08:49]  Ballyrad: Ballyrad
[10/14/2025 08:49]  R MATS: R MATS
[10/14/2025 08:49]  Limited Edition Deco: Limited Edition Deco
[10/14/2025 08:49]  Romanoff's Vendor: Romanoff's Vendor
[10/14/2025 08:49]  REAGENTS by Cooper: REAGENTS by Cooper
[10/14/2025 08:49]  Kalmans Andaria Gate: Kalmans Andaria Gate
[10/14/2025 08:49]  210 ALCHEMY: 210 ALCHEMY
[10/14/2025 08:49]  Adley: Adley
[10/14/2025 08:49]  WTSSS: WTSSS
[10/14/2025 08:49]  DeDeDe: DeDeDe
[10/14/2025 08:49]  +Aarom+: +Aarom+
[10/14/2025 08:49]  Harden: Harden
[10/14/2025 08:49]  VIP MONK: VIP MONK
[10/14/2025 08:49]  Links rare cores: Links rare cores
[10/14/2025 08:49]  Mamba's: Mamba's
[10/14/2025 08:49]  SaarKindly: SaarKindly
[10/14/2025 08:49]  SLEEPY TRADER: SLEEPY TRADER
[10/14/2025 08:49]  Sasha Zaycev: Sasha Zaycev
[10/14/2025 08:49]  Sasha Zaycev II: Sasha Zaycev II
[10/14/2025 08:49]  Dagmar: Dagmar
[10/14/2025 08:49]  Aron: Aron
[10/14/2025 08:49]  Hilary: Hilary the battle trainer
[10/14/2025 08:49]  BuBu(1): BuBu(1)
[10/14/2025 08:49]  Zolod: Zolod
[10/14/2025 08:49]  SS CORES REAL CHEAP: SS CORES REAL CHEAP
[10/14/2025 08:49]  HMANK'S VENDOR: HMANK'S VENDOR
[10/14/2025 08:49]  Hamlin: Hamlin
[10/14/2025 08:49]  EFB's Honest Thrift: EFB's Honest Thrift
[10/14/2025 08:49]  ***--**--**--**--***: ***--**--**--**--***
[10/14/2025 08:49]  Blondi: Blondi
[10/14/2025 08:49]  EFB's Like-New Goods: [Thug and allies only]
[10/14/2025 08:49]  EFB's Like-New Goods: EFB's Like-New Goods
[10/14/2025 08:49]  Maya's Goods!: Maya's Goods!
[10/14/2025 08:49]  EFBs Innocent Arms!: EFBs Innocent Arms!
[10/14/2025 08:49]  Konistrar: Konistrar
[10/14/2025 08:49]  *Links+Stuff*: *Links+Stuff*
[10/14/2025 08:49]  Derry(Andaria MG): Derry(Andaria MG)
[10/14/2025 08:49]  Acelin: Acelin
[10/14/2025 08:49]  Backstabber's Weapon: Backstabber's Weapon
[10/14/2025 08:49]  CHEAP KEGS: CHEAP KEGS
[10/14/2025 08:49]  Zelia: Zelia
[10/14/2025 08:49]  ANDARia MoonGate: ANDARia MoonGate
[10/14/2025 08:49]  Mikasa Lee: Mikasa Lee
[10/14/2025 08:49]  Andaria MG N: Andaria MG N
[10/14/2025 08:49]  Aitan: Aitan
[10/14/2025 08:49]  Cores Distills Shiet: Cores Distills Shiet
[10/14/2025 08:49]  Lumber & Boards: Lumber & Boards
[10/14/2025 08:49]  Azirov: Azirov
[10/14/2025 08:49]  Flower Flower: Flower Flower
[10/14/2025 08:49]  Andaria  gate: Andaria  gate
[10/14/2025 08:49]  Miss Stuff: Miss Stuff
[10/14/2025 08:49]  GatheringGalvin: GatheringGalvin
[10/14/2025 08:49]  Gilana: Gilana the innkeeper
[10/14/2025 08:49]  Shura (Andaria): Shura (Andaria)
[10/14/2025 08:49]  -Weiss-: -Weiss-
[10/14/2025 08:49]  Cheap Rares and loot: Cheap Rares and loot
[10/14/2025 08:49]  --Chop Shop--: --Chop Shop--
[10/14/2025 08:49]  Ambrose: Ambrose
[10/14/2025 08:49]  Katrien: Katrien
[10/14/2025 08:49]  System: Opening door...
[10/14/2025 08:49]  Bradlaugh's bargains: Bradlaugh's bargains
[10/14/2025 08:49]  System: Opening door...
[10/14/2025 08:49]  Quella: Quella
[10/14/2025 08:49]  Girthy Goods: Girthy Goods
[10/14/2025 08:49]  Commodity Shop: Commodity Shop
[10/14/2025 08:49]  VVIVV: VVIVV
[10/14/2025 08:49]  Marlon: Marlon
[10/14/2025 08:49]  a black bear: a black bear
[10/14/2025 08:49]  Allen: Allen the house steward
[10/14/2025 08:49]  GRMNK SHOP: GRMNK SHOP
[10/14/2025 08:49]  REAGENTS by Cooper: REAGENTS by Cooper
[10/14/2025 08:49]  Romanoff's Vendor: Romanoff's Vendor
[10/14/2025 08:49]  SS CORES REAL CHEAP: SS CORES REAL CHEAP
[10/14/2025 08:49]  Hamlin: Hamlin
[10/14/2025 08:49]  EFB's Honest Thrift: EFB's Honest Thrift
[10/14/2025 08:49]  EFB's Like-New Goods: [Thug and allies only]
[10/14/2025 08:49]  EFB's Like-New Goods: EFB's Like-New Goods
[10/14/2025 08:49]  EFBs Innocent Arms!: EFBs Innocent Arms!
[10/14/2025 08:49]  Zolod: Zolod
[10/14/2025 08:49]  ***--**--**--**--***: ***--**--**--**--***
[10/14/2025 08:49]  Derry(Andaria MG): Derry(Andaria MG)
[10/14/2025 08:49]  Acelin: Acelin
[10/14/2025 08:49]  Backstabber's Weapon: Backstabber's Weapon
[10/14/2025 08:49]  CHEAP KEGS: CHEAP KEGS
[10/14/2025 08:49]  Zelia: Zelia
[10/14/2025 08:49]  ANDARia MoonGate: ANDARia MoonGate
[10/14/2025 08:49]  HMANK'S VENDOR: HMANK'S VENDOR
[10/14/2025 08:49]  Blondi: Blondi
[10/14/2025 08:49]  Maya's Goods!: Maya's Goods!
[10/14/2025 08:49]  *Links+Stuff*: *Links+Stuff*
[10/14/2025 08:49]  Mikasa Lee: Mikasa Lee
[10/14/2025 08:49]  Konistrar: Konistrar
[10/14/2025 08:49]  Andaria MG N: Andaria MG N
[10/14/2025 08:49]  Aitan: Aitan
[10/14/2025 08:49]  Cores Distills Shiet: Cores Distills Shiet
[10/14/2025 08:49]  Lumber & Boards: Lumber & Boards
[10/14/2025 08:49]  Azirov: Azirov
[10/14/2025 08:49]  System: Being perfectly rested, you shove them out of the way.
[10/14/2025 08:49]  Mamba's: Take a look at your goods.
[10/14/2025 08:49]  Mamba's: Take a look at your goods.
[10/14/2025 08:49]  System: 68° 22'N, 177° 53'W
[10/14/2025 08:49]  GawdHammer: Kal Ort Por
[10/14/2025 08:49]  an ettin: an ettin
[10/14/2025 08:49]  a pack horse: (tame)
[10/14/2025 08:49]  a pack horse: [2/2000 stones]
[10/14/2025 08:49]  a pack horse: [1/125 items]
[10/14/2025 08:49]  a pack horse: a pack horse
[10/14/2025 08:49]  PaigeMe: [Recruit, OL]
[10/14/2025 08:49]  PaigeMe: PaigeMe
[10/14/2025 08:49]  a pack horse: (tame)
[10/14/2025 08:49]  a pack horse: [394/2000 stones]
[10/14/2025 08:49]  a pack horse: [95/125 items]
[10/14/2025 08:49]  a pack horse: a pack horse
[10/14/2025 08:49]  a great hart: a great hart
[10/14/2025 08:49]  HereKittiKitti: HereKittiKitti the battle trainer
[10/14/2025 08:49]  Razor: Need more mana... trying again
[10/14/2025 08:49]  Razor: Need more mana... trying again
[10/14/2025 08:49]  Razor: Need more mana... trying again
[10/14/2025 08:49]  System: Opening door...
[10/14/2025 08:49]  System: As the owner, you refresh the house and its contents.
[10/14/2025 08:50]  System: A locker has completed the achievement: Skill Proficiency (Basic).
[10/14/2025 08:50]  System: A locker has completed the achievement: Tinker (Basic).
[10/14/2025 08:51]  System: Selif has completed the achievement: Monstrous Slayer (Basic).
[10/14/2025 08:52]  a hind: a hind
[10/14/2025 08:53]  You see: backpack
[10/14/2025 08:53]  You see: (86 items, 347 stones)
[10/14/2025 08:54]  [Razor]: Select target for variable 'container_to_catalog'
[10/14/2025 08:54]  [Razor]: 'container_to_catalog' script variable updated to '0x509AD3DD'
[10/14/2025 08:54]  [Razor]: Select target for variable 'take_container'
[10/14/2025 08:54]  [Razor]: 'take_container' script variable updated to '0x509AD3DD'
[10/14/2025 08:54]  [Razor]: Now we catalog items...
[10/14/2025 08:54]  [Razor]: 1352324061
[10/14/2025 08:54]  [Razor]: invulnerability bone helm
[10/14/2025 08:54]  [Razor]: Added 1462161023 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability bone helm
[10/14/2025 08:54]  [Razor]: Added 1237257019 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability bone legs
[10/14/2025 08:54]  [Razor]: Added 1595074502 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability leather jerkin
[10/14/2025 08:54]  [Razor]: Added 1173751746 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability female studded chest
[10/14/2025 08:54]  [Razor]: Added 1143735437 to ignore list
[10/14/2025 08:54]  [Razor]: durable invulnerability female studded chest
[10/14/2025 08:54]  [Razor]: Added 1196159040 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability studded arms
[10/14/2025 08:54]  [Razor]: Added 1128307301 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability studded cap
[10/14/2025 08:54]  [Razor]: Added 1679746790 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability studded leggings
[10/14/2025 08:54]  [Razor]: Added 1097446887 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability ringmail gloves
[10/14/2025 08:54]  [Razor]: Added 1265657700 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability chainmail arms
[10/14/2025 08:54]  [Razor]: Added 1133017134 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability chainmail chest
[10/14/2025 08:54]  [Razor]: Added 1193961640 to ignore list
[10/14/2025 08:54]  [Razor]: invulnerability chainmail gorget
[10/14/2025 08:54]  [Razor]: Added 1557555196 to ignore list
[10/14/2025 08:54]  [Razor]: durable invulnerability chainmail leggings
[10/14/2025 08:54]  [Razor]: Added 1557419669 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly accurate ruin lesser monstrous slaying ancestral staff
[10/14/2025 08:54]  [Razor]: Added 1198340244 to ignore list
[10/14/2025 08:54]  [Razor]: vanquishing angelspire staff
[10/14/2025 08:54]  [Razor]: Added 1407377214 to ignore list
[10/14/2025 08:54]  [Razor]: accurate might lesser undead slaying darklight staff
[10/14/2025 08:54]  [Razor]: Added 1867069383 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly accurate lesser monstrous slaying paradox staff
[10/14/2025 08:54]  [Razor]: Added 1788705041 to ignore list
[10/14/2025 08:54]  [Razor]: power paradox staff
[10/14/2025 08:54]  [Razor]: Added 1558340039 to ignore list
[10/14/2025 08:54]  [Razor]: might lesser beastial slaying paradox staff
[10/14/2025 08:54]  [Razor]: Added 1121817858 to ignore list
[10/14/2025 08:54]  [Razor]: supremely accurate prismatic staff
[10/14/2025 08:54]  [Razor]: Added 1365848856 to ignore list
[10/14/2025 08:54]  [Razor]: might daemonic slaying shaman staff
[10/14/2025 08:54]  [Razor]: Added 1552513207 to ignore list
[10/14/2025 08:54]  [Razor]: force lesser beastial slaying heavy crossbow
[10/14/2025 08:54]  [Razor]: Added 1140409812 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly accurate lesser humanoid slaying heavy crossbow
[10/14/2025 08:54]  [Razor]: Added 1134986944 to ignore list
[10/14/2025 08:54]  [Razor]: durable accurate power heavy crossbow
[10/14/2025 08:54]  [Razor]: Added 1124424654 to ignore list
[10/14/2025 08:54]  [Razor]: durable surpassingly accurate lesser nature slaying hunting bow
[10/14/2025 08:54]  [Razor]: Added 1190884802 to ignore list
[10/14/2025 08:54]  [Razor]: durable power hunting bow
[10/14/2025 08:54]  [Razor]: Added 1742719688 to ignore list
[10/14/2025 08:54]  [Razor]: accurate might lesser construct slaying assassin's knife
[10/14/2025 08:54]  [Razor]: Added 1474708106 to ignore list
[10/14/2025 08:54]  [Razor]: ruin lesser nature slaying epee
[10/14/2025 08:54]  [Razor]: Added 1726615015 to ignore list
[10/14/2025 08:54]  [Razor]: durable accurate ruin lesser nature slaying epee
[10/14/2025 08:54]  [Razor]: Added 1256680263 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly accurate ruin lesser elemental slaying epee
[10/14/2025 08:54]  [Razor]: Added 1132792318 to ignore list
[10/14/2025 08:54]  [Razor]: might lesser humanoid slaying fencing sabre
[10/14/2025 08:54]  [Razor]: Added 1499964930 to ignore list
[10/14/2025 08:54]  [Razor]: ruin humanoid slaying hunting knife
[10/14/2025 08:54]  [Razor]: Added 1251162835 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly accurate might lesser daemonic slaying kukri
[10/14/2025 08:54]  [Razor]: Added 1358234281 to ignore list
[10/14/2025 08:54]  [Razor]: durable surpassingly accurate lesser undead slaying rapier
[10/14/2025 08:54]  System: Pestilencia has completed the achievement: Daemonic Slayer (Basic).
[10/14/2025 08:54]  [Razor]: Added 1344751122 to ignore list
[10/14/2025 08:54]  [Razor]: power sarissa
[10/14/2025 08:54]  [Razor]: Added 1279348919 to ignore list
[10/14/2025 08:54]  [Razor]: durable might lesser humanoid slaying short spear
[10/14/2025 08:54]  [Razor]: Added 1212409449 to ignore list
[10/14/2025 08:54]  [Razor]: power lesser nature slaying short spear
[10/14/2025 08:54]  [Razor]: Added 2087698114 to ignore list
[10/14/2025 08:54]  [Razor]: power short spear
[10/14/2025 08:54]  [Razor]: Added 1197640043 to ignore list
[10/14/2025 08:54]  [Razor]: vanquishing war fork
[10/14/2025 08:54]  [Razor]: Added 1234432309 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly accurate ruin lesser undead slaying war fork
[10/14/2025 08:54]  [Razor]: Added 1543791181 to ignore list
[10/14/2025 08:54]  [Razor]: supremely melodious drums (500/500 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1480821759 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly melodious harp of lesser construct enticement (500/500 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1606484418 to ignore list
[10/14/2025 08:54]  [Razor]: durable supremely melodious hurdy-gurdy (600/600 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1475967753 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly melodious lute of lesser monstrous enticement (500/500 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1286193581 to ignore list
[10/14/2025 08:54]  [Razor]: supremely melodious lute (500/500 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1344067291 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly melodious psaltery of lesser beastial enticement (500/500 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1802080492 to ignore list
[10/14/2025 08:54]  [Razor]: durable supremely melodious psaltery (600/600 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1203855698 to ignore list
[10/14/2025 08:54]  [Razor]: melodious shawm of lesser construct enticement (500/500 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1818707696 to ignore list
[10/14/2025 08:54]  [Razor]: melodious tambourine of lesser construct enticement (500/500 uses remaining)
[10/14/2025 08:54]  [Razor]: Added 1146574565 to ignore list
[10/14/2025 08:54]  [Razor]: ruin lesser undead slaying club
[10/14/2025 08:54]  [Razor]: Added 1552482652 to ignore list
[10/14/2025 08:54]  [Razor]: durable might lesser beastial slaying giant spiked mace
[10/14/2025 08:54]  [Razor]: Added 1225993353 to ignore list
[10/14/2025 08:54]  [Razor]: durable surpassingly accurate power gnarled staff
[10/14/2025 08:54]  [Razor]: Added 1117584805 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly accurate construct slaying hammer pick
[10/14/2025 08:54]  [Razor]: Added 2014283845 to ignore list
[10/14/2025 08:54]  [Razor]: power war hammer
[10/14/2025 08:54]  [Razor]: Added 1711681776 to ignore list
[10/14/2025 08:54]  [Razor]: power war mace
[10/14/2025 08:54]  [Razor]: Added 1204639728 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly potent lesser nature slaying magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1237143584 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly potent lesser undead slaying magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1155489280 to ignore list
[10/14/2025 08:54]  [Razor]: unidentified magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1157225412 to ignore list
[10/14/2025 08:54]  [Razor]: potent might undead slaying magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1863172320 to ignore list
[10/14/2025 08:54]  [Razor]: durable eminently potent magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1529486430 to ignore list
[10/14/2025 08:54]  [Razor]: power magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1606718058 to ignore list
[10/14/2025 08:54]  [Razor]: supremely potent magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1285125012 to ignore list
[10/14/2025 08:54]  [Razor]: vanquishing magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1284827330 to ignore list
[10/14/2025 08:54]  [Razor]: surpassingly potent lesser humanoid slaying magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1509277693 to ignore list
[10/14/2025 08:54]  [Razor]: power magic spellbook
[10/14/2025 08:54]  [Razor]: Added 1599574661 to ignore list
[10/14/2025 08:54]  [Razor]: unidentified bone shield
[10/14/2025 08:54]  [Razor]: Added 1157338074 to ignore list
[10/14/2025 08:54]  [Razor]: durable invulnerability parry dagger
[10/14/2025 08:54]  [Razor]: Added 1122534793 to ignore list
[10/14/2025 08:54]  [Razor]: power great axe
[10/14/2025 08:54]  [Razor]: Added 1211812645 to ignore list
[10/14/2025 08:54]  [Razor]: accurate power halberd
[10/14/2025 08:55]  [Razor]: Added 1723799736 to ignore list
[10/14/2025 08:55]  [Razor]: unidentified halberd
[10/14/2025 08:55]  [Razor]: Added 1157757297 to ignore list
[10/14/2025 08:55]  [Razor]: surpassingly accurate lesser undead slaying two-handed axe
[10/14/2025 08:55]  [Razor]: Added 1284181149 to ignore list
[10/14/2025 08:55]  [Razor]: supremely accurate fistblade
[10/14/2025 08:55]  [Razor]: Added 1212243766 to ignore list
[10/14/2025 08:55]  [Razor]: substantial ruin lesser humanoid slaying fistblade
[10/14/2025 08:55]  [Razor]: Added 1863696537 to ignore list
[10/14/2025 08:55]  [Razor]: surpassingly accurate lesser daemonic slaying martial manual
[10/14/2025 08:55]  [Razor]: Added 1232802642 to ignore list
[10/14/2025 08:55]  [Razor]: power martial manual
[10/14/2025 08:55]  [Razor]: Added 1573815692 to ignore list
[10/14/2025 08:55]  [Razor]: durable might ocean slaying harpoon
[10/14/2025 08:55]  [Razor]: Added 1721624487 to ignore list
[10/14/2025 08:55]  [Razor]: Ignore List cleared
[10/14/2025 08:55]  [Razor]: Ignore List cleared
[10/14/2025 08:55]  [Razor]: Cataloguing done.
[10/14/2025 08:55]  [Razor]: You can now log out to create a journal entry. Finds your journal logs here: Game FolderClassicUODataClientJournalLogs
[10/14/2025 08:55]  System: Select an item or mobile to view/inspect
[10/14/2025 08:56]  [Razor]: Select target for variable 'container_to_catalog'
[10/14/2025 08:56]  [Razor]: 'container_to_catalog' script variable updated to '0x509AD3DD'
[10/14/2025 08:56]  [Razor]: Select target for variable 'take_container'
[10/14/2025 08:56]  [Razor]: 'take_container' script variable updated to '0x4CD8BE20'
[10/14/2025 08:56]  [Razor]: Now we catalog items...
[10/14/2025 08:56]  GawdHammer: -1
[10/14/2025 08:56]  System: You now have 9 trapped pouches remaining.
[10/14/2025 08:56]  [Razor]: 1352324061
[10/14/2025 08:56]  [Razor]: invulnerability bone helm
[10/14/2025 08:56]  [Razor]: Added 1462161023 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability bone helm
[10/14/2025 08:56]  [Razor]: Added 1237257019 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability bone legs
[10/14/2025 08:56]  [Razor]: Added 1595074502 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability leather jerkin
[10/14/2025 08:56]  [Razor]: Added 1173751746 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability female studded chest
[10/14/2025 08:56]  [Razor]: Added 1143735437 to ignore list
[10/14/2025 08:56]  [Razor]: durable invulnerability female studded chest
[10/14/2025 08:56]  [Razor]: Added 1196159040 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability studded arms
[10/14/2025 08:56]  [Razor]: Added 1128307301 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability studded cap
[10/14/2025 08:56]  [Razor]: Added 1679746790 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability studded leggings
[10/14/2025 08:56]  [Razor]: Added 1097446887 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability ringmail gloves
[10/14/2025 08:56]  [Razor]: Added 1265657700 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability chainmail arms
[10/14/2025 08:56]  [Razor]: Added 1133017134 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability chainmail chest
[10/14/2025 08:56]  [Razor]: Added 1193961640 to ignore list
[10/14/2025 08:56]  [Razor]: invulnerability chainmail gorget
[10/14/2025 08:56]  [Razor]: Added 1557555196 to ignore list
[10/14/2025 08:56]  [Razor]: durable invulnerability chainmail leggings
[10/14/2025 08:56]  [Razor]: Added 1557419669 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly accurate ruin lesser monstrous slaying ancestral staff
[10/14/2025 08:56]  [Razor]: Added 1198340244 to ignore list
[10/14/2025 08:56]  [Razor]: vanquishing angelspire staff
[10/14/2025 08:56]  [Razor]: Added 1407377214 to ignore list
[10/14/2025 08:56]  [Razor]: accurate might lesser undead slaying darklight staff
[10/14/2025 08:56]  [Razor]: Added 1867069383 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly accurate lesser monstrous slaying paradox staff
[10/14/2025 08:56]  [Razor]: Added 1788705041 to ignore list
[10/14/2025 08:56]  [Razor]: power paradox staff
[10/14/2025 08:56]  [Razor]: Added 1558340039 to ignore list
[10/14/2025 08:56]  [Razor]: might lesser beastial slaying paradox staff
[10/14/2025 08:56]  [Razor]: Added 1121817858 to ignore list
[10/14/2025 08:56]  [Razor]: supremely accurate prismatic staff
[10/14/2025 08:56]  [Razor]: Added 1365848856 to ignore list
[10/14/2025 08:56]  [Razor]: might daemonic slaying shaman staff
[10/14/2025 08:56]  [Razor]: Added 1552513207 to ignore list
[10/14/2025 08:56]  [Razor]: force lesser beastial slaying heavy crossbow
[10/14/2025 08:56]  [Razor]: Added 1140409812 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly accurate lesser humanoid slaying heavy crossbow
[10/14/2025 08:56]  [Razor]: Added 1134986944 to ignore list
[10/14/2025 08:56]  [Razor]: durable accurate power heavy crossbow
[10/14/2025 08:56]  [Razor]: Added 1124424654 to ignore list
[10/14/2025 08:56]  [Razor]: durable surpassingly accurate lesser nature slaying hunting bow
[10/14/2025 08:56]  [Razor]: Added 1190884802 to ignore list
[10/14/2025 08:56]  [Razor]: durable power hunting bow
[10/14/2025 08:56]  [Razor]: Added 1742719688 to ignore list
[10/14/2025 08:56]  [Razor]: accurate might lesser construct slaying assassin's knife
[10/14/2025 08:56]  [Razor]: Added 1474708106 to ignore list
[10/14/2025 08:56]  [Razor]: ruin lesser nature slaying epee
[10/14/2025 08:56]  [Razor]: Added 1726615015 to ignore list
[10/14/2025 08:56]  [Razor]: durable accurate ruin lesser nature slaying epee
[10/14/2025 08:56]  [Razor]: Added 1256680263 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly accurate ruin lesser elemental slaying epee
[10/14/2025 08:56]  [Razor]: Added 1132792318 to ignore list
[10/14/2025 08:56]  [Razor]: might lesser humanoid slaying fencing sabre
[10/14/2025 08:56]  [Razor]: Added 1499964930 to ignore list
[10/14/2025 08:56]  [Razor]: ruin humanoid slaying hunting knife
[10/14/2025 08:56]  [Razor]: Added 1251162835 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly accurate might lesser daemonic slaying kukri
[10/14/2025 08:56]  System: ItchyCraft has completed the achievement: Artificers Enclave Initiate.
[10/14/2025 08:56]  [Razor]: Added 1358234281 to ignore list
[10/14/2025 08:56]  [Razor]: durable surpassingly accurate lesser undead slaying rapier
[10/14/2025 08:56]  [Razor]: Added 1344751122 to ignore list
[10/14/2025 08:56]  [Razor]: power sarissa
[10/14/2025 08:56]  [Razor]: Added 1279348919 to ignore list
[10/14/2025 08:56]  [Razor]: durable might lesser humanoid slaying short spear
[10/14/2025 08:56]  [Razor]: Added 1212409449 to ignore list
[10/14/2025 08:56]  [Razor]: power lesser nature slaying short spear
[10/14/2025 08:56]  [Razor]: Added 2087698114 to ignore list
[10/14/2025 08:56]  [Razor]: power short spear
[10/14/2025 08:56]  [Razor]: Added 1197640043 to ignore list
[10/14/2025 08:56]  [Razor]: vanquishing war fork
[10/14/2025 08:56]  [Razor]: Added 1234432309 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly accurate ruin lesser undead slaying war fork
[10/14/2025 08:56]  [Razor]: Added 1543791181 to ignore list
[10/14/2025 08:56]  [Razor]: supremely melodious drums (500/500 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1480821759 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly melodious harp of lesser construct enticement (500/500 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1606484418 to ignore list
[10/14/2025 08:56]  [Razor]: durable supremely melodious hurdy-gurdy (600/600 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1475967753 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly melodious lute of lesser monstrous enticement (500/500 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1286193581 to ignore list
[10/14/2025 08:56]  [Razor]: supremely melodious lute (500/500 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1344067291 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly melodious psaltery of lesser beastial enticement (500/500 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1802080492 to ignore list
[10/14/2025 08:56]  [Razor]: durable supremely melodious psaltery (600/600 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1203855698 to ignore list
[10/14/2025 08:56]  [Razor]: melodious shawm of lesser construct enticement (500/500 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1818707696 to ignore list
[10/14/2025 08:56]  [Razor]: melodious tambourine of lesser construct enticement (500/500 uses remaining)
[10/14/2025 08:56]  [Razor]: Added 1146574565 to ignore list
[10/14/2025 08:56]  [Razor]: ruin lesser undead slaying club
[10/14/2025 08:56]  [Razor]: Added 1552482652 to ignore list
[10/14/2025 08:56]  [Razor]: durable might lesser beastial slaying giant spiked mace
[10/14/2025 08:56]  [Razor]: Added 1225993353 to ignore list
[10/14/2025 08:56]  [Razor]: durable surpassingly accurate power gnarled staff
[10/14/2025 08:56]  [Razor]: Added 1117584805 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly accurate construct slaying hammer pick
[10/14/2025 08:56]  [Razor]: Added 2014283845 to ignore list
[10/14/2025 08:56]  [Razor]: power war hammer
[10/14/2025 08:56]  [Razor]: Added 1711681776 to ignore list
[10/14/2025 08:56]  [Razor]: power war mace
[10/14/2025 08:56]  [Razor]: Added 1204639728 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly potent lesser nature slaying magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1237143584 to ignore list
[10/14/2025 08:56]  [Razor]: surpassingly potent lesser undead slaying magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1155489280 to ignore list
[10/14/2025 08:56]  [Razor]: unidentified magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1157225412 to ignore list
[10/14/2025 08:56]  [Razor]: potent might undead slaying magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1863172320 to ignore list
[10/14/2025 08:56]  [Razor]: durable eminently potent magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1529486430 to ignore list
[10/14/2025 08:56]  [Razor]: power magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1606718058 to ignore list
[10/14/2025 08:56]  [Razor]: supremely potent magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1285125012 to ignore list
[10/14/2025 08:56]  [Razor]: vanquishing magic spellbook
[10/14/2025 08:56]  [Razor]: Added 1284827330 to ignore list
[10/14/2025 08:57]  [Razor]: surpassingly potent lesser humanoid slaying magic spellbook
[10/14/2025 08:57]  [Razor]: Added 1509277693 to ignore list
[10/14/2025 08:57]  [Razor]: power magic spellbook
[10/14/2025 08:57]  [Razor]: Added 1599574661 to ignore list
[10/14/2025 08:57]  [Razor]: unidentified bone shield
[10/14/2025 08:57]  [Razor]: Added 1157338074 to ignore list
[10/14/2025 08:57]  [Razor]: durable invulnerability parry dagger
[10/14/2025 08:57]  [Razor]: Added 1122534793 to ignore list
[10/14/2025 08:57]  [Razor]: power great axe
[10/14/2025 08:57]  [Razor]: Added 1211812645 to ignore list
[10/14/2025 08:57]  [Razor]: accurate power halberd
[10/14/2025 08:57]  [Razor]: Added 1723799736 to ignore list
[10/14/2025 08:57]  [Razor]: unidentified halberd
[10/14/2025 08:57]  [Razor]: Added 1157757297 to ignore list
[10/14/2025 08:57]  [Razor]: surpassingly accurate lesser undead slaying two-handed axe
[10/14/2025 08:57]  [Razor]: Added 1284181149 to ignore list
[10/14/2025 08:57]  [Razor]: supremely accurate fistblade
[10/14/2025 08:57]  [Razor]: Added 1212243766 to ignore list
[10/14/2025 08:57]  [Razor]: substantial ruin lesser humanoid slaying fistblade
[10/14/2025 08:57]  [Razor]: Added 1863696537 to ignore list
[10/14/2025 08:57]  [Razor]: surpassingly accurate lesser daemonic slaying martial manual
[10/14/2025 08:57]  [Razor]: Added 1232802642 to ignore list
[10/14/2025 08:57]  [Razor]: power martial manual
[10/14/2025 08:57]  [Razor]: Added 1573815692 to ignore list
[10/14/2025 08:57]  [Razor]: durable might ocean slaying harpoon
[10/14/2025 08:57]  [Razor]: Added 1721624487 to ignore list
[10/14/2025 08:57]  [Razor]: durable accurate power trident
[10/14/2025 08:57]  [Razor]: Added 1424129655 to ignore list
[10/14/2025 08:57]  [Razor]: power trident
[10/14/2025 08:57]  [Razor]: Added 1758019139 to ignore list
[10/14/2025 08:57]  [Razor]: Ignore List cleared
[10/14/2025 08:57]  [Razor]: Ignore List cleared
[10/14/2025 08:57]  [Razor]: Cataloguing done.
[10/14/2025 08:57]  [Razor]: You can now log out to create a journal entry. Finds your journal logs here: Game FolderClassicUODataClientJournalLogs
[10/14/2025 08:57]  [Razor]: Select a new target this Action.
[10/14/2025 08:57]  You see: backpack
[10/14/2025 08:57]  You see: (0 items, 0 stones)
`;

function handleDragOver(e) {
  isDragging.value = true;
}

function handleDragEnter(e) {
  isDragging.value = true;
}

function handleDragLeave(e) {
  isDragging.value = false;
}

function handleDrop(e) {
  isDragging.value = false;
  
  const files = e.dataTransfer.files;
  
  if (files.length === 0) {
    msg.value = 'No file dropped';
    return;
  }
  
  const file = files[0];
  
  // Check if it's a text-based file
  if (!file.type.match('text.*') && !file.name.endsWith('.json') && !file.name.endsWith('.log')) {
    msg.value = 'Please drop a text, JSON, or log file';
    return;
  }
  
  const reader = new FileReader();
  
  reader.onload = (event) => {
    input.value = event.target.result;
    msg.value = `File "${file.name}" loaded successfully`;
  };
  
  reader.onerror = () => {
    msg.value = 'Error reading file';
  };
  
  reader.readAsText(file);
}

function parseId(logData) {

  // Look for the first occurrence of "[Razor]: " followed by exactly 10 digits
  const idMatch = logData.match(/\[Razor\]: (\d{10})/);

  if (idMatch) {

      return idMatch[1]; // Return the ID if found

  }

  return null; // Return null if no ID is found

}

function parseContainerDate(logData) {

  // Look for the first occurrence of "[MM/DD/YYYY"

  const dateMatch = logData.match(/\[(\d{2}\/\d{2}\/\d{4})/);

  if (dateMatch) {

      return dateMatch[1]; // Return the date if found
      
  }

  return null; // Return null if no date is found

}

function parseContainerTime(logData) {

  // Look for the first occurrence of "[MM/DD/YYYY HH:MM]"
  const timeMatch = logData.match(/\[\d{2}\/\d{2}\/\d{4} (\d{2}:\d{2})\]/);

  if (timeMatch) {

      return timeMatch[1]; // Return the time if found

  }

  return null; // Return null if no time is found

}

function parseItems(logData) {
  const lines = logData.split('\n');
  const items = [];
  let cataloging = false;

  for (let i = 0; i < lines.length; i++) {
    const line = lines[i];

    // Start cataloging after "Now we catalog items..."
    if (line.includes('Now we catalog items...')) {
      cataloging = true;
      continue;
    }

    // Stop cataloging at "Cataloguing done."
    if (line.includes('Cataloguing done.')) {
      break;
    }

    if (cataloging && line.includes('[Razor]:')) {
      let description = line.split('[Razor]: ')[1].trim();
      
      // Skip certain system messages
      if (description.includes('Added') || description.includes('Ignore List cleared') || description.includes('Finished')) {
        continue;
      }

      console.log(`Line ${i}: Found description: "${description}"`);

      // Look ahead for the "Added X to ignore list" line, skipping System messages
      for (let j = i + 1; j < lines.length; j++) {
        const lookAheadLine = lines[j];
        
        // Skip System messages
        if (lookAheadLine.includes('System:')) {
          continue;
        }
        
        // Found the ID line
        if (lookAheadLine.includes('Added') && lookAheadLine.includes('to ignore list')) {
          const idMatch = lookAheadLine.match(/Added (\d+) to/);
          if (idMatch) {
            console.log(`Pushing: ${description} -> ${idMatch[1]}`);
            items.push({
              description,
              id: idMatch[1],
            });
          }
          break;
        }
        
        // If we hit another [Razor] line, stop looking
        if (lookAheadLine.includes('[Razor]:')) {
          break;
        }
      }
    }
  }

  return items;
}

function extractAllChestLogs(content, startingRegex, endingRegex) {
  /**
   * Extracts all complete blocks of text starting with `startingRegex` and ending with `endingRegex`.
   * 
   * @param {string} content - The log content as a string.
   * @param {RegExp} startingRegex - The starting delimiter regex.
   * @param {RegExp} endingRegex - The ending delimiter regex.
   * @return {string[]} - An array of all valid blocks found.
   */
  const blocks = [];
  let startIndex;

  while ((startIndex = content.search(startingRegex)) !== -1) {
      const blockStartIndex = startIndex;

      // Find the end of the current block
      const afterStartContent = content.slice(blockStartIndex);
      const endMatch = afterStartContent.match(endingRegex);
      if (!endMatch) break; // No ending match, stop searching

      const blockEndIndex = blockStartIndex + endMatch.index + endMatch[0].length;

      // Check for another start delimiter within this block
      const withinBlockContent = content.slice(blockStartIndex + 1, blockEndIndex);
      const nextStartMatch = withinBlockContent.match(startingRegex);
      if (nextStartMatch) {
          // Skip this block since another start was found
          content = content.slice(blockStartIndex + 1);
          continue;
      }

      // Valid block found
      const block = content.slice(blockStartIndex, blockEndIndex);
      blocks.push(block);

      // Remove the current block from content
      content = content.slice(blockEndIndex);
  }

  return blocks;
}

function validateJSON(jsonString, requiredProps) {

  try {
      // Parse the JSON string
      const data = JSON.parse(jsonString);

      // Check if parsed data is an array
      if (!Array.isArray(data)) {
          throw new Error("JSON is valid but does not contain an array.");
      }

      // Validate each object in the array
      for (const item of data) {
          if (typeof item !== "object" || item === null) {
              throw new Error("One or more elements in the array are not objects.");
          }

          // Check if all required properties exist in the object
          for (const prop of requiredProps) {
              if (!item.hasOwnProperty(prop)) {
                  throw new Error(`Missing required property: ${prop}`);
              }
          }
      }

      return { valid: true, message: "Valid JSON with all required properties." };
  } catch (error) {
      return { valid: false, message: error.message };
  }

}

function parseInput () {
   console.log("parsing input");
    console.log(input.value);
   //if input empty show message
   if(input.value === ''){
     msg.value = "Paste text first."
     return 0;
   }
 
   const requiredProps = ["description", "price"];
   if(validateJSON(input.value, requiredProps).valid){
     msg.value = "valid json";
     items.value = JSON.parse(input.value);
     //here we need to update the prices for containers
    if(containers.value.length > 0){
      console.log('containers exist');

      containers.value.forEach((container) => {
        container.contents.forEach((content) => {
          const matchingItem = items.value.find(item => item.description === content.item.description);
          if(matchingItem){
   
            content.item.price = matchingItem.price;
          }
        });
      });

    }

    return 1
   }
 
   if(input.value.includes("[Razor]: Now we catalog items...")){
    containers.value = [];
    msg.value = "valid catalog";
 
     const startingRegex = /Now we catalog items\.\.\./;
     const endingRegex = /Cataloguing done\./;
     const chestLogs = extractAllChestLogs(input.value, startingRegex, endingRegex);
 
     if(chestLogs.length < 0){
 
       msg.value = "No valid chest catalogs found."
 
       return 0;
 
     }
       let id = 1;
     chestLogs.forEach((chestLog) => {

       const parsedItems = parseItems(chestLog);
       const contents = [];
      
      parsedItems.forEach((parsedItem) => { 
      
        if(parsedItems.length > 0) {
      
          //check if item already exists in items list
          const existingItem = items.value.find(existingItem => existingItem.description === parsedItem.description);
          
          let item;
          if(existingItem){
            //the item exists - create a new object with the current parsedItem's ID
            item = {
              description: parsedItem.description,
              type: existingItem.type,
              price: existingItem.price,
              id: parsedItem.id,
            };
          } else {
            //the item does not exist and should be added
            item = {
              description: parsedItem.description,
              id: parsedItem.id,
              type: getItemType(parsedItem.description),
              price: 0,
            };
            items.value.push(item);
          }
          
        contents.push({
            item: item,
        });
        }


      });

       const container = {
           id : id++,
           serial: parseId(chestLog),
           date_updated: parseContainerDate(chestLog),
           time_updated: parseContainerTime(chestLog),
           contents: contents,
           show: true
       }
 
       containers.value.push(container);
 
     });
 
     console.log({
      items,containers
     });
 
     return 1;
   }
 
}

function downloadJSON(jsonObject, fileName) {
    // Convert JSON object to string
    const jsonString = JSON.stringify(jsonObject, null, 2);

    // Create a Blob from the JSON string
    const blob = new Blob([jsonString], { type: "application/json" });

    // Create a link element
    const link = document.createElement("a");

    // Set the download attribute with the filename
    link.download = fileName;

    // Create a URL for the Blob and set it as the href attribute
    link.href = URL.createObjectURL(blob);

    // Append the link to the document
    document.body.appendChild(link);

    // Programmatically click the link to trigger the download
    link.click();

    // Remove the link from the document
    document.body.removeChild(link);
}

function getItemType(itemDescription) {
  for (const [type, itemsOfType] of Object.entries(ItemTypes)) {

    if (itemsOfType.some(item => itemDescription.includes(item))) {
      return type;
    }
    
  }
  return -1;
}

function copyToClipboard(textToCopy){
  navigator.clipboard.writeText(textToCopy);
}

function copyCatalogLootScript (event){

  const textToCopy = `
  setvar container_to_catalog

  wait 250
  
  setvar take_container

  wait 250

  if not listexists item_ids

  createlist item_ids

  else

  clearlist item_ids


  endif
    
    
  # SET ITEMS TO CATALOG HERE #
  # Comment out any item types that you do not want to catalog.
  #Phylacteries:
  pushlist item_ids "Phylactery"        
  #Skill Scrolls:
  pushlist item_ids "scroll of calling"
  #Apect Cores:
  pushlist item_ids "aspect core"
  pushlist item_ids "chroma core"
  #Distillations
  pushlist item_ids "flask"
  pushlist item_ids "chroma distil"
  #Maps
  pushlist item_ids "map"
    
  //split items
  sysmsg "First we split item stacks..."
  wait 250
  sysmsg container_to_catalog
  wait 250
  //split items
  foreach id in item_ids

      while findtype id container_to_catalog as item
          
        lift item 1
        
        wait 250
          
        drop take_container
        
        wait 250
        
        ignore item
      endwhile

  endfor
  clearignore
  
  //catalog items
  sysmsg "Now we catalog items..."
  wait 250
  sysmsg take_container
  wait 250
  foreach id in item_ids

  while findtype id take_container as item

          getlabel item description

          sysmsg description

          wait 250
          
          ignore item
          
      endwhile

  endfor
  
  clearignore
  
  sysmsg "Cataloguing done."
  
  wait 250
  
  sysmsg "You can now log out to create a journal entry. Finds your journal logs here: Game FolderClassicUODataClientJournalLogs"`
  
  //show popoover that hides on next click
  const copyScriptBtn = event.target;
  copyScriptBtn.innerText = 'Copied!'
  window.setTimeout(() => {
  
    const copyScriptBtn = document.getElementById('copyCatalogLootScriptBtn');
    copyScriptBtn.innerText = 'Copy Catalog Loot Script'
  }, 2000)
  return copyToClipboard(textToCopy);
}

function copyCatalogEquipmentScript (event){

  const textToCopy = `
  setvar container_to_catalog

  wait 250
  
  setvar take_container

  wait 250

  if not listexists equipment_ids

  createlist equipment_ids

  else

  clearlist equipment_ids


  endif


pushlist equipment_ids "leather armor"
pushlist equipment_ids "leather sleeves"
pushlist equipment_ids "leather tunic"
pushlist equipment_ids "leather Gloves"
pushlist equipment_ids "leather gorget"
pushlist equipment_ids "leather cap"
pushlist equipment_ids "leather bustier"
pushlist equipment_ids "leather leggings"
pushlist equipment_ids "leather shorts"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "bone arms"
pushlist equipment_ids "bone armor"
pushlist equipment_ids "bone gloves"
pushlist equipment_ids "studded gorget"
pushlist equipment_ids "bone gorget"
pushlist equipment_ids "bone helmet"
pushlist equipment_ids "bone legs"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "leather jerkin"
pushlist equipment_ids "bone skirt"
pushlist equipment_ids "studded armor"
pushlist equipment_ids "studded sleeves"
pushlist equipment_ids "studded gloves"
pushlist equipment_ids "studded gorget"
pushlist equipment_ids "leather cap"
pushlist equipment_ids "studded cap"
pushlist equipment_ids "studded leggings"
pushlist equipment_ids "studded tunic"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "studded skirt"
pushlist equipment_ids "studded bustier"
pushlist equipment_ids "orc helm"
pushlist equipment_ids "ringmail sleeves"
pushlist equipment_ids "ringmail tunic"
pushlist equipment_ids "ringmail gloves"
pushlist equipment_ids "studded gorget"
pushlist equipment_ids "ringmail gorget"
pushlist equipment_ids "helmet"
pushlist equipment_ids "ringmail leggings"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "ringmail skirt"
pushlist equipment_ids "ringmail sleeves"
pushlist equipment_ids "chain arms"
pushlist equipment_ids "chainmail tunic"
pushlist equipment_ids "ringmail gloves"
pushlist equipment_ids "chain gloves"
pushlist equipment_ids "leather gorget"
pushlist equipment_ids "chain gorget"
pushlist equipment_ids "chainmail coif"
pushlist equipment_ids "chainmail leggings"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "chain skirt"
pushlist equipment_ids "platemail arms"
pushlist equipment_ids "norse helm"
pushlist equipment_ids "close helm"
pushlist equipment_ids "plate armor"
pushlist equipment_ids "platemail gloves"
pushlist equipment_ids "platemail gorget"
pushlist equipment_ids "plate helm"
pushlist equipment_ids "platemail legs"
pushlist equipment_ids "platemail"
pushlist equipment_ids "bascinet"
pushlist equipment_ids "leather skirt"
pushlist equipment_ids "plate skirt"

pushlist equipment_ids "abyssal staff"
pushlist equipment_ids "ancestral staff"
pushlist equipment_ids "angelspire staff"
pushlist equipment_ids "boneshard staff"
pushlist equipment_ids "burial staff"
pushlist equipment_ids "celestial staff"
pushlist equipment_ids "chaos staff"
pushlist equipment_ids "darklight staff"
pushlist equipment_ids "dragonbone staff"
pushlist equipment_ids "druid staff"
pushlist equipment_ids "fiendish staff"
pushlist equipment_ids "helix staff"
pushlist equipment_ids "nautilus staff"
pushlist equipment_ids "necromancer staff"
pushlist equipment_ids "paradox staff"
pushlist equipment_ids "prismatic staff"
pushlist equipment_ids "razorcrest staff"
pushlist equipment_ids "shaman staff"
pushlist equipment_ids "summoner staff"
pushlist equipment_ids "vine staff"

pushlist equipment_ids "balestra"
pushlist equipment_ids "bow"
pushlist equipment_ids "crossbow"
pushlist equipment_ids "long bow"
pushlist equipment_ids "heavy crossbow"
pushlist equipment_ids "composite bow"
pushlist equipment_ids "stubnose crossbow"
pushlist equipment_ids "tower guard crossbow"
pushlist equipment_ids "recurve bow"

pushlist equipment_ids "assassins dagger"
pushlist equipment_ids "dagger"
pushlist equipment_ids "epee"
pushlist equipment_ids "sabre"
pushlist equipment_ids "tracker knife"
pushlist equipment_ids "kryss"
pushlist equipment_ids "kukri"
pushlist equipment_ids "pitchfork"
pushlist equipment_ids "rapier"
pushlist equipment_ids "sarissa"
pushlist equipment_ids "short spear"
pushlist equipment_ids "Long Spear"
pushlist equipment_ids "war fork"

pushlist equipment_ids "bamboo flute"
pushlist equipment_ids "drum"
pushlist equipment_ids "gemshorn"
pushlist equipment_ids "lap harp"
pushlist equipment_ids "hurdy gurdy"
pushlist equipment_ids "lute"
pushlist equipment_ids "psaltery"
pushlist equipment_ids "sackbut"
pushlist equipment_ids "shawm"
pushlist equipment_ids "tambourine"
pushlist equipment_ids "vielle"

pushlist equipment_ids "black staff"
pushlist equipment_ids "club"
pushlist equipment_ids "cudgel"
pushlist equipment_ids "flail"
pushlist equipment_ids "flanged mace"
pushlist equipment_ids "giant club"
pushlist equipment_ids "giant spiked club"
pushlist equipment_ids "gnarled staff"
pushlist equipment_ids "great mace"
pushlist equipment_ids "sledgehammer"
pushlist equipment_ids "hammer pick"
pushlist equipment_ids "mace"
pushlist equipment_ids "maul"
pushlist equipment_ids "quarter staff"
pushlist equipment_ids "shepherd's crook"
pushlist equipment_ids "skull club"
pushlist equipment_ids "war axe"
pushlist equipment_ids "war hammer"
pushlist equipment_ids "war mace"

pushlist equipment_ids "spellbook"
pushlist equipment_ids "wand"

pushlist equipment_ids "bone shield"
pushlist equipment_ids "bronze shield"
pushlist equipment_ids "buckler"
pushlist equipment_ids "Chaos shield"
pushlist equipment_ids "heater shield"
pushlist equipment_ids "kite shield"
pushlist equipment_ids "metal shield"
pushlist equipment_ids "Order shield"
pushlist equipment_ids "parrying gauche"
pushlist equipment_ids "wooden kite shield"
pushlist equipment_ids "wooden shield"

pushlist equipment_ids "axe"
pushlist equipment_ids "bardiche"
pushlist equipment_ids "battle axe"
pushlist equipment_ids "broadsword"
pushlist equipment_ids "cutlass"
pushlist equipment_ids "double axe"
pushlist equipment_ids "executioner's axe"
pushlist equipment_ids "giant axe"
pushlist equipment_ids "halberd"
pushlist equipment_ids "katana"
pushlist equipment_ids "large battle axe"
pushlist equipment_ids "longsword"
pushlist equipment_ids "norse axe"
pushlist equipment_ids "scimitar"
pushlist equipment_ids "two handed axe"
pushlist equipment_ids "viking sword"
pushlist equipment_ids "zweihander"

pushlist equipment_ids "kryss"
pushlist equipment_ids "viking sword"
pushlist equipment_ids "club"
pushlist equipment_ids "bow"

pushlist equipment_ids "caesti"
pushlist equipment_ids "fistblade"
pushlist equipment_ids "martial arts scroll"

pushlist equipment_ids "harpoon"
pushlist equipment_ids "trident"
pushlist equipment_ids "buckler "
    
  # SET ITEMS TO CATALOG HERE #
  # Comment out any item types that you do not want to catalog.
  
    
  //split items
  sysmsg "Now we catalog items..."
  wait 250
  sysmsg container_to_catalog
  wait 250
  foreach id in equipment_ids

      while findtype id container_to_catalog as item
      
        getlabel item description
        
        sysmsg description
          
        lift item 1
        
        wait 250
          
        drop take_container
        
        wait 250
        
        ignore item
      endwhile

      endfor
      
  clearignore
  
  sysmsg "Cataloguing done."
  
  wait 250
  
  sysmsg "You can now log out to create a journal entry. Finds your journal logs here: Game FolderClassicUODataClientJournalLogs"`
  
  //show popoover that hides on next click
  const copyScriptBtn = event.target;
  copyScriptBtn.innerText = 'Copied!'
  window.setTimeout(() => {
  
    const copyScriptBtn = document.getElementById('copyCatalogEquipmentScriptBtn');
    copyScriptBtn.innerText = 'Copy Catalog Loot Script'
  }, 2000)
  return copyToClipboard(textToCopy);
}

function removeContainer(containerId){

  containers.value = containers.value.filter(function( obj ) {
      return obj.id !== containerId;
  });
}

function removeItemFromContainer(containerId, itemID){

  const container = containers.value.find(c => c.id === containerId);
  if(container){
    container.contents = container.contents.filter(function( obj ) {
        return obj.item.id !== itemID;
    });
  }

}

function updateContainerContentsPrices(event){
  console.log(event);
    if(containers.value.length > 0){
    console.log('containers exist');

    containers.value.forEach((container) => {
      container.contents.forEach((content) => {
        const matchingItem = items.value.find(item => item.description === content.item.description);
        if(matchingItem){
  
          content.item.price = matchingItem.price;
        }
      });
    });

  }
  
}

onMounted(() => {
  //input.value = testJournal;
  //parseInput();
});

</script>

<template>

  <div
  class="px-4 mt-5"
  >

    <!--Nav-->

    <!--step 1 Copy and run script-->
    <div class="row mb-4">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary text-light">Step 1: Copy and run the catalog script.</div>

        <div class="card-body d-flex flex-column justify-content-center">

          <div class="d-flex justify-content-center">

            <div
            class="card"
            >

              <div class="card-body">
                <p class="fw-bold">Catalog Loot:</p>
                <p class="fw-bold">Supported Items:</p>
                <ul class="list-group">
                  <li class="list-group-item">Aspect Cores</li>
                  <li class="list-group-item">Aspect Distillations</li>
                  <li class="list-group-item">Skill Scrolls</li>
                  <li class="list-group-item">Phylacteries</li>
                  <li class="list-group-item">Maps</li>
                  <li class="list-group-item">More coming soon...</li>
                </ul>

                <button id="copyCatalogLootScriptBtn" type="button" class="btn btn-secondary" @click="copyCatalogLootScript">Copy Catalog Loot Script</button>

                </div>

            </div>

            <div
            class="card ms-4"
            >

              <div class="card-body d-flex flex-column justify-content-between">
                <div>
                  <p class="fw-bold">Catalog Equipment:</p>
                  <p class="fw-bold">Supported Items:</p>
                  <ul class="list-group">
                    <li class="list-group-item">Most Equipment</li>
                  </ul>
                </div>

                <button id="copyCatalogEquipmentScriptBtn" type="button" class="btn btn-secondary" @click="copyCatalogEquipmentScript">Copy Catalog Equipment Script</button>

                </div>

            </div>
          </div>

          <p class="mt-4">Once cataloging is done, log out of the game. This creates a new journal log.</p>

        </div>

      </div>

    </div>
    <!--step 1-->

    <!--step 2 Copy Journal text-->
    <div class="row mb-4">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary text-light">Step 2: Find and copy latest journal log.</div>

        <div class="card-body d-flex flex-column justify-content-center">

          <p>Find your journal logs here:</p>
          <p> Game Folder\ClassicUO\Data\Client\JournalLogs</p>

        </div>

      </div>

    </div>
    <!--step 2-->

    <!--step 3 Import-->
    <div class="row mb-4">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary text-light">Step 3: Import journal text.</div>

          <div class="card-body">

            <form>
              
              <div class="mb-3 d-flex flex-column">
  
                  <label for="floatingTextarea" class="form-label">Journal Text: </label>
  
                  <textarea 
                    id="journalInput" 
                    class="form-control" 
                    :class="{ 'drag-over': isDragging }"
                    placeholder="Paste journal text, items.json, or drop a file here"
                    v-model="input"
                    @drop.prevent="handleDrop"
                    @dragover.prevent="handleDragOver"
                    @dragenter.prevent="handleDragEnter"
                    @dragleave.prevent="handleDragLeave"
                  ></textarea>
  
                  <div  class="form-text">Journal text/item json</div>
  
                  <div  class="form-text text-muted" ><p>{{ msg }}</p></div>
  
                  <button type="button" class="btn btn-primary " @click="parseInput()">Import</button>
      
              </div>
  
  
            </form>
          </div>

      </div>

    </div>
    <!--step 3-->

    <!--step 4 Items-->
    <div class="row mb-4">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary text-light">Step 4: Set item prices.</div>

        <div class="card-body d-flex flex-column justify-content-center">

          <ItemsTable 
          :items="items"
          @update:items="updateContainerContentsPrices($event)"
          />

          <button 
          v-show="items.length > 1 && !minimizeItemsTable" type="button" class="btn btn-primary" @click="downloadJSON(items, 'items.json')">Export Item Prices</button>

        </div>

      </div>

    </div>
    <!--step 4-->

    <!--step 5 Containers-->
    <div class="row mb-4">

      <div class="card mx-auto p-0 ">

        <div class="card-header bg-primary text-light">Step 5: Copy and run the Vendor Restock Script.</div>

        <div class="card-body d-flex flex-column justify-content-center">
          <img src="/images/store_menu.jpg" class="w-50 mx-auto">
          <div 
          v-for="container in containers" :key="container.id"
          class="row mt-5 mx-5">

            <div class="col-12">

              <div class="card border shadow">

                <div class="d-flex card-header text-light bg-primary">
                  <p class="mb-0 mt-2">Container Serial: {{ container.serial + ' / ' + parseInt(container.serial).toString(16) }}</p>
                  <button
                  class="btn border border-light text-light ms-auto"
                  @click="removeContainer(container.id)"
                  > X </button>
                  <button 
                  class="btn border border-light text-light ms-2"
                  @click="container.show = !container.show"
                  ><strong>{{ container.show ? '+' : '-' }}</strong></button>
                </div>

                <div class="card-body"
                    v-show="container.show"
                >
                  <ContainerContentsTable
                    :containerID="container.id"
                    :containerContents="container.contents"
                    @removeItem="removeItemFromContainer(container.id, $event)"
                  />   

  
                </div>


              </div>
          
            </div>

          </div>
        </div>

      </div>

    </div>
    <!--step 5-->

    
    <!--step 6 Check prices-->
    <div class="row mb-4">

      <div class="card mx-auto p-0">

        <div class="card-header bg-primary text-light">Step 6: Your items should now be stocked. Make sure to double check the prices.</div>

        <div class="card-body d-flex flex-column justify-content-center">

          <p>This site is still in development and may contain bugs. You should always check the prices of stocked items.</p>

        </div>

      </div>

    </div>
    <!--step 6-->
  </div>

</template>

