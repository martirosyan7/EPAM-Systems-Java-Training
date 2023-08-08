package com.epam.rd.autocode.factory.plot;

import com.epam.rd.autocode.Named;

class PlotFactories {

    class MoviePlot implements Plot {
        String hero, beloved, villain, format;
        MoviePlot(Named hero, Named beloved, Named villain, String format) {
            this.hero = hero.name();
            this.beloved = beloved.name();
            this.villain = villain.name();
            this.format = format;
        }

        public String toString() {
            return String.format(format, hero, beloved, villain);
        }

    }


    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        String format = "%1$s is great. %1$s and %2$s love each other. %3$s interferes with their happiness but loses in the end.";
        return () -> new MoviePlot(hero, beloved, villain, format);
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        String format = "%1$s feels a bit awkward and uncomfortable. " +
                "But personal issues fades, when a big trouble comes - %2$s. " +
                "%1$s stands up against it, but with no success at first." +
                "But putting self together and help of friends, " +
                "including spectacular funny %3$s restore the spirit and %1$s overcomes the crisis " +
                "and gains gratitude and respect";
        return () -> new MoviePlot(hero, epicCrisis, funnyFriend, format);
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        String subFormat1 = "";
        for (int i = 0; i < heroes.length; i++) {
            if (i == heroes.length - 1) {
                subFormat1 += "brave " + heroes[i].name() + "";
            } else {
                subFormat1 += "brave " + heroes[i].name() + ", ";
            }

        }
        final String subFormat = subFormat1;
        String format = "%2$s threatens the world. But %1$s on guard. So, no way that intrigues of %3$s overcome the willpower of inflexible heroes";
        return () -> new MoviePlot(() -> subFormat, epicCrisis, villain, format);
    }
}
