package lastprofstanding.ui

data class NavController(
    var activeRoute: Route = Route.START_SCREEN
) {
    enum class Route {
        START_SCREEN,
        GAME_SCREEN,
        END_SCREEN;
    }
}