package refactoring;


public class GildedRose {


    public static final int MAX_QUALITY = 50;
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SELL_IN_SIX = 6;
    public static final int SELL_IN_TEN = 10;
    public static final int QUALITY_THREE = 3;
    public static final int QUALITY_TWO = 2;
    Item[] items;


    public GildedRose(Item[] items) {

        this.items = items;

    }

    private void increaseQuality(Item item, int num) {
        if (item.quality + num <= MAX_QUALITY) {
            item.quality = item.quality + num;
        } else {
            item.quality = MAX_QUALITY;
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn--;
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }

    private void increaseBackstageQuality(Item item) {
        int sellIn = item.sellIn;
        if (sellIn <= 0) {
            item.quality = 0;
        }
        if (sellIn < SELL_IN_SIX) {
            increaseQuality(item, QUALITY_THREE);
        }
        if (sellIn <= SELL_IN_TEN) {
            increaseQuality(item, QUALITY_TWO);
        }
        if (sellIn > SELL_IN_TEN) {
            increaseQuality(item, 1);
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            switch (item.name) {
                case AGED_BRIE:
                    increaseQuality(item, 1);
                    decreaseSellIn(item);
                    break;
                case SULFURAS_HAND_OF_RAGNAROS:
                    break;
                case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                    increaseBackstageQuality(item);
                    decreaseSellIn(item);
                    break;
                default:
                    decreaseSellIn(item);
                    decreaseQuality(item);
            }
        }
    }


    public static void main(String[] args) {

        System.out.println("OMGHAI!");


        Item[] items = new Item[]{

                new Item("+5 Dexterity Vest", 10, 20), //

                new Item("Aged Brie", 2, 0), //

                new Item("Elixir of the Mongoose", 5, 7), //

                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //

                new Item("Sulfuras, Hand of Ragnaros", -1, 80),

                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),

                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),

                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),

                // this conjured item does not work properly yet

                new Item("Conjured Mana Cake", 3, 6)};


        GildedRose app = new GildedRose(items);


        int days = 2;

        if (args.length > 0) {

            days = Integer.parseInt(args[0]) + 1;

        }


        for (int i = 0; i < days; i++) {

            System.out.println("-------- day " + i + " --------");

            System.out.println("name, sellIn, quality");

            for (Item item : items) {

                System.out.println(item);

            }

            System.out.println();

            app.updateQuality();

        }

    }


}