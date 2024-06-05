{
  outputs = inputs@{ ... }:
    let
      systems = [
        "x86_64-linux"
        "aarch64-linux"
      ];

      forAllSystems = function:
        inputs.nixpkgs.lib.genAttrs systems
          (system:
            function (import inputs.nixpkgs { inherit system; })
          );
    in
    {
      devShells = forAllSystems (pkgs:
        {
          default = import ./shell.nix { inherit pkgs; };
        });
    };

  inputs = {
    nixpkgs.url = "nixpkgs/nixos-unstable";
  };
}
