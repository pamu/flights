package com.freeflyair.client.pages

sealed trait Location

case object FormPage extends Location

case object ResultsPage extends Location
