class Webartifact {
    static belongsTo = [instance: TomcatInstance ]
    static mapping = {
      columns {
         release column:'release_tag'
      }
    }
    String name
    String release
    String revision

    String toString() {
        "${name} : ${release}.${revision} $absolutePath"
    }
}
